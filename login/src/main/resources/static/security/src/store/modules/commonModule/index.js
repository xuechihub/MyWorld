import * as types from './consts';
import Vue from 'vue';
import { service } from '../../../assets/js/utils';

const commonModule = {
    state: {
        tableMateDatas: {},
        tablesData: {},
        formData: {}
    },
    mutations: {
        [types.LOAD_TABLE_MATE_DATA_SUCCESS]: (state, data) => {
            Vue.set(state.tableMateDatas, data.tableId, data.metaData);
            Vue.set(state, 'formData', {});
        },
        [types.LOAD_TABLE_MATE_DATA_FAILURE]: (state, error) => {
            Vue.set(state.tableMateDatas, error.tableId, {});
            Vue.set(state, 'formData', {});
        },
        [types.LOAD_TABLE_DATA_SUCCESS]: (state, data) => {
            Vue.set(state.tablesData, data.tableId, data.list);
        },
        [types.LOAD_TABLE_DATA_FAILURE]: (state, error) => {
            Vue.set(state.tablesData, error.tableId, {});
        },
        [types.LOAD_FORM_DATA_SUCCESS]: (state, data) => {
            Vue.set(state, 'formData', data.data);
        },
        [types.LOAD_FORM_DATA_FAILURE]: (state, error) => {
            Vue.set(state, 'formData', {});
        },
        [types.DELETE_DATA_SUCCESS]: (state) => {
            Vue.prototype.$message({
                type: 'success',
                message: '删除成功!'
            });
        },
        [types.DELETE_DATA_FAILURE]: (state, error) => {
            Vue.prototype.$message({
                type: 'error',
                message: '删除失败!'
            });
        }

    },
    actions: {
        getTableMateData ({ commit }, info) {
            let isEdit = !!info.isEdit;
            return service(`rest/metaData/table/${info.tableId || info}?isEdit=${isEdit}`, {
                method: 'GET'
            })
                .then((response) => {
                    let metaData = response.metaData;
                    commit(types.LOAD_TABLE_MATE_DATA_SUCCESS, {
                        metaData,
                        tableId: info.tableId || info
                    });
                })
                .catch((error) => {
                    commit(types.LOAD_TABLE_MATE_DATA_FAILURE, {
                        error,
                        tableId: info.tableId || info
                    });
                });
        },
        getTableData ({ commit }, tableId) {
            return service(`rest/${tableId}`, {
                method: 'GET'
            })
                .then((response) => {
                    let list = response.list;
                    commit(types.LOAD_TABLE_DATA_SUCCESS, {
                        list,
                        tableId
                    });
                })
                .catch((error) => {
                    commit(types.LOAD_TABLE_DATA_FAILURE, {
                        error,
                        tableId
                    });
                });
        },
        getFormData ({ commit }, info) {
            return service(`rest/${info.tableId}/${info.id}`, {
                method: 'GET'
            })
                .then((response) => {
                    let data = response.data;
                    commit(types.LOAD_FORM_DATA_SUCCESS, {
                        data,
                        tableId: info.tableId
                    });
                })
                .catch((error) => {
                    commit(types.LOAD_FORM_DATA_FAILURE, {
                        error,
                        tableId: info.tableId
                    });
                });
        },
        deleteCommonData ({ commit }, info) {
            return service(`rest/${info.tableId}`, {
                method: 'DELETE',
                body: JSON.stringify({ ids: info.ids })
            })
                .then((response) => {
                    commit(types.DELETE_DATA_SUCCESS);
                })
                .catch((error) => {
                    commit(types.DELETE_DATA_FAILURE, {
                        error
                    });
                });
        }
    }
};

export default commonModule;
