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
      <ChartCard :id="'chart2'" :data="chartData" :label="'Satış'" :labels="chartLabels" :title="'Haftalık Satış'"
                 :description="'Satış Açıklama'" :subDescription="'campaign sent 2 days ago'" :type="'primary'"/>
      <ChartCard :id="'chart3'" :data="chartData" :label="'Satış'" :labels="chartLabels" :title="'Haftalık Satış'"
                 :description="'Satış Açıklama'" :subDescription="'campaign sent 2 days ago'" :type="'dark'"/>
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
import ChartCard from "./ChartCard.vue"
import HomeMainCard from "./HomeMainCard.vue"
import HomeTimeLine from "./HomeTimeLine.vue"
import {onMounted, ref} from "vue";

const emits = defineEmits(["pageName"])

onMounted(() => {
  emits("pageName", "ANASAYFA")
})
const chartData = ref()
const chartLabels = ref()
chartData.value = [50, 20, 10, 22, 50, 10, 40]
chartLabels.value = ["M", "T", "W", "T", "F", "S", "S"]
</script>

<style scoped>

</style>