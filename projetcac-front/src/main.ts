import { createApp } from 'vue'
import App from './App.vue'
import router from './router'

const app = createApp(App)

createApp(app)
    .use(router)
    .mount('#app')
