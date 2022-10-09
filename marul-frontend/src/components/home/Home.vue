<template>
  <div class="container-fluid py-4">
    <div class="row">
      <TopMiniCard :icon="'person'" :color="'primary'"/>
      <TopMiniCard :icon="'weekend'" :color="'success'"/>
      <TopMiniCard :icon="'person'" :color="'success'"/>
      <TopMiniCard :icon="'weekend'" :color="'primary'"/>
    </div>

    <div class="row mt-4">
      <ChartCard :id="'chart1'" :data="chartData" :label="'Satış'" :labels="chartLabels" :title="'Haftalık Satış'"
                 :description="'Satış Açıklama'" :subDescription="'campaign sent 2 days ago'" :type="'success'"/>
      <ChartCard :id="'chart2'" :data="chartData" :label="'Satış'" :labels="chartLabels" :title="'Aylık Satış'"
                 :description="'Satış Açıklama'" :subDescription="'campaign sent 2 days ago'" :type="'primary'"/>
      <ChartCard :id="'chart3'" :data="chartData" :label="'Satış'" :labels="chartLabels" :title="'Yıllık Satış'"
                 :description="'Satış Açıklama'" :subDescription="'campaign sent 2 days ago'" :type="'dark'"/>
    </div>

    <div class="row mb-4">
      <div class="col-lg-8 col-md-6 mb-md-0 mb-4">
        <!--        <HomeMainCard/>-->
      </div>
      <!--      <HomeTimeLine/>-->
    </div>
  </div>

</template>

<script setup>
import TopMiniCard from "./TopMiniCard.vue"
import ChartCard from "./ChartCard.vue"
import {onMounted, reactive, ref} from "vue";
import {ProductService} from "../../services/ProductService.js";

const emits = defineEmits(["pageName"])
const productService = new ProductService();
const chartData = ref()
const chartLabels = ref()
let haftalikSatislar = reactive({});

const haftalikSatislariGetir = async () => {
  const result = await productService.haftalikSatislariGetir()
      .then(response => response.data.data);
  return result;
}

onMounted(async () => {
  emits("pageName", "ANASAYFA");
  haftalikSatislar = await haftalikSatislariGetir();
  console.log("haftalikSatislar: " + haftalikSatislar)
  chartData.value = Object.values(haftalikSatislar);
  chartLabels.value = Object.keys(haftalikSatislar);
})


</script>

<style scoped>

</style>