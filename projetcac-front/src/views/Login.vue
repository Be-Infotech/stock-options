<style scoped lang="scss">
#pagewrapper {
  background: linear-gradient(to right, #c8d5fc, white);
  height: 100vh;
  display: flex;
  justify-content: center;
  align-items: center;
}

h2 {
  font-size: 30px;
  color: #00CAE3;
}

#logginwrapper {
  background-color: white;
  border-radius: 21px;
  width: 691px;
  height: 411px;
  display: flex;
  justify-content: center;
  align-items: center;
  box-shadow: 5px 5px 15px #343434;
}

#logginwrapper div {
  margin: auto;
}

form {
  display: flex;
  flex-direction: column;
  justify-content: space-around;
  width: 340px;
  height: 365px;
}

form button {
  width: 282px;
  height: 42px;
  border-radius: 22.5px;
  border: none;
  outline: none;
  background-color: #00CAE3;
  color: white;
}

form button:hover {
  background-color: #0191A3;
}

#inputwrapper {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
}

#inputwrapper input {
  width: 282px;
  height: 42px;
  border-radius: 22.5px;
  border: none;
  outline: none;
  background-color: #F3F4F6;
  margin-top: 8px;
  margin-bottom: 8px;
  font-size: 15px;
  padding: 5px 40px;
}

#inputwrapper p {
  font-size: 15px;
}

#inputwrapper a {
  text-decoration: none;
  color: #00CAE3;
}

::-webkit-input-placeholder {
  text-align: center;
}

::-moz-placeholder {
  text-align: center;
}

/* firefox 19+ */
:-ms-input-placeholder {
  text-align: center;
}

/* ie */
input:-moz-placeholder {
  text-align: center;
}
</style>

<template>
  <div id="pagewrapper">
    <div id="logginwrapper">
      <form @submit.prevent="handleSubmit()">
        <div>
          <h2>Log in</h2>
        </div>
        <div id="inputwrapper">
          <input id="username" type="text" v-model="username" placeholder="Username" required>
          <input id="password" type="password" v-model="password" placeholder="Password" required>
          <p id="msgError" v-if="error">{{ error }}</p>
          <p>Forget your password ?
            <RouterLink id="redirection" to="/resetPassword">Reset here</RouterLink>
          </p>
        </div>
        <div>
          <button id="submit" type="submit">Log in now</button>
        </div>
      </form>
    </div>
  </div>
</template>

<script>

import {loginApi} from "@/api";
import router from "@/router";
import { mapState } from "vuex";


export default {
  data() {
    return {
      username: "",
      password: "",
      error : "",
    }
  },
  methods: {
    handleSubmit() {
      // loginApi(this.username, this.password)
      //     .then((response) => {
      //       console.log(response)
      //       router.push("/dashboard")
      //     })
      //     .catch((error) => {
      //       this.error = "Connection refused"
      //     })

      loginApi(this.username, this.password)
        .then(response => {
          console.log(response)
          console.log(response.username)
          this.$store.commit('updateUsername',response.username);
          this.$store.commit('updateMail',response.mail);
          router.push("/dashboard")
        })
        .catch(error => {
          this.error = "Connection refused"
        })

    },

  },

}
</script>
