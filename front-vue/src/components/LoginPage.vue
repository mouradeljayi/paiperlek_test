<template>
  
    <div>
      <NavBar/>
      <h5 class="text-center">Bonjour, Prière de se connecter à votre compte !</h5>
      <div class="d-flex justify-content-center mt-5 mb-5">
        <div class="col-md-6">
          <div class="card p-3">
            <div v-if="errors" class="alert alert-danger ">
            {{ errors }}
            </div>
            <form @submit.prevent="login">
                <div class="mb-3">
                    <label class="form-label">Adresse e-mail</label>
                    <input v-model="email" type="email" class="form-control">
                </div>
                <div class="mb-3">
                    <label class="form-label">Mot de passe</label>
                    <input v-model="password" type="password" class="form-control">
                </div>
                <button type="submit" class="btn btn-primary">Se connectez</button>
                </form>
          </div>
        </div>
      </div>
    </div>
    
  </template>
  
  <script>
  import NavBar from './NavBar.vue'
  import axios from 'axios'
  export default {
    name: 'LoginPage',
    components: {NavBar},
    data() {
      return {
        email: '',
        password: '',
        errors: ''
      }
    },
    created() {
      if(localStorage.getItem('token')) {
          this.$router.push({ name: 'HomePage' })
      }
    },
    methods: {
      login() {
        let user = {
          email: this.email,
          password: this.password
        }
        axios.post('http://localhost:8080/api/v1/auth/authenticate', user)
        .then((res) => {
          if(res.status === 200) {
            localStorage.setItem('token', res.data.token)
            this.$router.push({ name: "HomePage" })
          }
        }, err => {
          console.log(err.response);
          console.log(user)
          this.errors = err.response.data.message
        }) 
      }
    }
  }
  </script>
  
  <style scoped>
  </style>