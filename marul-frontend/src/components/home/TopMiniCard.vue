<template>
  <div class="col-xl-3 col-sm-6 mb-xl-0 mb-4">
    <div class="card">
      <div class="card-header p-3 pt-2">
        <div :class="cardClass">
          <i class="material-icons opacity-10">{{ props.icon }}</i>
        </div>
        <div class="text-end pt-1">
          <p class="text-sm mb-0 text-capitalize">{{ props.title }}</p>
          <h4 class="mb-0">{{ props.price }}</h4>
        </div>
      </div>
      <hr class="dark horizontal my-0">
      <div class="card-footer p-3">
        <p class="mb-0">{{ props.aciklama }} <span class="text-success text-sm font-weight-bolder"> {{
            props.miktar
          }} </span></p>
      </div>
    </div>
  </div>
</template>

<script setup>
import {computed} from "vue";

const props = defineProps(["icon", "color", "aciklama", "miktar"])

const cardClass = computed(() => {
  return `icon icon-lg icon-shape bg-gradient-${props.color} shadow-${props.color} text-center border-radius-xl mt-n4 position-absolute`;
})
</script>

<style scoped>

</style>