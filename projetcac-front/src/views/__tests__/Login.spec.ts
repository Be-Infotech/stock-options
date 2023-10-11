import {describe, it, expect, vi, SpyInstance, beforeEach} from 'vitest';
import { mount } from '@vue/test-utils';
import {User} from "../../api/api";
import * as api from "../../api/api";
import Login from '@/views/Login.vue'

function mountTheForm () {
  const wrapper = mount(Login, { props: {} })
  return wrapper
}

describe('Login', () => {

  let loginApi: SpyInstance<[username: string, password: string], Promise<User>>;
  beforeEach(() => {
      const data : User = {
          username: "loan",
          password: "loan",
          firstName: "loan",
          lastName: "Heniart",
          mail: "loan@gmail.com",
          address: "1 rue national",
          city: "Bethune",
          country: 'France',
          postalCode: "62400",
          profilePhoto:  ""
      };

    loginApi = vi.spyOn(api, 'loginApi').mockResolvedValue(data)
  })


  it('Submit login', () => {

      const wrapper = mount(Login);
      const inputUsername = wrapper.find("#username");
      const inputPassword = wrapper.find('#password');
      const inputSubmit = wrapper.find("#submit")

      inputUsername.setValue('loan')
      inputPassword.setValue('toto')
      inputSubmit.trigger('submit');

      expect(loginApi).toHaveBeenCalledWith('loan', 'toto')
  })
})
