<template>
    <div class="container mt-5">
        <div class="mb-5">
            <div v-if="user">
                <p ><strong>Bonjour {{ user.firstname }} | {{ user.role }}</strong></p>
            </div>
            <div class="d-flex justify-content-between">
                <button @click="generatePass()" v-if="user.role === 'ADMIN'" class="btn btn-secondary btn-sm ">Générer un nouveau mot de passe</button>
                <button @click="newPersonnel" class="btn btn-info btn-sm mr-2">ajouter personnel</button>
                <button @click="logout" class="btn btn-warning btn-sm ">se déconnecter</button>
            </div>
           
        </div>
      <div class="d-flex justify-content-center">
        
        <table v-if="personnel.length > 0" class="table table-bordered">
            <thead>
                <tr>
                    <th scope="col">ID</th>
                    <th scope="col">Nom</th>
                    <th scope="col">Prénom</th>
                    <th scope="col">Salaire</th>
                    <th scope="col">Service</th>
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
                    <td><button @click="deletePersonnel(p.id)" class="btn btn-danger btn-sm">supprimer</button></td>
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
            user: {}

        }
    },
    created() {
        this.getUser();
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
      logout() {
            localStorage.clear();
            this.$router.push("/");
    },
    generatePass() {
        this.$router.push('/generatePassword')
    },
    getUser() {
        axios.get('http://localhost:8080/api/v1/auth/me', {
             headers: 
             { Authorization: `Bearer ${localStorage.getItem('token')}`}
            }) .then(response => {
          this.user = response.data;
        })
        .catch(error => {
          console.log(error);
        });
    },
    newPersonnel() {
        this.$router.push("/add")
    }
   }
}
</script>