<script>
import Head from "@/components/Head.vue";
import UserData from "@/components/UserData.vue";
import UserInfo from "@/components/UserInfo.vue";

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
  methods: {}
}
</script>

<template>
  <Head/>
  <div id="pageWrapper">
    <UserData title="Edit profile"/>
    <UserInfo
      firstName = "Loan"
      lastName = "Heniart"
      username="lheniart"
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
