import Vue from 'vue';
import App from './App.vue';
import router from './router';
import store from './store';
import './plugins/element.js';
import './assets/css/theme.css';
// import './assets/css/element/index.scss';
// eslint-disable-next-line no-unused-vars
import _ from 'lodash';

Vue.config.productionTip = false;

new Vue({
    router,
    store,
    render: h => h(App)
}).$mount('#app');
