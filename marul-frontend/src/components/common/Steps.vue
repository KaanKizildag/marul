<template>
  <el-steps :active="active" finish-status="success" simple style="margin-top: 20px">
    <el-step title="Müşteri Seç" ></el-step>
    <el-step title="Satış Yap" ></el-step>
  </el-steps>
  <el-button style="margin-top: 12px;" @click="nextStep">Next step</el-button>
</template>

<script setup>
import {ref} from "vue";

const active = ref(0)
const nextStep = () => {
  if (active.value++ > 1) active.value = 0;
}
</script>

<style scoped>

</style>