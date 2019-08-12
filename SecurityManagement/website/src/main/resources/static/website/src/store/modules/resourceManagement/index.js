import * as types from './consts';
import Vue from 'vue';
import { service } from '../../../assets/js/utils';

const resourceManagement = {
    state: {
        resTypeForm: {
            effect: []
        },
        extList: [],
        loading: false,
        extForm: {}
    },
    mutations: {
        [types.SAVE_EXTEND_PROPERTY]: (state, from) => {
            state.extList.push(from);
            state.extList = _.sortBy(state.extList, ['displayOrder']);
        },
        [types.CLEAR_EXTEND_PROPERTY]: (state) => {
            Vue.set(state, 'extList', []);
        },
        [types.DELETE_EXTEND_PROPERTY]: (state, selections) => {
            let extList = _.differenceBy(state.extList, selections, 'id');
            Vue.set(state, 'extList', extList);
        },
        [types.SAVE_RES_TYPE_SUCCESS]: (state) => {
            Vue.set(state, 'extList', []);
        },
        [types.GET_EXT_FORM]: (state, selection) => {
            Vue.set(state, 'extForm', selection);
        }

    },
    actions: {
        saveExtProp ({ commit }, from) {
            commit(types.SAVE_EXTEND_PROPERTY, from);
        },
        deleteExtProp ({ commit }, selections) {
            commit(types.DELETE_EXTEND_PROPERTY, selections);
        },
        saveResType ({ commit }, requestBody) {
            return service('res/rest/authResourceType', {
                method: 'POST',
                body: requestBody
            })
                .then((response) => {
                    commit(types.SAVE_RES_TYPE_SUCCESS, response.resultValue);
                })
                .catch((error) => {
                    commit(types.SAVE_RES_TYPE_SUCCESS);
                });
        },
        getExtFrom ({ commit }, selection) {
            commit(types.GET_EXT_FORM, selection);
        }
    }
};

export default resourceManagement;
