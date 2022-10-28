<template>

  <div class="col-md-3 mt-4">
    <div class="card h-100 mb-4">
      <div class="card-header pb-0 px-3">
        <div class="row">
          <div class="col-md-6">
            <h6 class="mb-0">Son Satışlar</h6>
          </div>
          <li class="list-group-item border-0 d-flex justify-content-between ps-0 mb-2 border-radius-lg">

            <div class="col-md-6 d-flex justify-content-start justify-content-md-end align-items-center ">
              <LimitSelector @stockLimit="stockLimitHandler"/>
            </div>

          </li>
        </div>
      </div>
      <div class="card-body pt-4 p-3">
        <ul class="list-group">

          <li v-for="satis in satisList"
              class="list-group-item border-0 d-flex justify-content-between ps-0 mb-2 border-radius-lg">
            <div class="d-flex align-items-center">
              <button
                  class="btn btn-icon-only btn-rounded mb-0 me-3 p-3 btn-sm d-flex align-items-center justify-content-center btn-outline-success">
                <i class="material-icons text-lg">currency_lira</i>
              </button>
              <div class="d-flex flex-column">
                <h6 class="mb-1 text-dark text-sm">{{ satis.musteriAdi }}</h6>
                <span class="text-xs">{{ satis.urunAdi }}</span>
              </div>
            </div>
            <div class="d-flex align-items-center text-gradient text-sm font-weight-bold text-success">
              {{ "satış tutarı " + satis.satisTutari }}
            </div>
          </li>

        </ul>
      </div>
    </div>
  </div>

</template>
<script setup>

import SalesService from "../../services/SalesService.js"
import NotificationService from "../../services/NotificationService.js";
import {ref} from "vue";
import LimitSelector from "../common/LimitSelector.vue";

const {successResponse, errorResponse} = NotificationService();

const satisListAll = ref([]);
const satisList = ref([]);
const stockLimit = ref()

const stockLimitHandler = (limit) => {
  console.log(limit)
  stockLimit.value = limit;
  satisList.value = satisListAll.value.slice(0, limit);
}

const satisService = new SalesService();


const sonSatislariGetir = async () => {
  let result = await satisService.sonSatislariGetir()
      .then(response => response.data)
      .catch(error => error.response.data);

  if (!result.success) {
    errorResponse(result.message)
    return;
  }
  satisListAll.value = result.data;
  satisList.value = result.data.slice(0, 5);
  successResponse(result.message);
}

sonSatislariGetir();

</script>

<style scoped>

</style>