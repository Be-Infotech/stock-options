<script>

export default {
  name: "Graphic",
  props: {
    title : String
  },
}

</script>

<template>
  <div id="componentWrapper">
    <div id="titleWrapper"><h2>{{title}}</h2></div>
    <div id="tabWrapper"></div>
  </div>
</template>

<style scoped lang="scss">
  #componentWrapper{
    background-color: white;
    width: 680px;
    height: 400px;
    border-radius: 17px;
    display: flex;
    flex-direction: column;
    align-items: center;
    box-shadow: 5px 5px 15px #343434;
  }
  #titleWrapper{
    display: flex;
    background-color: #FB8C00;
    width: 615px;
    Height: 105px;
    border-radius: 10px;
    transform: translateY(-18px);
    align-items: center;
  }
  #titleWrapper h2{
    color: white;
    font-weight: 600;
    font-size: 32px;
    font-family: Roboto, sans-serif;
    line-height: 37.5px;
    margin: 0 50px;
  }
  #tabWrapper{
    border: solid black 1px;
  }
</style>
