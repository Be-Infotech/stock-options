import {afterEach, beforeEach, describe, expect, it,} from 'vitest';
import nock from 'nock';
import {loginApi, resetPasswordApi, User} from "../api";
import axios, {isAxiosError} from "axios";

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


    expect.extend({

        verify<T>(received: any, predicate: (value: T) => boolean) {
            return {pass: predicate(received), message: () => `expected ${received} to match ${predicate}`};
        },
    });
    it('Should return code 401', async () => {
        // Réponse simulée que vous souhaitez renvoyer


        nock(url)
            .post('/user/signin')
            .reply(401);
        await expect(loginApi(username, password)).rejects.toThrow(
            expect.verify(x => isAxiosError(x) && x.response?.status === 401));
    });


});

describe("resetPassword Api", async () => {
    const username = 'testuser';
    const url = 'http://localhost:8080';
    it('Should return password', async () => {
        const response = {
            password : "1234"
        };
        nock(url)
            .post('/user/resetPassword')
            .reply(200,response)

        const result = await resetPasswordApi(username)
        expect(result).toEqual(response);
    })
    expect.extend({

        verify<T>(received: any, predicate: (value: T) => boolean) {
            return {pass: predicate(received), message: () => `expected ${received} to match ${predicate}`};
        },
    });
    it('Should return 500', async () =>{

        nock(url)
            .post('/user/resetPassword')
            .reply(500);
        await expect(resetPasswordApi(username)).rejects.toThrow(
            expect.verify(x => isAxiosError(x) && x.response?.status === 500));

    })
})
