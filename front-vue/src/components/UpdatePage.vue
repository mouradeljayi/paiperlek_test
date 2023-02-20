<template>
    <div class="mt-5 container">
        <h5 class="text-center">Modifier personnel</h5>
        <div class="row justify-content-center">
            <div class="col-md-6">
          <div class="card p-3">
            
            <form @submit.prevent="updatePersonnel">
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
             
              <button type="submit" class="btn btn-warning">Modifier</button>
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
    created() {
      if(!localStorage.getItem('token')) {
                this.$router.push({ name: "LoginPage" })
        }

        axios.get(`http://localhost:8080/api/v1/personnel/${this.$route.params.id}`, {
             headers: { Authorization: `Bearer ${localStorage.getItem('token')}` 
             }})
        .then(res => {
            this.form = res.data
            console.log(res.data)
        })
        .catch(err => {
            console.log(err)
        })
    },
   methods: {
    updatePersonnel() {
        axios.put(`http://localhost:8080/api/v1/personnel/${this.$route.params.id}`,this.form, {
             headers: { Authorization: `Bearer ${localStorage.getItem('token')}` 
             }})
        .then(res => {
            console.log(res)
            this.$router.push({ name: "ListPersonnel", params: { message: "Le personnel a été modifier avec succés" } })
        })
        .catch(err => {
            console.log(err)
        })
   }
}
}
</script>