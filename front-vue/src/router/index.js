import Vue from 'vue'
import VueRouter from 'vue-router'
import LoginPage from '@/components/LoginPage'
import RegisterPage from '@/components/RegisterPage'
import HomePage from '@/components/HomePage'
import AddPage from '@/components/AddPage'
import PasswordGenerator from '@/components/PasswordGenerator'

Vue.use(VueRouter)

const routes = [
  {
        path: '/',
        component: LoginPage,
        name: 'LoginPage'
    },
    {
        path: '/register',
        component: RegisterPage,
        name: 'RegisterPage',
    }, 
    {
        path: '/home',
        component: HomePage,
        name: 'HomePage',
    }, 
    {
      path: '/add',
      component: AddPage,
      name: 'AddPage',
    },  
    {
      path: '/generatePassword',
      component: PasswordGenerator,
      name: 'generatePassword',
    },  
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
