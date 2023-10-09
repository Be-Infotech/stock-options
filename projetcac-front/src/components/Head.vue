<script>
export default {
  name: "Header",
}
</script>

<template>
  <header>
    <router-link to="/"><img src="public/images/tableau-de-bord.png"></router-link>
    <router-link to="/resetPassword"><img src="../../public/images/utilisateur.png"></router-link>
  </header>
</template>

<style scoped lang="scss">
header{
  height: 100px;
  background: #00CAE3;
  border-bottom: black 2px solid;
  display: flex;
  flex-direction: row;
  justify-content: end;
  align-items: center;
  padding: 10px;
}
img{
  width: 35px;
  margin: 0 20px;
}
img:hover{
  width: 45px;
}
</style>
