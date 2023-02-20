<template>
    <div class="container mt-5">
        <div class="mb-5">
            <div v-if="this.$route.params.message" class="alert alert-success mb-2">
                {{ this.$route.params.message }}
            </div>
            
            <div class="d-flex justify-content-between align-items-center">
                
                <div v-if="user">
                <p >  <img src="@/assets/user.png/" alt="" height="30px"> <strong>Bonjour {{ user.firstname }} | <span class="badge bg-success">{{ user.role }}</span></strong></p>
                </div>
                <div class="float-end">
                    <img src="@/assets/paiperleck.png/" alt="" height="50px" >
                 </div>
                <form class="d-flex" role="search">
                    <input class="form-control me-2" v-model="search" type="search" placeholder="Recherchez" aria-label="Search">
                    
                </form>
            </div>
            <hr>
            <div class="d-flex justify-content-between mt-3">
                
                <button @click="newUser()" v-if="user.role === 'ADMIN'" class="btn btn-secondary btn-sm">Créer un nouveau utilisateur</button>
                <button @click="allPersonnel()" class="btn btn-secondary btn-sm">Consulter la liste de personnel</button>
                
                <button @click="logout" class="btn btn-secondary btn-sm ">se déconnecter</button>
            </div>
           
        </div>
      <div class="d-flex justify-content-center">
        
        <table v-if="users.length > 0" class="table table-bordered">
            <thead>
                <tr>
                    <th scope="col">ID</th>
                    <th scope="col">Nom</th>
                    <th scope="col">Prénom</th>
                    <th scope="col">email</th>
                    <th scope="col">role</th>
                    <th scope="col" v-if="user.role === 'ADMIN'">Options</th>
                </tr>
            </thead>
            
            <tbody>
                <tr v-for="p in searchUsers()" :key="p.id">
                    <th>{{ p.id }}</th>
                    <td>{{ p.firstname }}</td>
                    <td>{{ p.lastname }}</td>
                    <td>{{ p.email }}</td>
                    <td>{{ p.role }}</td>
                    <td v-if="user.role === 'ADMIN'">
                        <div class="d-flex justify-content-around">
                            <button @click="generatePass(p.id)" v-if="user.role === 'ADMIN'" class="btn btn-warning btn-sm ">Modifier</button>
                            <button @click="deleteUser(p.id)" v-if="user.role === 'ADMIN'" class="btn btn-danger btn-sm">supprimer</button>
                            <button @click="generatePass(p.id)" v-if="user.role === 'ADMIN'" class="btn btn-dark btn-sm ">Générer un nouveau mot de passe</button>
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
            users: [],
            user: {},
            search : '',

        }
    },
    created() {
        this.getUser();
    },
    mounted() {
        if(!localStorage.getItem('token')) {
                this.$router.push({ name: "LoginPage" })
        }
        axios.get('http://localhost:8080/api/v1/auth/all', {
             headers: 
             { Authorization: `Bearer ${localStorage.getItem('token')}`}
            })
        .then(res => {
            this.users = res.data
        })
        .catch(err => {
            console.log(err)
        })
    },
   methods: {
      deleteUser(id) {
         axios.delete(`http://localhost:8080/api/v1/auth/${id}`, {
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
    generatePass(id) {
        this.$router.push({ name: "generatePassword", params: { id: id } })
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
    allPersonnel() {
        this.$router.push("/listPersonnel")
    },
    getUsers() {
        this.$router.push("/add")
    },
    newUser() {
        this.$router.push("/createUser") 
    },
    searchUsers() {
        return this.users.filter(a => {
        return a.firstname.toLowerCase().includes(this.search.toLowerCase())
      })
    }
   }
}
</script>