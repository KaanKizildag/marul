<template>
  <div class="col-md-9 mt-4">
    <div class="card">
      <div class="card-header pb-0 px-3">
        <h6 class="mb-0">Ürün Listesi</h6> <br/>
        <el-button type="primary" plain @click="addProduct">Ekle</el-button>

        <Dialog :dialog-visible="dialogVisible" :title="'Ürün Ekle'" @open="open">
          <div class="input-group input-group-outline mb-3">
            <input type="text" v-model="urunDto.urunAdi" class="form-control" placeholder="Ürün Adı">
          </div>
          <div class="input-group input-group-outline mb-3">
            <input type="number" v-model="urunDto.fiyat" class="form-control" placeholder="Ürün Fiyat">
          </div>
          <div class="input-group input-group-outline mb-3">
            <input type="number" v-model="urunDto.kdv" class="form-control" placeholder="Kdv">
          </div>
          <el-select v-model="urunDto.kategoriId" filterable placeholder="Select">
            <el-option
                v-for="item in categories"
                :key="item.id"
                :label="item.kategoriAdi"
                :value="item.id"
            />
          </el-select>

          <template #buttons>
            <el-button @click="dialogVisible = false">Vazgeç</el-button>
            <el-button type="primary" @click="save"
            > {{ buttonText }}
            </el-button>
          </template>
        </Dialog>

      </div>
      <div class="card-body pt-4 p-3">
        <Table :data="filterTableData">
          <el-table-column label="Ürün Adı" prop="urunAdi" :sortable="true"/>
          <el-table-column label="Fiyat" prop="fiyat" :sortable="true"/>
          <el-table-column label="Kdv" prop="kdv" :sortable="true"/>
          <el-table-column label="Kategori Adı" prop="kategoriAdi" :sortable="true"/>
          <el-table-column align="right">
            <template #header>
              <el-input v-model="search" size="small" placeholder="Ara"/>
            </template>
            <template #default="scope">
              <DeleteButton @click="remove(scope.row.id)"/>
              <EditButton @click="update(scope.row)"/>
            </template>
          </el-table-column>
        </Table>
      </div>
    </div>
  </div>
</template>

<script setup>
import {computed, ref} from "vue";
import Dialog from "../common/Dialog.vue";
import {ProductService} from "../../services/ProductService.js";
import {CategoryService} from "../../services/CategoryService.js";
import NotificationService from "../../services/NotificationService.js";
import DeleteButton from "../common/DeleteButton.vue";
import EditButton from "../common/EditButton.vue";
import Table from "../common/Table.vue";


const productService = new ProductService();
const categoryService = new CategoryService();

const {successResponse, errorResponse} = NotificationService();

const dialogVisible = ref(false)
const isUpdate = ref(false)
const tableData = ref([])
const categories = ref([]);
const search = ref('')

const urunDto = ref({
  urunAdi: "",
  fiyat: "",
  kdv: "",
})

const findAllProduct = () => {
  productService.findAllPrdocut().then(resp => tableData.value = resp.data.data)
}

findAllProduct();

const findAllCategories = () => {
  categoryService.findAllCategories()
      .then(response => categories.value = response.data.data);
}

findAllCategories();

function open() {
  console.log('opennn')
}

const addProduct = () => {
  dialogVisible.value = true
  isUpdate.value = false
}
const buttonText = computed(() => isUpdate.value ? "Güncelle" : "Kaydet")
const save = async () => {
  urunDto.value.urunAdi = urunDto.value.urunAdi.toUpperCase();
  let result;
  if (!isUpdate.value) {
    result = await productService.savePrdocut(urunDto.value)
        .then(response => response.data)
        .catch((error) => error.response.data)
  } else {
    result = await productService.updateProduct(urunDto.value)
        .then(response => response.data)
        .catch((error) => error.response.data)
  }

  if (!result.success) {
    errorResponse(result.message)
    return;
  }

  dialogVisible.value = false
  urunDto.value = {
    urunAdi: "",
    fiyat: "",
    kdv: "",
    kategoriId: null,
  }
  successResponse(result.message)
  findAllProduct();
}

const cancel = () => {
  dialogVisible.value = false
  urunDto.value = {
    urunAdi: "",
    fiyat: "",
    kdv: "",
  }
}

const update = (row) => {
  dialogVisible.value = true
  isUpdate.value = true
  row.kategoriId = 3;
  urunDto.value = row
  console.log(row)
}

const remove = async (id) => {
  let result = await productService.deleteById(id)
      .then(response => response.data)
      .catch((error) => error.response.data)

  if (!result.success) {
    errorResponse(result.message)
    return;
  }
  successResponse(result.message)
  findAllProduct();
}

const filterTableData = computed(() =>
    tableData.value.filter(
        (data) => !search.value
            || data.urunAdi.toLowerCase()
                .includes(search.value.toLowerCase())
    )
)


</script>

<style scoped>
.dialog-footer button:first-child {
  margin-right: 10px;
}
</style>