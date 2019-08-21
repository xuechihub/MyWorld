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
        [types.ADD_FORM_DATA_SUCCESS]: (state) => {
            Vue.prototype.$message({
                showClose: true,
                type: 'success',
                message: '新建成功!'
            });
        },
        [types.ADD_FORM_DATA_FAILURE]: (state, error) => {
            Vue.prototype.$message({
                showClose: true,
                type: 'error',
                message: '新建失败!'
            });
        },
        [types.UPDATE_FORM_DATA_SUCCESS]: (state) => {
            Vue.prototype.$message({
                showClose: true,
                type: 'success',
                message: '编辑成功!'
            });
        },
        [types.UPDATE_FORM_DATA_FAILURE]: (state, error) => {
            Vue.prototype.$message({
                showClose: true,
                type: 'error',
                message: '编辑失败!'
            });
        },
        [types.DELETE_DATA_SUCCESS]: (state) => {
            Vue.prototype.$message({
                showClose: true,
                type: 'success',
                message: '删除成功!'
            });
        },
        [types.DELETE_DATA_FAILURE]: (state, error) => {
            Vue.prototype.$message({
                showClose: true,
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
                    if (response && response.code === 0) {
                        let metaData = response.data.metaData;
                        commit(types.LOAD_TABLE_MATE_DATA_SUCCESS, {
                            metaData,
                            tableId: info.tableId || info
                        });
                    } else {
                        return Promise.reject(response);
                    }
                })
                .catch((error) => {
                    commit(types.LOAD_TABLE_MATE_DATA_FAILURE, {
                        error,
                        tableId: info.tableId || info
                    });
                });
        },
        getTableData ({ commit }, { tableId, parm }) {
            return service(`rest/${tableId}${parm}`, {
                method: 'GET'
            })
                .then((response) => {
                    if (response && response.code === 0) {
                        let list = response.data;
                        commit(types.LOAD_TABLE_DATA_SUCCESS, {
                            list,
                            tableId
                        });
                    } else {
                        return Promise.reject(response);
                    }
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
                    if (response && response.code === 0) {
                        let data = response.data.data;
                        commit(types.LOAD_FORM_DATA_SUCCESS, {
                            data,
                            tableId: info.tableId
                        });
                    } else {
                        return Promise.reject(response);
                    }
                })
                .catch((error) => {
                    commit(types.LOAD_FORM_DATA_FAILURE, {
                        error,
                        tableId: info.tableId
                    });
                });
        },
        addCommonData ({ dispatch, commit }, info) {
            return service(`rest/${info.tableId}`, {
                method: 'PUT',
                body: JSON.stringify(info.formData)
            })
                .then((response) => {
                    if (response && response.code === 0) {
                        commit(types.ADD_FORM_DATA_SUCCESS);
                        dispatch('getTableData', info.tableId);
                    } else {
                        return Promise.reject(response);
                    }
                })
                .catch((error) => {
                    commit(types.ADD_FORM_DATA_FAILURE, {
                        error
                    });
                });
        },
        updateCommonData ({ dispatch, commit }, info) {
            return service(`rest/${info.tableId}`, {
                method: 'POST',
                body: JSON.stringify(info.formData)
            })
                .then((response) => {
                    if (response && response.code === 0) {
                        commit(types.UPDATE_FORM_DATA_SUCCESS);
                        dispatch('getTableData', info.tableId);
                    } else {
                        return Promise.reject(response);
                    }
                })
                .catch((error) => {
                    commit(types.UPDATE_FORM_DATA_FAILURE, {
                        error
                    });
                });
        },
        deleteCommonData ({ dispatch, commit }, info) {
            let ids = info.ids.join(',');
            return service(`rest/${info.tableId}/${ids}`, {
                method: 'DELETE'
            })
                .then((response) => {
                    if (response && response.code === 0) {
                        commit(types.DELETE_DATA_SUCCESS);
                        dispatch('getTableData', info.tableId);
                    } else {
                        return Promise.reject(response);
                    }
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
