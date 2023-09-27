<script>
export default {
  name: 'ResetPassword',
  data () {
    return {
      username: "",
      password: "",
      newPasswordDefine : false
    }
  },
  methods : {
    showUsername(){
      console.log("Username : ", this.username)
    }
  }
}
</script>

<template>
  <div id="pagewrapper">
    <div id="backgroundwrapper">
      <div id="signinwrapper">
        <div>
          <h2>Reset password</h2>
        </div>
        <div id="inputwrapper">
          <input id="username" type="text" v-model="username" placeholder="Username">
        </div>
        <div>
          <button>Send</button>
        </div>
        <div>
          <p id="pNewPassword" v-if="newPasswordDefine">Your password : {{ password }}</p>
        </div>
        <div>
          <a href="/">Login</a>
        </div>
      </div>
    </div>

  </div>
</template>

<style scoped lang="scss">

  #pagewrapper{
    background: linear-gradient(to right, #c8d5fc, white);
    height: 100vh;
    display: flex;
    justify-content: center;
    align-items: center;
  }
  h2{
    font-size: 20px;
    color: #00CAE3;
  }
  #backgroundwrapper{
   background-color: white;
    border-radius: 21px;
    width: 691px;
    height: 411px;
    display: flex;
    justify-content: center;
    align-items: center;
    box-shadow: 5px 5px 15px #343434;
  }
  #signinwrapper{
    background: white;
    display: flex;
    flex-direction: column;
    justify-content: space-around;
    width: 340px;
    height: 365px;
  }
  #signinwrapper button{
    width: 188px;
    height: 28px;
    border-radius: 22.5px;
    border:none;
    outline: none;
    background-color: #00CAE3;
    color: white;
  }
  #signinwrapper button:hover{
    background-color: #0191a3;
  }
  #inputwrapper{
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
  }
  #inputwrapper input{
    width: 188px;
    height: 28px;
    border-radius: 22.5px;
    border:none;
    outline: none;
    background-color: #f3f4f6;
    margin-top: 8px;
    margin-bottom: 8px;
    padding: 2px;
    font-size: 10px;
  }
  #inputwrapper p{
    font-size: 10px;
  }
   a{
    text-decoration: none;
    color: #00CAE3;
  }
  #pNewPassword{
    color: red;
  }
</style>