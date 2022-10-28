<template>
  <el-select v-model="limit" @change="changeValue" class="m-2" size="small">
    <el-option
        v-for="item in options"
        :key="item"
        :label="`${item} tane listele`"
        :value="item"
    />
  </el-select>
</template>

<script setup>
import {ref} from "vue";

const emits = defineEmits(["stockLimit"])
const limit = ref(5)
const options = ref([5, 10, 15, 20])

const changeValue = () => {
  emits("stockLimit", limit.value)
}
</script>

<style scoped>

</style>