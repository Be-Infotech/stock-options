<script>
export default {
  name: "UserData",
  props: {
    firstName: String,
    lastName: String,
    username: String
  },
}
</script>

<template>
<div id="componentWrapper">
  <div id="profilephotoWrapper"></div>
  <div id="nameWrapper"><h2>{{firstName}} {{lastName}}</h2></div>
  <div id="usernameWrapepr"><p>{{username}}</p></div>
</div>
</template>

<style scoped lang="scss">
#componentWrapper{
  background-color: white;
  width: 430px;
  height: 220px;
  border-radius: 17px;
  display: flex;
  flex-direction: column;
  align-items: center;
  box-shadow: 5px 5px 15px #343434;
  margin: 40px;
}
#profilephotoWrapper{
  width: 128px;
  height: 128px;
  border-radius: 100%;
  background-color: #D9D9D9;
  transform: translateY(-64px);
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
}

</style>
