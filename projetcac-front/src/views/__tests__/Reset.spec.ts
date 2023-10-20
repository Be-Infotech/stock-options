import {beforeEach, describe, expect, it, SpyInstance, vi} from 'vitest';
import {mount} from '@vue/test-utils';
import * as api from "../../api/api";
import ResetPasswrod from "@/views/ResetPassword.vue"
import router from '@/router';
import {nextTick} from "vue";


function mountTheForm() {
    const wrapper = mount(ResetPasswrod, {
        global: {
            plugins: [router]
        }
    })
    return wrapper
}

describe('Reset Password', () => {

    let resetPasswordApi: SpyInstance<[username: string], Promise<Number | string>>;
    beforeEach(() => {
        resetPasswordApi = vi.spyOn(api, 'resetPasswordApi')
    })

    it('Should reset password', async () => {
        const newPassword = "1234"
        resetPasswordApi.mockResolvedValue(newPassword)
        const wrapper = mountTheForm();
        const inpUsername = wrapper.find("#username");
        const inpSend = wrapper.find("#submit");
        const push = vi.spyOn(router,'push');

        await inpUsername.setValue('loan');
        await inpSend.trigger('submit');
        expect(resetPasswordApi).toHaveBeenCalledOnce();
        expect(resetPasswordApi).toHaveBeenCalledWith('loan');
        await nextTick();
        expect(wrapper.find('#pNewPassword').text()).toContain('1234');
        expect(push).toHaveBeenCalledTimes(0);

    })
    it("Should show error message", async () =>{
        const error = 401;
        resetPasswordApi.mockResolvedValue(error);
        const wrapper = mountTheForm();
        const inpUsername = wrapper.find("#username");
        const inpSend = wrapper.find("#submit");
        const push = vi.spyOn(router,'push');

        await inpUsername.setValue('loan');
        await inpSend.trigger('submit');
        expect(resetPasswordApi).toHaveBeenCalledOnce();
        expect(resetPasswordApi).toHaveBeenCalledWith('loan');
        await nextTick();
        expect(wrapper.find('#msgError').text()).toContain(401)
        expect(push).toHaveBeenCalledTimes(0);
    })
    it("Should redirect to login", async () =>{
        const wrapper = mountTheForm();
        const push = vi.spyOn(router,'push');
        const inputResetPassword = wrapper.find('a[id=redirection');

        await inputResetPassword.trigger('click');

        expect(push).toHaveBeenCalledOnce();
        expect(push).toHaveBeenCalledWith('/')
    })
})
