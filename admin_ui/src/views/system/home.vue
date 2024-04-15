<template>
  <side-bar></side-bar>
  <div class="header_content">
    <header-bar></header-bar>
    <Tags></Tags>
    <div class="content_body">
      <router-view v-slot="{ Component }">
        <transition name="move" mode="out-in">
          <component :is="Component"></component>
        </transition>
      </router-view>
    </div>
  </div>

</template>

<script setup>
import HeaderBar from "./headerBar.vue";
import SideBar from "./sideBar.vue";
import Tags from '@/components/tags.vue';

</script>

<style scoped>
.header_content{
  position: relative;
  width: 100%;
  height: 100%;
  right: 0;
  top: 0px;
  bottom: 0;
  padding-bottom: 30px;
  /*background: #f0f0f0;*/

}
.content_body{
  height: 100%;
  margin: 10px;
}

</style>