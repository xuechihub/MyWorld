import Vue from 'vue';
import Vuex from 'vuex';
import app from './modules/app/index';
import resourceManagement from './modules/resourceManagement';
import session from './modules/session';
import getters from './getters';
import commonModule from './modules/commonModule';

Vue.use(Vuex);

const store = new Vuex.Store({
    modules: {
        app,
        resourceManagement,
        session,
        commonModule
    },
    getters
});

export default store;
