<template>
  <div class="row">

    <ProductList />
    <div class="col-md-5 mt-4">
      <div class="card h-100 mb-4">
        <div class="card-header pb-0 px-3">
          <div class="row">
            <div class="col-md-6">
              <h6 class="mb-0">HAFTALIK SATIŞLAR</h6>
            </div>
            <div class="col-md-6 d-flex justify-content-start justify-content-md-end align-items-center">
              <i class="material-icons me-2 text-lg">date_range</i>
              <small>01 - 07 Ekim 2022</small>
            </div>
          </div>
        </div>
        <div class="card-body pt-4 p-3">
          <ul class="list-group">

            <li v-for="item in haftalikSatis"
                class="list-group-item border-0 d-flex justify-content-between ps-0 mb-2 border-radius-lg">
              <div class="d-flex align-items-center">
                <button
                    class="btn btn-icon-only btn-rounded btn-outline-danger mb-0 me-3 p-3 btn-sm d-flex align-items-center justify-content-center">
                  <i class="material-icons text-lg">expand_more</i></button>
                <div class="d-flex flex-column">
                  <h6 class="mb-1 text-dark text-sm">{{ item[0].toUpperCase() }}</h6>
                  <span class="text-xs">27 March 2020, at 12:30 PM</span>
                </div>
              </div>
              <div class="d-flex align-items-center text-danger text-gradient text-sm font-weight-bold">
                - $ {{ item[1] }}
              </div>
            </li>

          </ul>
        </div>
      </div>
    </div>
    </div>

</template>

<script setup>
import {computed, onMounted, ref} from 'vue'
import ProductList from "./ProductList.vue";
import {ProductService} from "../../services/ProductService.js";

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
