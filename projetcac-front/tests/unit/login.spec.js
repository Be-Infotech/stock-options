import Login from "@/views/Login.vue";
import {shallowMount} from "@vue/test-utils";
import {describe, expect, it, vi} from "vitest";
import axios from "axios";
import {loginApi} from "@/api";

const mockList = {
  data: [
    {
      username: "loan",
      password: "loan",
      firstName: "loan",
      lastName: "Heniart",
      mail: "loan@gmail.com",
      address: "1 rue national",
      city: "Bethune",
      country: 'France',
      postalCode: "62400",
      profilePhoto: null
    }
  ]
};

vi.spyOn(axios, 'post').mockResolvedValue(mockList);


describe("connection", async function () {

  it("should allow connection")
  const wrapper = shallowMount(Login);
  const inputUsername = wrapper.find("#username");
  const inputPassword = wrapper.find('#password');
  const inputSubmit = wrapper.find("#submit")

  await inputUsername.setValue('loan')
  await inputPassword.setValue('loan')
  await inputSubmit.trigger('click');

  expect(axios.post).toHaveBeenCalledOnce();

})

