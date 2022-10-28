<template>
  <div class="row">
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
                <el-button type="success" :icon="ArrowRightBold" circle/>
              </template>
            </el-table-column>
          </Table>
        </div>
      </div>
    </div>
    <SalesSummary/>
  </div>
</template>

<script setup>
import {computed, defineEmits, onMounted, ref} from "vue";
import {CustomerService} from "../../services/CustomerService.js";
import NotificationService from "../../services/NotificationService.js";
import Table from "../common/Table.vue";
import SalesSummary from "./SalesSummary.vue";
import {ArrowRightBold} from '@element-plus/icons-vue'


const search = ref('')
const tableData = ref([])

const customerService = new CustomerService();
const {successResponse, errorResponse} = NotificationService();


const emits = defineEmits(["pageName"])

const filterTableData = computed(() =>
    tableData.value
        .filter((data) => !search.value
            || data.musteriAdi.toLowerCase()
                .includes(search.value.toLowerCase())
        )
)
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

onMounted(() => {
  emits("pageName", "SATIŞ")
  findAllCustomers();

})


const update = (row) => {

}
const remove = (rowid) => {

}


</script>

<style scoped>

</style>