<script lang="ts">
import {mapState} from 'vuex'
import {updateUserDataApi} from "@/api/api";
import store from '@/store';

export default {
  name: "UserData",
  data() {
    return {
      user : store.state.userInfo
    }
  },
  props: {
    title : String
  },
  computed: {
    ...mapState(['userInfo'])
  },
  methods: {
    handleSubmit(){
      updateUserDataApi(store.state.userInfo)
        .then(response => {
          console.log(response)
        })
        .catch(error =>{
          console.log(error);
        })
    }
  }
}
</script>

<template>
  <div id="componentWrapper">
    <div id="titleWrapper"><h2>{{title}}</h2></div>
    <form id="formWrapper" @submit.prevent="handleSubmit()">
      <div id="div1" class="inputWrapper">
        <input type="text" name="username" id="username" placeholder="Username" v-model="user.username" required>
        <input type="email" name="email" id="email" placeholder="Email" v-model="user.mail" required>
      </div>
      <div id="imagePassword" class="inputWrapper">
        <input type="file" accept="./png" id="image">
        <input type="password" name="password" id="password" placeholder="password">
      </div>
      <div id="div2" class="inputWrapper">
        <input type="text" name="firstname" id="firstname" placeholder="First name" v-model="user.firstName">
        <input type="text" name="Last name" id="Last name" placeholder="Last name" v-model="user.lastName">
      </div>
      <div id="div3" class="inputWrapper">
        <input type="text" name="address" id="address" placeholder="Address" v-model="user.address">
      </div>
      <div id="div4" class="inputWrapper">
        <input type="text" name="city" id="city" placeholder="City" v-model="user.city">
        <input type="text" name="country" id="country" placeholder="Country" v-model="user.country">
        <input type="text" name="postalcode" id="postalcode" placeholder="Postal code" v-model="user.postalCode">
      </div>
      <div id="div5" ><button>UPDATE PROFILE</button></div>
    </form>

  </div>
</template>

<style scoped lang="scss">
#componentWrapper{
  background-color: white;
  width: 625px;
  height: 730px;
  border-radius: 17px;
  display: flex;
  flex-direction: column;
  align-items: center;
  box-shadow: 5px 5px 15px #343434;
  margin: 40px;
}
#titleWrapper{
  display: flex;
  background-color: #00CAE3;
  width: 600px;
  Height: 105px;
  border-radius: 10px;
  transform: translateY(-18px);
  align-items: center;
}
#titleWrapper h2{
  font-weight: 600;
  font-size: 32px;
  font-family: Roboto, sans-serif;
  line-height: 37.5px;
  color: white;
  margin: 0 30px;
}
.inputWrapper{
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  margin: 70px 5px;
}
input{
  border-bottom: solid black 2px;
  width: 200px;
}
input:focus{
  outline: none;
}
#address{
  width: 100%;
}
#div4 input{
  width: 160px;
}
#formWrapper{
  width: 550px;
  height: 600px;
}
#div5{
  display: flex;
  flex-direction: row;
  justify-content: end;
}
#div5 button{
  background-color: #00CAE3;
  color: white;
  width: 200px;
  height: 45px;
  border-radius: 10px;
}
#image{
  background-color: white;
  outline: none;
}
</style>
