<template>
  <div class="row">
    <ProductList/>
    <ProductSummary/>
  </div>

</template>

<script setup>
import {computed, onMounted, ref} from 'vue'
import ProductList from "./ProductList.vue";
import {ProductService} from "../../services/ProductService.js";
import ProductSummary from "./ProductSummary.vue";
const emits = defineEmits(["pageName"])
onMounted(()=>{
  emits("pageName","ÜRÜN")
})

const search = ref('')
const productService = new ProductService();
const filterTableData = computed(() => {

      console.log(search)
      console.log(filterTableData)
      return tableData.filter(
          (data) =>
              !search.value ||
              data.name.toLowerCase().includes(search.value.toLowerCase())
      )
    }
)
const haftalikSatis = ref([])
onMounted(async () => {
  let result = await productService.haftalikSatislariGetir().then(resp => resp.data.data)
  haftalikSatis.value = Object.entries(result)
})
const handleEdit = (index, row) => {
  console.log(index, row)
}
const handleDelete = (index, row) => {
  console.log(index, row)
}

const tableData = [
  {
    date: '2016-05-03',
    name: 'Tom',
    address: 'No. 189, Grove St, Los Angeles',
  },
  {
    date: '2016-05-02',
    name: 'John',
    address: 'No. 189, Grove St, Los Angeles',
  },
  {
    date: '2016-05-04',
    name: 'Morgan',
    address: 'No. 189, Grove St, Los Angeles',
  },
  {
    date: '2016-05-01',
    name: 'Jessy',
    address: 'No. 189, Grove St, Los Angeles',
  },
]
</script>
