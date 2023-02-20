<template>
    <div class="mt-5 mb-3 container">
        <h5 class="text-center">Ajouter un nouvel utilisateur</h5>
        <div class="row justify-content-center">
            <div class="col-md-6">
          <div class="card p-3">
            <div v-if="errors" class="alert alert-danger text-center">
            {{ errors }}
            </div>
            <form @submit.prevent="addUser">
              <div class="mb-3">
                <label class="form-label">Nom</label>
                <input v-model="form.firstname" type="text" class="form-control">
              </div>
              <div class="mb-3">
                <label class="form-label">Prénom</label>
                <input v-model="form.lastname" type="text" class="form-control">
              </div>
              <div class="mb-3">
                <label class="form-label">Email</label>
                <input v-model="form.email" type="email" class="form-control">
              </div>
              <div class="mb-3">
                <label class="form-label">Role</label>
                <select name="role" v-model="form.role" class="form-select">
                    <option value="ADMIN">ADMIN</option>
                    <option value="USER">USER</option>
                </select>
              </div>
              <div class="mb-3">
                <label class="form-label">Mot de passe </label>
                <input v-model="form.password" type="password" class="form-control">
              </div>
              <div class="mb-3">
                <label class="form-label">Confirmer mot de passe</label>
                <input v-model="confirm_password" type="password" class="form-control">
              </div>
             
              <button type="submit" class="btn btn-success">Enregistrer</button>
            </form>
          </div>
        </div>
        </div>
    </div>
</template>
  
<script>
import axios from 'axios'

export default {
    data() {
        return {
            form: {
                firstname: '',
                lastname: '',
                email: '',
                role: '',
                password: '',
        },
        confirm_password: '',
        errors: ''
        }
    },
    mounted() {
      if(!localStorage.getItem('token')) {
                this.$router.push({ name: "LoginPage" })
        }
    },
   methods: {
      addUser() {
        const config =  { headers: { Authorization: `Bearer ${localStorage.getItem('token')}`  }}
        if(this.form.password !== this.confirm_password) {
        this.errors = "Veillez confirmer le mot de passe !"
      } else {
        axios.post('http://localhost:8080/api/v1/auth/createUser',this.form, config)
        .then(() => {
            this.$router.push({ name: "HomePage", params: { message: "Utilisateur crée avec succés" } })
        }, err => {
          console.log(err.response)
          this.errors = "Un utilisateur avec les memes informaions existe déja !"
        })
    }
      },

   }
}
</script>