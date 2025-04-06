<template>
  <div class="login-container">
    <h1 class="title">ログインへようこそ</h1>
    <div class="form-group">
      <input type="text" placeholder="ユーザー名" v-model="username" />
    </div>
    <div class="form-group">
      <input type="password" placeholder="パスワード" v-model="password" />
    </div>
    <div class="button-group">
      <button class="login-button" @click="handleLogin">ログイン</button>
      <button class="register-button" @click="handleRegister">登録</button>
    </div>
  </div>
</template>

<script>
import '../assets/css/login.css';
import axios from 'axios';

export default {
  data() {
    return {
      username: "",
      password: "",
    };
  },
  methods: {
    handleLogin() {
      if (this.username && this.password) {
        axios
            .post('http://localhost:2025/login',{
              username: this.username,
              password: this.password,
        })
        .then(response =>{
          if (response.data.success) {
            this.$router.push('/adminPanel');
          }else {
            alert("ユーザー名またはパスワードが正しくありません！");
          }
        })
        .catch(error =>{
          console.error("エラーが発生しました:", error);
          alert("ログイン処理中にエラーが発生しました！")
        });
      } else {
        alert("ユーザー名とパスワードを入力してください！");
      }
    },
    handleRegister() {
      this.$router.push('/register')
    },
  },
};
</script>
