<template>
  <div class="register-container">
    <h1>新規登録</h1>
    <div class="form-group">
      <input type="text" placeholder="ユーザー名" v-model="username" />
    </div>
    <div class="form-group">
      <input
          type="password"
          placeholder="パスワード"
          v-model="password"
      />
    </div>
    <div class="form-group">
      <input
          type="password"
          placeholder="パスワードをもう一度入力"
          v-model="confirmPassword"
      />
    </div>
    <div class="button-group">
      <button class="register-button" @click="handleRegister">登録</button>
    </div>
  </div>
</template>

<script>
import '../assets/css/register.css';
import axios from 'axios';

export default {
  data() {
    return {
      username: "",
      password: "",
      confirmPassword: "",
    };
  },
  methods: {
    handleRegister() {
      if (this.username && this.password && this.confirmPassword) {
        if (this.password === this.confirmPassword) {
          axios
              .post("http://localhost:2025/register", {
                username: this.username,
                password: this.password,
              })
              .then(response => {
                if (response.data.success) {
                  alert(`登録が成功しました, ${this.username} さん！`);
                  this.$router.push('/admin');
                } else {
                  alert("登録に失敗しました！" + response.data.message);
                }
              })
              .catch(error => {
                console.error("エラーが発生しました:", error);
                alert("登録中にエラーが発生しました！");
              });
        } else {
          alert("パスワードが一致しません！");
        }
      } else {
        alert("ユーザー名とパスワードを入力してください！");
      }
    },
  },
};
</script>
