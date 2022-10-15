<template>
  <el-table :data="data" style="width: 100%">
    <slot></slot>
  </el-table>
</template>

<script setup>
import {defineProps} from "vue";

const props = defineProps(["data"]);

</script>

<style scoped>

</style>