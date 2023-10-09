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
  <div id="graphicWrapper"></div>
  <p id="pTitle">{{title}}</p>
</div>
</template>

<style scoped lang="scss">
#componentWrapper{
  background-color: white;
  width: 430px;
  height: 300px;
  border-radius: 17px;
  display: flex;
  flex-direction: column;
  align-items: center;
  box-shadow: 5px 5px 15px #343434;
}
#graphicWrapper{
  background-color: #00CAE3;
  width: 404px;
  Height: 185px;
  border-radius: 10px;
  transform: translateY(-20px);
}
#pTitle{
  font-weight: 600;
  font-size: 32px;
  font-family: Roboto, sans-serif;
  line-height: 37.5px;
}
</style>
