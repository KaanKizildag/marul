<template>
  <div class="container-fluid py-4">
    <div class="row">
      <TopMiniCard :icon="'currency_lira'" :color="'primary'" :aciklama="'Toplam kasa tutarı'"
                   :miktar="`${kasaToplamTutari} ₺`"/>
      <TopMiniCard :icon="'trending_up'" :color="'success'" :aciklama="'Satışı artan ürün'" :miktar="'Toz Biber'"/>
      <TopMiniCard :icon="'trending_down'" :color="'danger'" :aciklama="'Satışı azalan ürün'" :miktar="'Çay'"/>
      <TopMiniCard :icon="'apps_outage'" :color="'warning'" :aciklama="'Kar oranı'" :miktar="'45%'"/>
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
      <HomeTimeLine/>
    </div>
  </div>

</template>

<script setup>
import TopMiniCard from "./TopMiniCard.vue"
import HomeTimeLine from "./HomeTimeLine.vue"
import ChartCard from "./ChartCard.vue"
import {onMounted, reactive, ref} from "vue";
import {ProductService} from "../../services/ProductService.js";
import {KasaHareketiService} from "../../services/KasaHareketiService.js";
import NotificationService from "../../services/NotificationService.js";


const emits = defineEmits(["pageName"])
const productService = new ProductService();
const kasaHareketiService = new KasaHareketiService();
const haftalikSatislarChartData = ref()
const haftalikSatislarChartLabels = ref()
let haftalikSatislar = reactive({});
let kasaToplamTutari = ref(0);
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
const kasaToplamTutariGetir = async () => {
  const result = await kasaHareketiService.kasaToplamTutariGetir()
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
  kasaToplamTutari.value = await kasaToplamTutariGetir();
  haftalikSatislarChartData.value = Object.values(haftalikSatislar);
  haftalikSatislarChartLabels.value = Object.keys(haftalikSatislar);
})


</script>

<style scoped>

</style>