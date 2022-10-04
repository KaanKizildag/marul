<template>
  <div class="col-md-7 mt-4">
    <div class="card">
      <div class="card-header pb-0 px-3">
        <h6 class="mb-0">Ürün Listesi</h6> <br/>
        <el-button type="primary" plain @click="addProduct">Ekle</el-button>

        <Dialog :dialog-visible="dialogVisible" :title="'Ürün Ekle'" @open="open" >
          <div class="input-group input-group-outline mb-3">
            <input type="text" v-model="urunDto.urunAdi" class="form-control" placeholder="Ürün Adı">
          </div>
          <div class="input-group input-group-outline mb-3">
            <input type="text" v-model="urunDto.fiyat" class="form-control" placeholder="Ürün Fiyat">
          </div>
          <div class="input-group input-group-outline mb-3">
            <input type="text" v-model="urunDto.kdv" class="form-control" placeholder="Kdv">
          </div>
          <el-select v-model="urunDto.kategoriId" filterable placeholder="Select">
            <el-option
                v-for="item in options"
                :key="item.value"
                :label="item.label"
                :value="item.value"
            />
          </el-select>

          <template #buttons>
            <el-button @click="dialogVisible = false">Vazgeç</el-button>
            <el-button type="primary" @click="save"
            >Keydet</el-button
            >
          </template>
        </Dialog>

      </div>
      <div class="card-body pt-4 p-3">
        <el-table :data="filterTableData" style="width: 100%">
          <el-table-column label="Date" prop="date"/>
          <el-table-column label="Name" prop="name"/>
          <el-table-column align="right">
            <template #header>
              <el-input v-model="search" size="small" placeholder="Type to search"/>
            </template>
            <template #default="scope">
              <a class="btn btn-link text-success px-3 mb-0" @click="update(scope.row)"><i
                  class="material-icons text-sm me-2" @click="update">edit</i>Güncelle</a>
              <a class="btn btn-link text-danger text-gradient px-3 mb-0">
                <i class="material-icons text-sm me-2" @click="remove(scope.row)">delete</i>Sil</a>
            </template>
          </el-table-column>
        </el-table>
      </div>
    </div>
  </div>
</template>

<script setup>
import {computed, ref} from "vue";
import Dialog from "../common/Dialog.vue";
import axios from "axios"

const dialogVisible = ref(false)
const isUpdate = ref(false)

const urunDto = ref({
  urunAdi:"",
  fiyat:"",
  kdv:"",

})

const findAllProduct = () => {
  axios.get("http://localhost:8084/satis-service/v1/urun/findAll")
      .then(response => console.log(response.data))
}

findAllProduct();

function open() {
  console.log('opennn')
}

function addProduct() {
  dialogVisible.value = true
  isUpdate.value = false
}

function save() {
  dialogVisible.value = false
  console.log(urunDto.value)
  urunDto.value = {
    urunAdi:"",
    fiyat:"",
    kdv:"",
    kategoriId: null,
  }
}function cancel() {
  dialogVisible.value = false
  urunDto.value = {
    urunAdi:"",
    fiyat:"",
    kdv:"",

  }
}


function update(row) {
  dialogVisible.value = true
  urunDto.value = {
    urunAdi:"Marul",
    fiyat: 13,
    kdv: 18,
    kategoriId: 1
  }
  console.log(row)
}
function remove(row) {
  console.log(row)
}

const search = ref('')
const filterTableData = computed(() =>
    tableData.filter(
        (data) =>
            !search.value ||
            data.name.toLowerCase().includes(search.value.toLowerCase())
    )
)

const tableData = [
  {
    date: '2016-05-03',
    name: 'Tom',
    address: 'No. 189, Grove St, Los Angeles',
  },
  {
    date: '2016-05-02',
    name: 'John',
    address: 'No. 189, Grove St, Los Angeles',
  },
  {
    date: '2016-05-04',
    name: 'Morgan',
    address: 'No. 189, Grove St, Los Angeles',
  },
  {
    date: '2016-05-01',
    name: 'Jessy',
    address: 'No. 189, Grove St, Los Angeles',
  },
]
const value = ref('')
const options = [
  {
    value: 1,
    label: 'Elektronik',
  },
  {
    value: 2,
    label: 'Yiyecek',
  },
  {
    value: 3,
    label: 'Kırtasiye',
  },
  {
    value: 4,
    label: 'Diğer',
  },
]
</script>

<style scoped>
.dialog-footer button:first-child {
  margin-right: 10px;
}
</style>