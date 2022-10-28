<template>
  <div class="col-md-3 mt-4">
    <div class="card h-100 mb-4">
      <div class="card-header pb-0 px-3">
        <div class="row">
          <div class="col-md-6">
            <h6 class="mb-0">STOK AZALAN ÜRÜNLER</h6>
          </div>
          <div class="col-md-6 d-flex justify-content-start justify-content-md-end align-items-center">
            <LimitSelector @stockLimit="stockLimitHandler"/>
          </div>
        </div>
      </div>
      <div class="card-body pt-4 p-3">
        <ul class="list-group">

          <li v-for="stock in criticalStockList"
              class="list-group-item border-0 d-flex justify-content-between ps-0 mb-2 border-radius-lg">
            <div class="d-flex align-items-center">
              <button
                  class="btn btn-icon-only btn-rounded mb-0 me-3 p-3 btn-sm d-flex align-items-center justify-content-center"
                  :class="stock.kritikMi ? 'btn-outline-danger ': 'btn-outline-warning'">
                <i class="material-icons text-lg">{{ stock.kritikMi ? "priority_high" : "low_priority" }}</i></button>
              <div class="d-flex flex-column">
                <h6 class="mb-1 text-dark text-sm">{{ stock.urunAdi }}</h6>
                <span class="text-xs">{{ stock.kategoriAdi }}</span>
              </div>
            </div>
            <div class="d-flex align-items-center text-gradient text-sm font-weight-bold"
                 :class="stock.kritikMi ? 'text-danger': 'text-warning'">
              {{ "Kalan stok adeti: " + stock.adet }}
            </div>
          </li>

        </ul>
      </div>
    </div>
  </div>
</template>

<script setup>
import {onMounted, ref} from "vue";
import {StockService} from "../../services/StockService.js";
import NotificationService from "../../services/NotificationService.js"
import LimitSelector from "../common/LimitSelector.vue"

const criticalStockList = ref()
const criticalStockListAll = ref()

const stockService = new StockService();
const {errorResponse, successResponse} = NotificationService();
const getCriticalStockInfo = () => {
  return stockService.getCriticalStockInfo()
      .then(response => response.data)
      .catch(error => error.response.data);
}

onMounted(async () => {
  let result = await getCriticalStockInfo();

  if (!result.success) {
    errorResponse(result.message);
    return;
  }

  criticalStockListAll.value = result.data
  criticalStockList.value = result.data.slice(0, 5);
})
const stockLimit = ref()
const stockLimitHandler = (limit) => {
  console.log(limit)
  stockLimit.value = limit;
  criticalStockList.value = criticalStockListAll.value.slice(0, limit);
  console.log(criticalStockList.value);
}

</script>

<style scoped>

</style>