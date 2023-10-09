<style scoped lang="scss">
* {
  margin: 0;
  padding: 0;
}

#pagewrapper {
  display: flex;
  flex-direction: column;
}

#graphWrapper, #tabWrapper {
  display: flex;
  flex-direction: row;
  justify-content: space-around;
  margin: 40px 10px;
}

</style>

<template>
  <Head/>
  <div id="pagewrapper">
    <div id="graphWrapper">
      <Graphic title="Weekly evolution"/>
      <Graphic title="Monthly evolution"/>
      <Graphic title="Semi-annual evolution"/>

    </div>
    <div id="tabWrapper"></div>
  </div>
</template>
<script>
import Head from "@/components/Head.vue";
import Graphic from "@/components/Graphic.vue";

export default {
  name: "Dashboard",
  components: {
    Head,
    Graphic
  },
  data() {
    return {}
  },
  methods: {}
}
</script>
