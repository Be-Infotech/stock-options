import { createRouter, createWebHistory } from 'vue-router'
import Login from "@/views/Login.vue";
import Reset from "@/views/ResetPassword.vue";

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
    }
  ]
})

export default router
