import {describe, expect, it,beforeEach,afterEach} from 'vitest';
import nock from 'nock';
import {loginApi, User} from "../api";
import axios from "axios";

describe('loginApi', () => {
    const username = 'testuser';
    const password = 'testpassword';
    const url = 'http://localhost:8080';

    afterEach(() => {
        nock.cleanAll();
    });

    beforeEach(() => {
      // fix: Cross origin http://localhost forbidden)
      axios.defaults.adapter = 'http';
    });

    it('Should return code 200 and User', async () => {
        // Réponse simulée que vous souhaitez renvoyer
        const responseData: User = {
            username: "toto",
            password: "####",
            firstName: "",
            lastName: "",
            mail: "toto@mail.com",
            address: "",
            city: "",
            country: '',
            postalCode: "",
            profilePhoto: ""
        }


        nock(url)
            .post('/user/signin')
            .reply(200, responseData);

        const result = await loginApi(username, password);
        expect(result).toEqual(responseData);
    });


});
