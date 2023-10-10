import {afterEach, beforeEach, vi} from "vitest";
import * as vueRouter from 'vue-router';
import axios from "axios";
import {cleanAll} from "nock";


beforeEach(() => {
    // fix: Cross origin http://localhost forbidden)
    axios.defaults.adapter = 'http';
});

beforeEach(() => {
    vi.mock('vue-router', async () => ({...(await vi.importActual<typeof vueRouter>('vue-router'))}))
})

afterEach(() => {
    vi.restoreAllMocks()
});

afterEach(() => cleanAll());

