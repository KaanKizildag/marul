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
                 :subDescription="`${Math.floor((Date.now() - guncellenmeZamani) / 1000)} saniye önce güncellendi`"
                 :type="'success'"/>
      <ChartCard :id="'chart2'" :data="haftalikSatislarChartData" :label="'Satış'" :labels="haftalikSatislarChartLabels"
                 :title="'Aylık Satış'"
                 :description="'Satış Açıklama'"
                 :subDescription="`${Math.floor((Date.now() - guncellenmeZamani) / 1000)} saniye önce güncellendi`"
                 :type="'primary'"/>
      <ChartCard :id="'chart3'" :data="haftalikSatislarChartData" :label="'Satış'" :labels="haftalikSatislarChartLabels"
                 :title="'Yıllık Satış'"
                 :description="'Satış Açıklama'"
                 :subDescription="`${Math.floor((Date.now() - guncellenmeZamani) / 1000)} saniye önce güncellendi`"
                 :type="'dark'"/>
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

const emits = defineEmits(["pageName"])
const productService = new ProductService();
const haftalikSatislarChartData = ref()
const haftalikSatislarChartLabels = ref()
let haftalikSatislar = reactive({});
let guncellenmeZamani = Date.now();

const haftalikSatislariGetir = async () => {
  const result = await productService.haftalikSatislariGetir()
      .then(response => response.data.data);
  return result;
}

onMounted(async () => {
  emits("pageName", "ANASAYFA");
  haftalikSatislar = await haftalikSatislariGetir();
  console.log("haftalikSatislar: " + haftalikSatislar)
  haftalikSatislarChartData.value = Object.values(haftalikSatislar);
  haftalikSatislarChartLabels.value = Object.keys(haftalikSatislar);
  guncellenmeZamani = Date.now();
})


</script>

<style scoped>

</style>