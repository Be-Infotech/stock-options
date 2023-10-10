import Login from "@/views/Login.vue";
import {shallowMount} from "@vue/test-utils";
import {describe, expect, it,vi, beforeEach} from "vitest";
import Router from 'vue-router';
import * as vueRouter from 'vue-router';



describe("test login page", () => {
  let $router;
  beforeEach(() => {
    $router = Router;
    vi.spyOn(vueRouter, 'useRouter').mockReturnValue($router)
  })
  it("redirection", () => {
    const wrapper = shallowMount(Login);
    const inpReset = wrapper.find('#resetPassword')
    inpReset.trigger('click');
    expect($router.push).toHaveBeenCalledWith('/resetPassword')
  })
})
