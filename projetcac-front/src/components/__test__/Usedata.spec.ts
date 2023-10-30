import {beforeEach, describe, expect, it, SpyInstance, vi} from 'vitest';
import {mount} from '@vue/test-utils';
import UserData from "@/components/UserData.vue"
import {mapState} from "vuex";
import Vuex from 'vuex';


describe('UserData', () => {
    let store
    beforeEach(() =>{
        store = new Vuex.Store({
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
            }
        })
    })
    it("Should display user information", () => {
        const wrapper = mount(UserData);
        const userData = {
            userId : 1,
            username: "toto",
            mail: "toto@mail.com",
            profilePhoto: "",
            firstName: "toto",
            lastName: "toto",
            address: "",
            city: "",
            country: "",
            postalCode: ""
        }
        expect(store)..toHaveBeenCalledOnce();

    })
});
