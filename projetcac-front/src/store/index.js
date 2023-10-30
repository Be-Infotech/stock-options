import { createStore } from 'vuex'

export default createStore({
  state: {
    userInfo : {
      userId: null,
      username: "",
      mail: "",
      profilePhoto: "",
      firstName: "",
      lastName: "",
      address: "",
      city: "",
      country: "",
      postalCode: ""
    }



  },
  getters: {
  },
  mutations: {
    updateUserInfo(state, newValue){
      state.userInfo = newValue;
    }
  },
  actions: {
  },
  modules: {
  }
})
