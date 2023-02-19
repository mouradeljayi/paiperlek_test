<template>
    <div>
      <NavBar/>
      <h5 class="text-center">Créer un nouveau compte</h5>
      <div class="row justify-content-center mt-4 mb-4">
        <div class="col-md-6">
          <div class="card p-3">
            <div v-if="errors" class="alert alert-danger text-center">
            {{ errors }}
            </div>
            <form @submit.prevent="signUp">
              <div class="mb-3">
                <label class="form-label">Nom</label>
                <input v-model="form.firstname" type="text" class="form-control">
              </div>
              <div class="mb-3">
                <label class="form-label">Prénom</label>
                <input v-model="form.lastname" type="text" class="form-control">
              </div>
              <div class="mb-3">
                <label class="form-label">Adresse e-mail</label>
                <input v-model="form.email" type="email" class="form-control">
              </div>
              <div class="mb-3">
                <label class="form-label">Mot de passe</label>
                <input v-model="form.password" type="password" class="form-control">
              </div>
              <div class="mb-3">
                <label class="form-label">Confirmez le mot de passe</label>
                <input v-model="form.password_confirmation" type="password" class="form-control" >
              </div>
              <button type="submit" class="btn btn-warning">Enregistrer</button>
              <div class="mt-3 ">
                <p>Vous avez déja un compte ?  <router-link :to="{ name:'LoginPage' }">Se connectez à votre compte</router-link> </p>
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>
  </template>
  
  <script>
  import axios from 'axios'
  import NavBar from './NavBar.vue'
  export default {
    name: 'RegisterPage',
    components: {NavBar},
    data() {
      return {
        form: {
          firstname: '',
          lastname: '',
          email: '',
          password: '',
          password_confirmation: ''
        },
        errors: ""
      }
    },
    created() {
      if(localStorage.getItem('token')) {
          this.$router.push({ name: 'HomePage' })
      }
    },
    methods: {
     signUp() {
      if(this.form.password !== this.form.password_confirmation) {
        this.errors = "Veillez confirmer votre mot de passe !"
      } else {
          axios.post('http://localhost:8080/api/v1/auth/register', this.form)
        .then(() => {
          this.$router.push("/")
        }, err => {
          console.log(err.response)
          this.errors = err.response.data.message
        })
      }
      
     }
    }
  }
  </script>
  
  <style scoped>
  </style>