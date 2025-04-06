// src/router/index.js
import { createRouter, createWebHistory } from 'vue-router';
import Login from '../views/Login.vue';
import Register from "../views/Register.vue";
import Inventory from "../views/Inventory.vue";
import AdminPanel from "../views/AdminPanel.vue";

const routes = [
    {
        path: '/login',
        name: 'login',
        component: Login,
    },
    {
        path: '/register',
        component: Register,
    },
    {
        path: '/adminPanel',
        name: 'AdminPanel',
        component: AdminPanel,
    },
];

const router = createRouter({
    history: createWebHistory(),
    routes,
});

export default router;
