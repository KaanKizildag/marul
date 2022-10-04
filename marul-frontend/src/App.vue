<script setup>
import Sidebar from "../src/components/Sidebar.vue"

</script>

<template>
  <Sidebar />
  <main class="main-content position-relative max-height-vh-100 h-100 border-radius-lg ">
    <router-view></router-view>
  </main>
</template>

<style scoped>

</style>
