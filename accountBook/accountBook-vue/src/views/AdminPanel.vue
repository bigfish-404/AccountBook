<template>
  <div class="p-6 max-w-6xl mx-auto">
    <!-- Tabs -->
    <div class="tabs-container">
      <button
          v-for="tab in tabs"
          :key="tab"
          @click="activeTab = tab"
          :class="[
          'tab-button',
          activeTab === tab
            ? 'active-tab'
            : 'inactive-tab'
        ]"
      >
        {{ tab }}
      </button>

      <!--title-->
      <div class="title">
        <p class="title-size">AccountBook</p>
      </div>

    </div>

    <!-- 标签内容 -->
    <div v-if="activeTab === '在庫管理'">
      <Inventory />
    </div>
    <div v-else-if="activeTab === 'メルカリ'">
      <Mercari />
    </div>
    <div v-else-if="activeTab === 'Yahooフリマ'">
      <Furima />
    </div>
  </div>
</template>

<script setup>

import { ref } from 'vue'

import Inventory from './Inventory.vue'
import Mercari from './Mercari.vue'
import Furima from './Furima.vue'

import '../assets/css/admin.css';

const tabs = [ 'メルカリ', 'Yahooフリマ','在庫管理']
const activeTab = ref('在庫管理')

</script>

