<template>
  <div class="container-fluid py-4">
    <div class="row">
      <TopMiniCard :icon="'apps_outage'" :color="'primary'"/>
      <TopMiniCard :icon="'weekend'" :color="'success'"/>
      <TopMiniCard :icon="'person'" :color="'success'"/>
      <TopMiniCard :icon="'weekend'" :color="'primary'"/>
    </div>

    <div class="row mt-4">
      <ChartCard :id="'chart1'" :data="haftalikSatislarChartData" :label="'Satış'" :labels="haftalikSatislarChartLabels"
                 :title="'Haftalık Satış'"
                 :description="'Satış Açıklama'"
                 :subDescription="`${updatedAt} dakika önce güncellendi`"
                 :type="'success'"
                 :chart-type="'bar'"/>
      <ChartCard :id="'chart2'" :data="haftalikSatislarChartData" :label="'Satış'" :labels="haftalikSatislarChartLabels"
                 :title="'Aylık Satış'"
                 :description="'Satış Açıklama'"
                 :subDescription="`${updatedAt} dakika önce güncellendi`"
                 :type="'primary'"
                 :chart-type="'line'"/>
      <ChartCard :id="'chart3'" :data="haftalikSatislarChartData" :label="'Satış'" :labels="haftalikSatislarChartLabels"
                 :title="'En çok satış yapılan 7 müşteri'"
                 :description="'En çok satış yapılan 7 müşteri'"
                 :subDescription="`${updatedAt} dakika önce güncellendi`"
                 :type="'dark'"
                 :chart-type="'bar'"/>
    </div>

    <div class="row mb-4">
      <div class="col-lg-8 col-md-6 mb-md-0 mb-4">
        <HomeMainCard/>
      </div>
      <HomeTimeLine/>
    </div>
  </div>

</template>

<script setup>
import TopMiniCard from "./TopMiniCard.vue"
import HomeMainCard from "./HomeMainCard.vue"
import HomeTimeLine from "./HomeTimeLine.vue"
import ChartCard from "./ChartCard.vue"
import {onMounted, reactive, ref} from "vue";
import {ProductService} from "../../services/ProductService.js";
import NotificationService from "../../services/NotificationService.js";


const emits = defineEmits(["pageName"])
const productService = new ProductService();
const haftalikSatislarChartData = ref()
const haftalikSatislarChartLabels = ref()
let haftalikSatislar = reactive({});
const {errorResponse, successResponse} = NotificationService();

let updatedAt = ref(0)
const haftalikSatislariGetir = async () => {
  const result = await productService.haftalikSatislariGetir()
      .then(response => response.data);
  if (!result.success) {
    errorResponse(result.message);
  }
  return result.data;
}
setInterval(() => updatedAt.value++, 1000 * 60)

onMounted(async () => {
  emits("pageName", "ANASAYFA");
  haftalikSatislar = await haftalikSatislariGetir();
  haftalikSatislarChartData.value = Object.values(haftalikSatislar);
  haftalikSatislarChartLabels.value = Object.keys(haftalikSatislar);
})


</script>

<style scoped>

</style>