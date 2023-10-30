<script>
import Head from "@/components/Head.vue";
import UserData from "@/components/UserData.vue";
import UserInfo from "@/components/UserInfo.vue";
import {mapState} from "vuex";
import router from "@/router";

export default {
  name: "Profile",
  components: {
    Head,
    UserData,
    UserInfo
  },
  data() {
    return {}
  },
  methods: {},
  computed: {
    ...mapState(['userInfo']),
  },
  beforeMount() {
    if ((this.userInfo.username === "" ) && (this.userInfo.mail === "")){
      router.push("/")
    }
  }
}
</script>

<template>
  <Head/>
  <div id="pageWrapper">
    <UserData title="Edit profile"/>
    <UserInfo
      :firstName = userInfo.firstName
      :lastName = userInfo.lastName
      :username= userInfo.username
    />
  </div>

</template>

<style scoped lang="scss">
  #pageWrapper{
    display: flex;
    flex-direction: row;
    justify-content: space-around;
  }
</style>
