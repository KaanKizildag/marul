<template>
  <div class="col-lg-4 col-md-6 mt-4 mb-4">
    <div class="card z-index-2 ">
      <div class="card-header p-0 position-relative mt-n4 mx-3 z-index-2 bg-transparent">
        <div :class="chartCardClass">
          <div class="chart">
            <canvas :id="props.id" class="chart-canvas" height="170"></canvas>
          </div>
        </div>
      </div>
      <div class="card-body">
        <h6 class="mb-0 "> {{ props.title }}</h6>
        <p class="text-sm "> {{ props.description }}</p>
        <hr class="dark horizontal">
        <div class="d-flex ">
          <i class="material-icons text-sm my-auto me-1">schedule</i>
          <p class="mb-0 text-sm">{{ props.subDescription }}  </p>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
const props = defineProps(["id", "data", "label", "labels", "title", "description", "subDescription", "type", "chartType"])
import {computed, onMounted} from "vue";

const chartCardClass = computed(() => {
  return `bg-gradient-${props.type} shadow-${props.type} border-radius-lg py-3 pe-1`
})
const chartDataFucn = () => {
  var ctx = document.getElementById(props.id).getContext("2d");
  //const ctx = ref(null)
  new Chart(ctx, {
    type: props.chartType,
    data: {
      labels: props.labels,
      datasets: [{
        label: props.label,
        tension: 0,
        pointRadius: 5,
        pointBackgroundColor: "rgba(255, 255, 255, .8)",
        pointBorderColor: "transparent",
        borderColor: "rgba(255, 255, 255, .8)",
        borderWidth: 4,
        backgroundColor: "transparent",
        fill: true,
        data: props.data,
        maxBarThickness: 6

      }],
    },
    options: {
      responsive: true,
      maintainAspectRatio: false,
      plugins: {
        legend: {
          display: false,
        }
      },
      interaction: {
        intersect: false,
        mode: 'index',
      },
      scales: {
        y: {
          grid: {
            drawBorder: false,
            display: true,
            drawOnChartArea: true,
            drawTicks: false,
            borderDash: [5, 5],
            color: 'rgba(255, 255, 255, .2)'
          },
          ticks: {
            display: true,
            color: '#f8f9fa',
            padding: 10,
            font: {
              size: 14,
              weight: 300,
              family: "Roboto",
              style: 'normal',
              lineHeight: 2
            },
          }
        },
        x: {
          grid: {
            drawBorder: false,
            display: false,
            drawOnChartArea: false,
            drawTicks: false,
            borderDash: [5, 5]
          },
          ticks: {
            display: true,
            color: '#f8f9fa',
            padding: 10,
            font: {
              size: 14,
              weight: 300,
              family: "Roboto",
              style: 'normal',
              lineHeight: 2
            },
          }
        },
      },
    },
  });
}
onMounted(() => {
  setTimeout(chartDataFucn, 500);
})
</script>

<style scoped>

</style>