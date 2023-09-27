import { createRouter, createWebHashHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import SignInView from "@/views/SignInView.vue";
import ResetPasswordView from "@/views/ResetPasswordView.vue";

const routes = [
  {
    path: '/home',
    name: 'home',
    component: HomeView
  },
  {
    path: '/about',
    name: 'about',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/AboutView.vue')
  },
  {
    path: '/',
    name: 'signin',
    component: SignInView
  },
  {
    path: '/resetPassword',
    name: 'resetPassword',
    component: ResetPasswordView
  }
]

const router = createRouter({
  history: createWebHashHistory(),
  routes
})

export default router
