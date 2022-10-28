<template>
  <div class="input-group input-group-outline mb-3">
    <input type="text" class="form-control" :placeholder="props.placeholder" @input="inputHandler">
  </div>
</template>

<script setup>
const props = defineProps(["placeholder", "value"])
const emits = defineEmits(["update:value"])

const inputHandler = (event) => {
  emits("update:value", event.target.value)
}
</script>

<style scoped>

</style>