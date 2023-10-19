import { createRouter, createWebHistory } from 'vue-router'
import Login from "@/views/Login.vue";
import Reset from "@/views/ResetPassword.vue";

import Dashboard from "@/views/Dashboard.vue";
import Profile from "@/views/Profile.vue";


const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '',
      name: 'Login',
      component: Login
    },
    {
      path: '/resetPassword',
      name: 'ResetPassword',
      component: Reset

    },
    {
      path: '/dashboard',
      name: 'dashboard',
      component: Dashboard
    },
    {
      path: '/profile',
      name: 'profile',
      component: Profile

    }
  ]
})

export default router
