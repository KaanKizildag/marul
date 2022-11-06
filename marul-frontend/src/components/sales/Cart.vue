<template>
  <div class="col-md-3 mt-4">
    <div class="card h-100 mb-4">
      <div class="card-header pb-0 px-3">
        <h6 class="mb-0">Sepet</h6>
        <Table :data="cartArray"
               style="width:100%;">
          <el-table-column label="Ürün Adı" prop="urunAdi"/>
          <el-table-column label="Adet" align="center" >
            <template #default="scope">
              <el-input-number
                  v-model="scope.row.satilanAdet"
                  :min="0"
                  :max="1000000"
                  size="small"
                  @change="handleChange(scope.row)"
              />
            </template>
          </el-table-column>
          <el-table-column label="Tutar" prop="tutar">
            <template #default="scope">
              {{scope.row.satilanAdet * scope.row.tutar}}
            </template>
          </el-table-column>
        </Table>
        <strong>
          {{"Toplam Tutar: " + totalPrice}}
        </strong>
      </div>
    </div>
  </div>
</template>

<script setup>
import Table from "../common/Table.vue";
import {computed, ref} from "vue";

const props = defineProps(["cartArray"])
const emits = defineEmits(["changeProductCount"])

const total = ref(0)

const handleChange = (row) => {
  emits("changeProductCount",row)
}

const totalPrice = computed(()=> {
  total.value = 0;
  props.cartArray.forEach(sale => {
    total.value += sale.satilanAdet * sale.tutar;
  })
  return total.value;
})
</script>