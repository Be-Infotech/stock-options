import {describe, expect, it} from 'vitest';
import {mount, VueWrapper} from '@vue/test-utils';
import {createStore} from "vuex";
import UserData from "@/components/UserData.vue";
import {nextTick} from "vue";

describe('UserData', () => {

  let wrapper: VueWrapper<any>

  function init() {
    const mockStore = createStore({
      state: {
        userInfo: {
          userId: null,
          username: "toto",
          mail: "",
          profilePhoto: "",
          firstName: "",
          lastName: "",
          address: "",
          city: "",
          country: "",
          postalCode: ""
        }
      }
    })
    wrapper = mount(UserData, {
      global: {
        plugins: [mockStore],
      },
    })
  }

  it('Should display user information', async () => {
    init();

    const inputUsername = wrapper.find("#username").element as HTMLInputElement;

    expect(inputUsername.value).toContain("toto")
  })
})
