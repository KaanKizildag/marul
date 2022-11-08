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
              <Table :data="filteredCustomers">
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
              <Table :data="filteredProductList" style="width: 100%">
                <el-table-column label="Ürün Adı" prop="urunAdi" :sortable="true"/>
                <el-table-column label="Birim Fiyatı" prop="fiyat" :sortable="true"/>
                <el-table-column align="right">
                  <template #header>
                    <el-input v-model="searchProductName" size="small" placeholder="Ara"/>
                  </template>
                  <template #default="scope">
                    <el-button style="margin-left: 20px" @click="addToCart(scope.row)" type="warning" plain>Sepete
                      Ekle
                    </el-button>
                  </template>
                </el-table-column>
              </Table>
            </div>
          </div>
        </div>

        <Cart :cartArray="cartArray" @changeProductCount="changeProductCount"/>
      </template>


      <!--  TODO:  MAKE A SALE COMPONENT -->

      <el-button v-if="saleState === addToCartState && cartArray.length > 0"
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
const cartArray = ref([])
const active = ref(0)
const musteriId = ref(null)
const search = ref('')
const searchProductName = ref('')
const customers = ref([])
const productList = ref([])

const satisInsertDto = reactive({
  musteriId: null,
  satisDtoList: []
});

onMounted(() => {
  emits("pageName", "SATIŞ")
  findAllProduct();
  findAllCustomers();

})

const confirmCart = () => {
  saleState.value = makeASale
  nextStep();
}

const sale = async () => {
  saleState.value = makeASale

  satisInsertDto.satisDtoList = satisInsertDto.satisDtoList.filter(sales => sales.satilanAdet > 0);
  const result = await salesService.save(satisInsertDto).then(resp => resp.data).catch(error => error.response)

  if (!result.success) {
    errorResponse(result.message)
    cartArray.value = []
    satisInsertDto.satisDtoList = []
    saleState.value = addToCartState;
    return;
  }

  successResponse(result.message);

  nextStep();

  setTimeout(()=>successResponse("Fatura mail ile gönderildi"),500);

  nextStep();
  saleState.value = selectCustomer
  satisInsertDto.musteriId = null;
  satisInsertDto.satisDtoList = []
  cartArray.value = []
}


const nextStep = () => {
  if (active.value++ > 2) active.value = 0;
}

const findAllProduct = () => {
  productService.findAllPrdocut().then(resp => productList.value = resp.data.data)
}

const addToCart = (row) => {
  let satisDto = {
    urunId: row.id,
    satilanAdet: 1,
  }
  satisInsertDto.musteriId = musteriId.value
  satisInsertDto.satisDtoList.push(satisDto);
  const salesD = {
    musteriId: musteriId.value,
    urunId: row.id,
    satilanAdet: satisDto.satilanAdet,
    urunAdi: row.urunAdi,
    tutar: satisDto.satilanAdet * row.fiyat
  }

  cartArray.value.push(salesD)
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
  customers.value = result.data;
  successResponse(result.message);
}
const removeFromCartHandler = (row) => {
  cartArray.value = cartArray.value.filter(sale => sale.urunId !== row.urunId)
}

const changeProductCount = (row) => {
  let product = satisInsertDto.satisDtoList.find(satis => satis.urunId == row.urunId);
  product.satilanAdet = row.satilanAdet
  if (row.satilanAdet === 0) {
    removeFromCartHandler(row);
  }
}

const filteredCustomers = computed(() =>
    customers.value
        .filter((data) => !search.value
            || data.musteriAdi.toLowerCase()
                .includes(search.value.toLowerCase())
        )
)
const filteredProductList = computed(() =>
    productList.value
        .filter((data) => !searchProductName.value
            || data.urunAdi.toLowerCase()
                .includes(searchProductName.value.toLowerCase())
        )
)

</script>
