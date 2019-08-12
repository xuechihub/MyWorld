import * as types from './consts';
import Vue from 'vue';
import { service } from '../../../assets/js/utils';

const session = {
    state: {
        userInfo: {},
        headerMenu: {
            hasLoad: false,
            menu: {}
        }
    },
    mutations: {
        [types.LOAD_MENU_SUCCESS]: (state, menu) => {
            Vue.set(state.headerMenu, 'menu', menu);
            Vue.set(state.headerMenu, 'hasLoad', true);
        },
        [types.LOAD_MENU_FAILURE]: (state, error) => {
            state.headerMenu.hasLoad = false;
            state.headerMenu.data = {};
        }
    },
    actions: {
        getMenu ({ commit }) {
            return service('rest/metaData/headerMenu', {
                method: 'GET'
            })
                .then((response) => {
                    if (response && response.code === 0) {
                        commit(types.LOAD_MENU_SUCCESS, response.data);
                        return Promise.resolve(response.data);
                    } else {
                        return Promise.reject(response);
                    }
                })
                .catch((error) => {
                    commit(types.LOAD_MENU_FAILURE, error);
                });
        }
    }
};

export default session;
