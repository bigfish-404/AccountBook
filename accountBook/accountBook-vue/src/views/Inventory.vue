<template>
  <div class="container">
    <!-- 搜索 -->
    <div class="search">
      <input
          v-model="searchKeyword"
          type="text"
          placeholder="Search..."
          class="border"
      />
      <button @click="performSearch" class="search-btn">
        検索
      </button>
    </div>

    <!-- 商品表格 -->
    <table class="w-table">
      <thead class="bg-gray">
      <tr>
        <th>ID</th>
        <!-- 担当者 -->
        <th>
          <select v-model="selectedAssignee" class="dropdown-select">
            <option value="">担当者</option>
            <option v-for="a in assigneeOptions" :key="a" :value="a">{{ a }}</option>
          </select>
        </th>


        <!-- カテゴリ -->
        <th>
          <select v-model="selectedCategory" class="dropdown-select">
            <option value="">カテゴリ</option>
            <option v-for="a in categoryOptions" :key="a" :value="a">{{ a }}</option>
          </select>
        </th>

        <!-- 在庫数 -->
        <th>
          <select v-model="selectedStock" class="dropdown-select">
            <option value="">在庫数</option>
            <option v-for="a in stockOptions" :key="a" :value="a">{{ a }}</option>
          </select>
        </th>
        <th>販売した数</th>
        <th>価格</th>
        <th>販売した金額</th>
        <th>仕入価格</th>
        <th>新規入庫数</th>
        <th>アクション</th>
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
        <td class="border px-3 py-2">{{ item.category }}</td>
        <td class="border px-3 py-2">{{ item.quantity }}</td>
        <td class="border px-3 py-2">{{ item.quantity }}</td>
        <td class="border px-3 py-2">{{ item.quantity }}</td>
        <td class="border px-3 py-2">{{ item.quantity }}</td>
        <td class="border px-3 py-2">{{ item.createdAt }}</td>
        <!-- 新規入庫数的输入框 -->
        <td class="border px-3 py-2">
          <input
              v-model.number="item.newStock"
              type="number"
              placeholder="新規入庫数"
              class="custom-cell"
          />
        </td>
        <!-- 提交按钮 -->
        <td class="border px-3 py-2">
          <button
              @click="submitStockUpdate(item.id)"
              class="bg-blue-500 text-white px-4 py-1 rounded hover:bg-blue-600"
          >
            提交
          </button>
        </td>
      </tr>
      </tbody>
    </table>

    <!-- 商品追加ボタン -->
    <button @click="openDialog" class="bg-blue-500 text-white px-4 py-1 rounded hover:bg-blue-600 mt-4">
      商品追加
    </button>

    <!-- 商品追加ダイアログ -->
    <div v-if="dialogOpen" class="fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center">
      <div class="bg-white p-6 rounded shadow w-96">
        <h2 class="text-lg font-bold mb-4">新規商品追加</h2>
        <div class="space-y-2">
          <input v-model="newItem.name" placeholder="担当者名" class="border px-2 py-1 w-full" />
          <input v-model="newItem.category" placeholder="カテゴリ" class="border px-2 py-1 w-full" />
          <input v-model.number="newItem.quantity" type="number" placeholder="在庫数" class="border px-2 py-1 w-full" />
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
import '../assets/css/inventory.css'

// 商品リスト
const items = ref([
  { id: 1, name: '佐藤', category: '電子機器', quantity: 10, createdAt: '2025', newStock: 0 },
  { id: 2, name: '田中', category: 'アクセサリ', quantity: 100, createdAt: '2025', newStock: 0 },
  { id: 3, name: '佐藤', category: '電子機器', quantity: 50, createdAt: '2025', newStock: 0 },
])

// 筛选和搜索字段
const searchKeyword = ref('')
const selectedAssignee = ref('')
const selectedCategory = ref('')
const selectedStock = ref('')

// 三角下拉框的显示状态
const showFilter = ref({
  assignee: false,
  category: false,
  stock: false
})

const toggleFilter = (key) => {
  showFilter.value[key] = !showFilter.value[key]
  for (const k in showFilter.value) {
    if (k !== key) showFilter.value[k] = false
  }
}

const closeFilter = (key) => {
  showFilter.value[key] = false
}

// 下拉选项
const assigneeOptions = computed(() => [...new Set(items.value.map(item => item.name))])
const categoryOptions = computed(() => [...new Set(items.value.map(item => item.category))])
const stockOptions = computed(() => ['0~20', '21~50', '51+'])

// 过滤逻辑
const filteredItems = computed(() =>
    items.value.filter(item =>
        item.name.includes(searchKeyword.value) &&
        (selectedAssignee.value === '' || item.name === selectedAssignee.value) &&
        (selectedCategory.value === '' || item.category === selectedCategory.value) &&
        (selectedStock.value === '' ||
            (selectedStock.value === '0~20' && item.quantity <= 20) ||
            (selectedStock.value === '21~50' && item.quantity > 20 && item.quantity <= 50) ||
            (selectedStock.value === '51+' && item.quantity > 50))
    )
)

// 添加商品
const dialogOpen = ref(false)
const newItem = ref({ name: '', quantity: 0, category: '' })

const openDialog = () => {
  dialogOpen.value = true
  newItem.value = { name: '', quantity: 0, category: '' }
}

const addItem = () => {
  if (!newItem.value.name || !newItem.value.category || newItem.value.quantity <= 0) return
  const newId = items.value.length ? Math.max(...items.value.map(i => i.id)) + 1 : 1
  items.value.push({
    id: newId,
    name: newItem.value.name,
    category: newItem.value.category,
    quantity: newItem.value.quantity,
    createdAt: new Date().toISOString().slice(0, 10),
    newStock: 0,
  })
  dialogOpen.value = false
}

// 删除商品
const removeItem = (id) => {
  items.value = items.value.filter(item => item.id !== id)
}

// 提交库存更新
const submitStockUpdate = (id) => {
  const item = items.value.find(i => i.id === id)
  if (item && item.newStock !== 0) {
    item.quantity += item.newStock
    item.newStock = 0 // 更新库存后清空输入框
  }
}

// 搜索功能
const performSearch = () => {}
</script>
