<script setup>
import Sidebar from "../src/components/Sidebar.vue"
import Navbar from "../src/components/Navbar.vue"
import { ref } from "vue";
const pageName = ref("")
</script>

<template>
  <Sidebar :activePage="pageName"/>
  <main class="main-content position-relative max-height-vh-100 h-100 border-radius-lg ">
    <Navbar :pageName="pageName"/>
    <router-view @pageName="pageName = $event"> </router-view>
  </main>
</template>

<style scoped>

</style>
