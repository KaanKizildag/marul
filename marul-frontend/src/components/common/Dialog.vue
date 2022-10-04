<template>
  <el-dialog
      v-model="dialogVisible"
      :title="title"
      :width="width"
      align-center
      :close-on-click-modal="false"
      :close-on-press-escape="false"
      :show-close="false"
      @open="emits('open')"
      @opened="emits('opened')"
      @close="emits('close')"
  >
    <slot v-if="dialogVisible"></slot>

    <template #footer>
    <slot name="buttons"></slot>

    </template>
  </el-dialog>
</template>

<script setup>

const props = defineProps({
  dialogVisible: {
    type: Boolean,
    default: false
  },
  isOpen: {
    type: Boolean,
    default: false
  },
  title: {
    type: String,
    required: true
  },
  width: {
    type: String,
    default: "30%"
  },
  center: {
    type: Boolean,
    required: false
  }
})

const emits = defineEmits(["open","close","opened"])

</script>

