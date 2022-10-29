<template>
  <div>
    <!--  TODO:  STEPS COMPONENT -->
    <el-steps :active="active" finish-status="success" simple style="margin-top: 20px">
      <el-step title="Müşteri Seç"></el-step>
      <el-step title="Sepete Ekle"></el-step>
      <el-step title="Satış Yap"></el-step>
    </el-steps>
    <!--  TODO:  STEPS COMPONENT -->

    <div class="row">
      <template v-if="saleState === selectCustomer">
        <!--  TODO:  CUSTOMER SELECTOR COMPONENT -->
        <div class="col-md-9 mt-4">
          <div class="card">
            <div class="card-header pb-0 px-3">
              <h6 class="mb-0">Müşteri Listesi</h6> <br/>
              <Table :data="filterTableData">
                <el-table-column label="Müşteri adı" prop="musteriAdi" :sortable="true"/>
                <el-table-column label="Telefon no" prop="telefonNo" :sortable="true"/>
                <el-table-column label="Email" prop="email" :sortable="true"/>
                <el-table-column label="Teslimat noktası" prop="teslimatNoktasi" :sortable="true"/>
                <el-table-column label="Borç" prop="borc" :sortable="true"/>
                <el-table-column label="Tur adı" prop="turAdi" :sortable="true"/>
                <el-table-column align="right">
                  <template #header>
                    <el-input v-model="search" size="small" placeholder="Ara"/>
                  </template>
                  <template #default="scope">
                    <el-button type="success" @click="handleProductList(scope.row)" :icon="ArrowRightBold" circle/>
                  </template>
                </el-table-column>
              </Table>
            </div>
          </div>
        </div>
        <SalesSummary/>
      </template>
      <!--  TODO:  CUSTOMER SELECTOR COMPONENT -->

      <template v-if="saleState === addToCartState || saleState === makeASale">
        <!--  TODO:  MAKE A SALE COMPONENT -->
        <div class="col-md-9 mt-4">
          <div class="card">
            <div class="card-header pb-0 px-3">
              <h6 class="mb-0">Ürün Listesi</h6> <br/>
              <Table :data="productList" style="width: 100%">
                <el-table-column label="Ürün Adı" prop="urunAdi" :sortable="true"/>
                <el-table-column label="Fiyat" prop="fiyat" :sortable="true"/>
                <el-table-column label="Adet" align="center">
                  <template #default="scope">
                    <el-input-number v-model="scope.row.adet" :min="1" :max="9999999"></el-input-number>
                    <el-button style="margin-left: 20px" @click="addToCart(scope.row)" type="warning" plain>Sepete
                      Ekle
                    </el-button>
                  </template>
                </el-table-column>
              </Table>
            </div>
          </div>
        </div>

        <Cart :saleArray="saleArray" @removeFromCart="removeFromCartHandler"/>
      </template>


      <!--  TODO:  MAKE A SALE COMPONENT -->

      <el-button v-if="saleState === addToCartState && saleArray.length > 0"
                 style="margin-top: 12px; width: 100px; margin-left: 20px" @click="confirmCart">Sepeti Onayla
      </el-button>
      <el-button v-if="saleState === makeASale" style="margin-top: 12px; width: 100px; margin-left: 20px" @click="sale">
        Satış Yap
      </el-button>

    </div>
  </div>
</template>

<script setup>
import {computed, defineEmits, onMounted, reactive, ref} from "vue";
import Table from "../common/Table.vue";
import SalesSummary from "../../components/sales/SalesSummary.vue";
import {ArrowRightBold} from '@element-plus/icons-vue'
import {CustomerService} from "../../services/CustomerService.js";
import {ProductService} from "../../services/ProductService.js";
import {SalesService} from "../../services/SalesService.js";
import NotificationService from "../../services/NotificationService.js";
import Cart from "./Cart.vue";

const {successResponse, errorResponse} = NotificationService();

const emits = defineEmits(["pageName"])

const customerService = new CustomerService();
const productService = new ProductService();
const salesService = new SalesService();

const selectCustomer = "SELECT_CUSTOMER";
const addToCartState = "ADD_TO_CART_STATE";
const makeASale = "MAKE_A_SALE";

const saleState = ref(selectCustomer)
const saleArray = ref([])
const cart = ref([])
const active = ref(0)
const musteriId = ref(null)
const search = ref('')
const amount = ref(0)
const tableData = ref([])
const productList = ref([])
const steps = ref(null)

onMounted(() => {
  emits("pageName", "SATIŞ")
  findAllProduct();
  findAllCustomers();

})

const confirmCart = () => {
  saleState.value = makeASale
  nextStep()
}

const sale = async () => {
  saleArray.value.forEach(p => {
    let product = productList.value.filter(product => product.id === p.urunId)
    cart.value.push(product[0])
  })
  saleState.value = makeASale

  const result = await salesService.save(saleArray.value).then(resp => resp.data).catch(error => error.response)

  if (!result.success) {
    errorResponse(result.message)
    return;
  }

  successResponse(result.message);

  nextStep()
}


const nextStep = () => {
  if (active.value++ > 2) active.value = 0;
}

const findAllProduct = () => {
  productService.findAllPrdocut().then(resp => productList.value = resp.data.data)
}

const addToCart = (row) => {
  let satisUrunDtoList = {
    urunId: row.id,
    satilanAdet: row.adet == null ? row.adet = 1 : row.adet,
  }
  const salesD = {
    musteriId: musteriId.value,
    urunId: row.id,
    satilanAdet: row.adet == null ? row.adet = 1 : row.adet,
    satisUrunDtoList: [],
    urunAdi: row.urunAdi,
    tutar: row.adet * row.fiyat
  }
  salesD.satisUrunDtoList.push(satisUrunDtoList)

  let product = saleArray.value.find(sale => sale.urunId === salesD.urunId);
  if (product !== undefined) {
    product.satilanAdet += row.adet
    product.tutar += row.adet * row.fiyat
    saleArray.value.push()
    return;
  }

  saleArray.value.push(salesD)
}

const handleProductList = (customer) => {
  musteriId.value = customer.id
  saleState.value = addToCartState
  nextStep()
}

const findAllCustomers = async () => {
  let result = await customerService.findAllCustomer()
      .then(response => response.data)
      .catch(error => error.response.data);

  if (!result.success) {
    errorResponse(result.message)
    return;
  }
  tableData.value = result.data;
  successResponse(result.message);
}
const removeFromCartHandler = (row) => {
  saleArray.value = saleArray.value.filter(sale => sale.urunId !== row.urunId)
}

const filterTableData = computed(() =>
    tableData.value
        .filter((data) => !search.value
            || data.musteriAdi.toLowerCase()
                .includes(search.value.toLowerCase())
        )
)

</script>
