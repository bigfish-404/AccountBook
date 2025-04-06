<!-- src/App.vue -->
<!--App.vueファイルはVue.jsアプリケーションのルートコンポーネントとして、このファイルを通じてアプリ全体の基本構造とレイアウトが定義される-->
<template>
  <div id="app">
    <router-view></router-view>
  </div>
</template>

<script>
export default {
  name: 'App',
};
</script>

<style>
#app {
  text-align: center;
}
</style>
