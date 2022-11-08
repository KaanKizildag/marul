<template>
  <div class="col-md-9 mt-4">
    <div class="card">
      <div class="card-header pb-0 px-3">
        <h6 class="mb-0">Müşteri Listesi</h6> <br/>
        <el-button type="primary" plain @click="addCustomer">Ekle</el-button>

        <Dialog :dialog-visible="dialogVisible" :title="'Müşteri Ekle'" @open="open">
          <div class="input-group input-group-outline mb-3">
            <input type="text" v-model="customerDto.musteriAdi" class="form-control" placeholder="Müşteri Adı">
          </div>
          <div class="input-group input-group-outline mb-3">
            <input type="text" v-model="customerDto.email" class="form-control" placeholder="E-Posta">
          </div>
          <div class="input-group input-group-outline mb-3">
            <input type="text" v-model="customerDto.telefonNo" class="form-control" placeholder="Telefon No">
          </div>
          <div class="input-group input-group-outline mb-3">
            <input type="text" v-model="customerDto.teslimatNoktasi" class="form-control" placeholder="Teslimat Noktası">
          </div>
          <div class="input-group input-group-outline mb-3">
            <input type="text" v-model="customerDto.borc" class="form-control" placeholder="Borç">
          </div>
          <el-select v-model="customerDto.turId" filterable placeholder="Select">
            <el-option
                v-for="item in tours"
                :key="item.id"
                :label="item.turAdi"
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
          <el-table-column label="Ad Soyad" prop="musteriAdi" :sortable="true"/>
          <el-table-column label="E-Posta" prop="email" :sortable="true"/>
          <el-table-column label="Telefon No" prop="telefonNo" :sortable="true"/>
          <el-table-column label="Borç" prop="borc" :sortable="true"/>
          <el-table-column label="Teslimat Noktası" prop="teslimatNoktasi" :sortable="true"/>
          <el-table-column label="Bölge" prop="turAdi" :sortable="true"/>
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
import {computed, onMounted, ref} from "vue";
import Dialog from "../common/Dialog.vue";
import {CustomerService} from "../../services/CustomerService.js"
import {TourService} from "../../services/TourService.js"
import NotificationService from "../../services/NotificationService.js";
import DeleteButton from "../common/DeleteButton.vue";
import EditButton from "../common/EditButton.vue";
import Table from "../common/Table.vue";


const customerService = new CustomerService();
const tourService = new TourService();

const {successResponse, errorResponse} = NotificationService();

const dialogVisible = ref(false)
const isUpdate = ref(false)
const tableData = ref([])
const tours = ref([]);
const search = ref('')

const customerDto = ref({
  musteriAdi: "",
  telefonNo: "",
  email: "",
  teslimatNoktasi: "",
  borc: "",
  turId: "",
})

onMounted(() => {
  findAllCustomer();
  findAllTours();
})

const findAllCustomer = () => {
  customerService.findAllCustomer().then(resp => tableData.value = resp.data.data)
}

const findAllTours = () => {
  tourService.findAll().then(resp => tours.value = resp.data.data)
}

const addCustomer = () => {
  dialogVisible.value = true
  isUpdate.value = false
}

const save = async () => {
  let result;
  if (!isUpdate.value) {
    result = await customerService.saveCustomer(customerDto.value)
        .then(response => response.data)
        .catch((error) => error.response.data)
  } else {
    result = await customerService.updateCustomer(customerDto.value)
        .then(response => response.data)
        .catch((error) => error.response.data)
  }

  if (!result.success) {
    errorResponse(result.message)
    return;
  }

  dialogVisible.value = false
  customerDto.value = {
    musteriAdi: "",
    telefonNo: "",
    email: "",
    teslimatNoktasi: "",
    turId: "",
  }
  successResponse(result.message)
  findAllCustomer();
}

const cancel = () => {
  dialogVisible.value = false
  urunDto.value = {
    musteriAdi: "",
    telefonNo: "",
    email: "",
    teslimatNoktasi: "",
    turId: "",
  }
}

const update = (row) => {
  dialogVisible.value = true
  isUpdate.value = true
  customerDto.value = row
}

const remove = async (id) => {
  let result = await customerService.deleteById(id)
      .then(response => response.data)
      .catch((error) => error.response.data)

  if (!result.success) {
    errorResponse(result.message)
    return;
  }
  successResponse(result.message)
  findAllCustomer();
}

const buttonText = computed(() => isUpdate.value ? "Güncelle" : "Kaydet")

const filterTableData = computed(() =>
    tableData.value.filter(
        (data) => !search.value
            || data.musteriAdi.toLowerCase()
                .includes(search.value.toLowerCase())
    )
)

</script>

<style scoped>
.dialog-footer button:first-child {
  margin-right: 10px;
}
</style>