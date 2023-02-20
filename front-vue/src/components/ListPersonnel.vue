<template>
    <div class="container mt-5">
        <h1>Liste de personnel</h1>
        <div v-if="this.$route.params.message" class="alert alert-success mb-2">
                {{ this.$route.params.message }}
            </div>
      <div class="d-flex justify-content-center">
        
        <table v-if="personnel.length > 0" class="table table-bordered">
            <thead>
                <tr>
                    <th scope="col">ID</th>
                    <th scope="col">Nom</th>
                    <th scope="col">Prénom</th>
                    <th scope="col">salaire</th>
                    <th scope="col">service</th>
                    <th scope="col">Options</th>
                </tr>
            </thead>
            
            <tbody>
                <tr v-for="p in personnel" :key="p.id">
                    <th>{{ p.id }}</th>
                    <td>{{ p.nom }}</td>
                    <td>{{ p.prenom }}</td>
                    <td>{{ p.salaire }}</td>
                    <td>{{ p.service }}</td>
                    <td >
                        <div class="d-flex justify-content-around">
                            <button @click="editPersonnel(p.id)" class="btn btn-warning btn-sm ">Modifier</button>
                            <button @click="deletePersonnel(p.id)" class="btn btn-danger btn-sm">supprimer</button>
                        </div>
                    </td>
                </tr>
            </tbody>
            </table>
             <div v-else class="alert alert-info mt-5">
                Il n'y a pas de personnel pour le moment
             </div>
      </div>
    </div>
    
  </template>
  
  <script>
import axios from 'axios'

export default {
    data() {
        return {
            personnel: [],
            user: {},
            search : '',

        }
    },

    mounted() {
        if(!localStorage.getItem('token')) {
                this.$router.push({ name: "LoginPage" })
        }
        axios.get('http://localhost:8080/api/v1/personnel', {
             headers: 
             { Authorization: `Bearer ${localStorage.getItem('token')}`}
            })
        .then(res => {
            this.personnel = res.data
        })
        .catch(err => {
            console.log(err)
        })
    },
   methods: {
    editPersonnel(id) {
        this.$router.push({ name: "UpdatePage", params: { id:id} })
    },
    deletePersonnel(id) {
         axios.delete(`http://localhost:8080/api/v1/personnel/${id}`, {
             headers: { Authorization: `Bearer ${localStorage.getItem('token')}` 
             
        }}).then(
            location.reload()
        )
        .catch(err=> {
            console.log(err)
        })
      },
      
   

   }
}
</script>