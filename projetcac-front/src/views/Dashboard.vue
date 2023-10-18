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
    <div id="tabWrapper">
      <Tableau title="Cours des actions"/>
    </div>
  </div>
</template>
<script>
import Head from "@/components/Head.vue";
import Graphic from "@/components/Graphic.vue";
import Tableau from "@/components/Tableau.vue";
import { mapState } from "vuex";
import router from "@/router";

export default {
  name: "Dashboard",
  components: {
    Head,
    Graphic,
    Tableau
  },
  data() {
    return {}
  },
  methods: {},
  computed: {
    ...mapState(['username','mail']),
  },
  beforeMount() {
    if ((this.username === "" ) || (this.mail === "")){
      router.push("/")
    }
  }
}
</script>
