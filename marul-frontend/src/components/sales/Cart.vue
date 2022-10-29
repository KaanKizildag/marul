<template>
  <div class="col-md-3 mt-4">
    <div class="card h-100 mb-4">
      <div class="card-header pb-0 px-3">
        <h6 class="mb-0">Sepet</h6>
        <Table :data="saleArray"
               style="width:100%;">
          <el-table-column label="Ürün Adı" prop="urunAdi"/>
          <el-table-column label="Adet" prop="satilanAdet"/>
          <el-table-column label="Tutar" prop="tutar"/>
          <el-table-column align="right">
            <template #default="scope">
              <DeleteButton @click="removeFromCart(scope.row)"/>
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
import DeleteButton from "../common/DeleteButton.vue";
import {computed, ref} from "vue";

const props = defineProps(["saleArray"])
const emits = defineEmits(["removeFromCart"])

const removeFromCart = (row) => {
  emits("removeFromCart",row)
}
const total = ref(0)

const totalPrice = computed(()=> {
  total.value = 0;
  props.saleArray.forEach(sale => {
    total.value += sale.tutar;
  })
  return total.value;
})
</script>

<style scoped>

</style>