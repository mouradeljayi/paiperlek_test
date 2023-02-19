<template>
    <div class="mt-5 container">
        <h5 class="text-center">Ajouter un nouveau personnel</h5>
        <div class="row justify-content-center">
            <div class="col-md-6">
          <div class="card p-3">
            
            <form @submit.prevent="addPersonnel">
              <div class="mb-3">
                <label class="form-label">Nom</label>
                <input v-model="form.nom" type="text" class="form-control">
              </div>
              <div class="mb-3">
                <label class="form-label">Prénom</label>
                <input v-model="form.prenom" type="text" class="form-control">
              </div>
              <div class="mb-3">
                <label class="form-label">Salaire</label>
                <input v-model="form.salaire" type="text" class="form-control">
              </div>
              <div class="mb-3">
                <label class="form-label">Service</label>
                
                <input v-model="form.service" type="text" class="form-control">
              </div>
             
              <button type="submit" class="btn btn-warning">Enregistrer</button>
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
                nom: '',
                prenom: '',
                salaire: '',
                service: ''
        },
        }
    },
    mounted() {
      if(!localStorage.getItem('token')) {
                this.$router.push({ name: "LoginPage" })
        }
    },
   methods: {
      addPersonnel() {
        const config =  { headers: { Authorization: `Bearer ${localStorage.getItem('token')}`  }}
        axios.post('http://localhost:8080/api/v1/personnel',this.form, config)
        .then(() => {
          this.$router.push("/")
        }, err => {
          console.log(err.response)
        })
        
      },

   }
}
</script>