import {createStore} from 'vuex'

export default createStore({
  state: {
    userInfo: {
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
  getters: {},
  mutations: {
    updateUserInfo(state: State, newValue: UserInfo) {
      state.userInfo = newValue;
    }
  },
  actions: {},
  modules: {}
})

export interface State {
  userInfo: UserInfo
}

export interface UserInfo {
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
