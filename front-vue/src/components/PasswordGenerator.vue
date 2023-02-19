<template>
    <div class="container mt-5">
       <div class="row justify-content-center">
        <div v-if="msg" class="alert alert-warning p-2 mt-3 mb-3">
            {{ msg }}
        </div>
        <div class="col-md-6">
            <div class="card p-4">
        <h4>Regénérer un nouveau mot de passe</h4>
        <hr>
        <button @click="onGenerate()" class="btn btn-secondary">Générer nouveau mot de passe</button>
        <p>Nouveau mot de passe : {{ newPassword }}</p>
        <button v-if="newPassword" @click="submitPassword()">Appliquez ce mot de passe</button>
       </div>
        </div>
       </div>
    </div> 
</template>
  
<script>

import axios from 'axios'
import randomstring from 'crypto-random-string';

    export default {
    data() {
        return {
            newPassword: "",
            user: {},
            msg:'',

        }
    },
    mounted() {
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
    methods: {
        onGenerate(){
            const password = randomstring({ length: 8, type: 'alphanumeric' });
            this.newPassword = password;
            
        },
        submitPassword() {
            axios.put(`http://localhost:8080/api/v1/auth/changePassword/${this.user.id}`, this.newPassword, {
            headers: {
              'Content-Type': 'text/plain'
            }
          }).then(() => {
          this.msg = "Votre nouveau mot de passe est : " + this.newPassword
          console.log("success")
        })
        .catch(error => {
          console.log(error);
        });
        }
    }
    
}
</script>