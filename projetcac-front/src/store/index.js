import { createStore } from 'vuex'

export default createStore({
  state: {
    username: "",
    mail: "",

  },
  getters: {
  },
  mutations: {
    updateUsername(state, newValue){
      state.username = newValue;
    },
    updateMail(state, newValue){
      state.mail = newValue;
    }
  },
  actions: {
  },
  modules: {
  }
})
