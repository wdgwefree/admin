<template>
  <el-card class="box-card">
    <template #header>
      <div class="card-header">
        <span>个人信息</span>
      </div>
    </template>
    <div v-for="o in 4" :key="o" class="text item">{{ 'List item ' + o }}</div>
  </el-card>

</template>

<script setup>

</script>

<style scoped>
</style>