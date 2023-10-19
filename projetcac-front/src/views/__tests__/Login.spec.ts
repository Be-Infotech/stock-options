import {beforeEach, describe, expect, it, SpyInstance, vi} from 'vitest';

import {mount} from '@vue/test-utils';
import * as api from "../../api/api";
import {User} from "../../api/api";
import Login from '@/views/Login.vue'
import router from '@/router';
import {nextTick} from "vue";

function mountTheForm() {
  const wrapper = mount(Login, {
    global: {
      plugins: [router]
    }
  })
  return wrapper

}

describe('Login', () => {


  let loginApi: SpyInstance<[username: string, password: string], Promise<User | string>>;
  beforeEach(() => {
    loginApi = vi.spyOn(api, 'loginApi')
  })


  it('Should allow connection', async () => {
    const data: User = {
      username: "loan",
      password: "loan",
      firstName: "loan",
      lastName: "Heniart",
      mail: "loan@gmail.com",
      address: "1 rue national",
      city: "Bethune",
      country: 'France',
      postalCode: "62400",
      profilePhoto: ""
    }
    loginApi.mockResolvedValue(data)
    const wrapper = mount(Login);
    const inputUsername = wrapper.find("#username");
    const inputPassword = wrapper.find('#password');
    const inputSubmit = wrapper.find("#submit")
    const push = vi.spyOn(router, 'push');

    await inputUsername.setValue('loan')
    await inputPassword.setValue('toto')
    await inputSubmit.trigger('submit');

    expect(loginApi).toHaveBeenCalledWith('loan', 'toto')
    expect(push).toHaveBeenCalledOnce();
    expect(push).toHaveBeenCalledWith('/dashboard')

  })
  it('should not allow connection', async () => {
    const error: string = "error"
    loginApi.mockResolvedValue(error)

    const wrapper = mountTheForm();
    const inputUsername = wrapper.find("#username");
    const inputPassword = wrapper.find('#password');
    const inputSubmit = wrapper.find("#submit")
    const push = vi.spyOn(router, 'push');

    await inputUsername.setValue('loan')
    await inputPassword.setValue('toto')
    await inputSubmit.trigger('submit');
    expect(loginApi).toHaveBeenCalledWith('loan', 'toto')
    await nextTick()
    const textError = wrapper.find("#msgError");
    expect(textError.text()).toContain("error");
    expect(push).toHaveBeenCalledTimes(0);

  })
  it("Should redirect to resetPassword", async () => {
    const wrapper = mountTheForm();
    const push = vi.spyOn(router, 'push');
    const inputResetPassword = wrapper.find('a[id=redirection');

    await inputResetPassword.trigger('click');

    expect(push).toHaveBeenCalledOnce();
    expect(push).toHaveBeenCalledWith('/resetPassword')
  })
})



