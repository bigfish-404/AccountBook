<template>
  <div class="container">
    <!-- 搜索 -->
    <div class="mb-4 flex items-center gap-2">
      <input
          v-model="searchKeyword"
          type="text"
          placeholder="商品名で検索"
          class="border rounded px-2 py-1 w-64"
      />

    </div>

    <!-- 商品表格 -->
    <table class="w-full border">
      <thead class="bg-gray-100">
      <tr>
        <th class="border px-3 py-2">ID</th>
        <th class="border px-3 py-2">商品名</th>
        <th class="border px-3 py-2">数量</th>
        <th class="border px-3 py-2">カテゴリ</th>
        <th class="border px-3 py-2">已售卖</th>
        <th class="border px-3 py-2">操作</th>
      </tr>
      </thead>
      <tbody>
      <tr
          v-for="item in filteredItems"
          :key="item.id"
          class="hover:bg-gray-50"
      >
        <td class="border px-3 py-2">{{ item.id }}</td>
        <td class="border px-3 py-2">{{ item.name }}</td>
        <td class="border px-3 py-2">{{ item.quantity }}</td>
        <td class="border px-3 py-2">{{ item.category }}</td>
        <td class="border px-3 py-2">{{ item.createdAt }}</td>
        <td class="border px-3 py-2">
          <button @click="removeItem(item.id)" class="text-red-600 hover:underline">削除</button>
        </td>
      </tr>
      </tbody>
    </table>

    <button @click="openDialog" class="bg-blue-500 text-white px-4 py-1 rounded hover:bg-blue-600">
      商品追加
    </button>
    <!-- 商品追加ダイアログ -->
    <div v-if="dialogOpen" class="fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center">
      <div class="bg-white p-6 rounded shadow w-96">
        <h2 class="text-lg font-bold mb-4">新規商品追加</h2>
        <div class="space-y-2">
          <input v-model="newItem.name" placeholder="商品名" class="border px-2 py-1 w-full" />
          <input v-model.number="newItem.quantity" type="number" placeholder="数量" class="border px-2 py-1 w-full" />
          <input v-model="newItem.category" placeholder="カテゴリ" class="border px-2 py-1 w-full" />
        </div>
        <div class="mt-4 flex justify-end gap-2">
          <button @click="dialogOpen = false" class="border px-3 py-1 rounded">キャンセル</button>
          <button @click="addItem" class="bg-blue-500 text-white px-4 py-1 rounded hover:bg-blue-600">追加</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, computed } from 'vue'

import '../assets/css/inventory.css';

// 模拟商品列表
const items = ref([
  { id: 1, name: 'ノートPC', quantity: 10, category: '電子機器', createdAt: '2025-04-05' },
  { id: 2, name: 'USBケーブル', quantity: 100, category: 'アクセサリ', createdAt: '2025-04-03' },
  { id: 3, name: 'マウス', quantity: 50, category: '電子機器', createdAt: '2025-04-02' },
])

// 搜索关键字
const searchKeyword = ref('')

// 过滤后的商品
const filteredItems = computed(() =>
    items.value.filter(item => item.name.includes(searchKeyword.value))
)

// 添加新商品
const dialogOpen = ref(false)
const newItem = ref({ name: '', quantity: 0, category: '' })

const openDialog = () => {
  dialogOpen.value = true
  newItem.value = { name: '', quantity: 0, category: '' }
}

const addItem = () => {
  if (!newItem.value.name || newItem.value.quantity <= 0) return

  const newId = items.value.length ? Math.max(...items.value.map(i => i.id)) + 1 : 1
  items.value.push({
    id: newId,
    name: newItem.value.name,
    quantity: newItem.value.quantity,
    category: newItem.value.category,
    createdAt: new Date().toISOString().slice(0, 10),
  })
  dialogOpen.value = false
}

// 删除商品
const removeItem = (id) => {
  items.value = items.value.filter(item => item.id !== id)
}
</script>

