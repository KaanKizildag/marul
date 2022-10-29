<template>
  <div class="row">
    <ProductList/>
    <ProductSummary/>
  </div>

</template>

<script setup>
import { onMounted, ref} from 'vue'
import ProductList from "./ProductList.vue";
import ProductSummary from "./ProductSummary.vue";

const emits = defineEmits(["pageName"])

onMounted(()=>{
  emits("pageName","ÜRÜN")
})

</script>
