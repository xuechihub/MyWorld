<script>
    import { parseJson2Url } from '../../../../../assets/js/utils';

    export default {
        name: 'CommonMainPanel',
        data () {
            return {
                options: [{
                    value: '选项1',
                    label: '黄金糕'
                }],
                value8: '',
                loading: false,
                defaultPagination: {
                    total: 0,
                    pageSize: 10,
                    currentPage: 1
                },
                search: {},
                searchConditions: [],
                sortConditions: {}
            };
        },
        computed: {
            tableMateDatas: function () {
                return this.$store.state.commonModule.tableMateDatas || {};
            },
            headers: function () {
                let headers = this.tableMateDatas[this.tableId] ? this.tableMateDatas[this.tableId].header || [] : [];
                // eslint-disable-next-line no-undef
                return _.filter(headers, { showable: true });
            },
            searchField: function () {
                let headers = this.tableMateDatas[this.tableId] ? this.tableMateDatas[this.tableId].header || [] : [];
                // eslint-disable-next-line no-undef
                return _.keyBy(_.filter(headers, { searchable: true }), 'id');
            },
            buttonList: function () {
                let buttonList = this.tableMateDatas[this.tableId] ? this.tableMateDatas[this.tableId].buttonList || [] : [];
                return buttonList;
            },
            searchs: function () {
                let searchs = this.tableMateDatas[this.tableId] ? this.tableMateDatas[this.tableId].header || [] : [];
                // eslint-disable-next-line no-undef
                return _.filter(searchs, { searchable: true });
            },
            tableId: function () {
                return this.$route.meta.objId;
            },
            tableData: function () {
                return this.$store.state.commonModule.tablesData[this.tableId] || {};
            },
            tablePagination: function () {
                return {
                    total: this.tableData.total || this.defaultPagination.total,
                    pageSize: this.tableData.pageSize || this.defaultPagination.pageSize,
                    currentPage: this.tableData.currentPage || this.defaultPagination.currentPage
                };
            }

        },
        watch: {
            '$route.meta.objId': function (oldTableId, newTableId) {
                this.init();
            }
        },
        beforeMount () {
            this.init();
        },
        methods: {
            init () {
                this.sortConditions = {};
                this.search = {};
                this.getTableMateData();
                this.getTableData();
            },
            formatter (row, column) {
                if (Array.isArray(row[column.property])) {
                    let items = row[column.property];
                    let cell = [];
                    items.map((item) => {
                        cell.push(item.text + ':' + item.value);
                    });
                    return cell.join(',');
                }
                return row[column.property];
            },
            getTableData: function () {
                let parm = {
                    currentPage: this.tablePagination.currentPage,
                    pageSize: this.tablePagination.pageSize,
                    searchConditions: this.searchConditions,
                    sortConditions: Object.keys(this.sortConditions).length > 0 ? [this.sortConditions] : []
                };
                return this.$store.dispatch('getTableData', { tableId: this.tableId, parm: `?${parseJson2Url(parm)}` });
            },
            getTableMateData: function () {
                return this.$store.dispatch('getTableMateData', this.tableId);
            },
            deleteCommonData: function (info) {
                return this.$store.dispatch('deleteCommonData', info);
            },
            handlePlus: function (e) {
                this.$router.push({ path: `/${this.$route.meta.commonId}/${this.$route.meta.objId}/add` });
            },
            handleEdit: function (e, scope) {
                if (this.$refs.commonTable.selection.length === 1) {
                    // eslint-disable-next-line standard/object-curly-even-spacing
                    this.$router.push({ path: `/${this.$route.meta.commonId}/${this.$route.meta.objId}/edit/${this.$refs.commonTable.selection[0].id}` });
                } else if (scope) {
                    this.$router.push({ path: `/${this.$route.meta.commonId}/${this.$route.meta.objId}/edit/${scope.row.id}` });
                } else {
                    this.$message({
                        showClose: true,
                        message: '请选择一条记录！'
                    });
                }
            },
            handleDelete: function (e, scope) {
                if (this.$refs.commonTable.selection.length === 0 && !scope) {
                    this.$message({
                        showClose: true,
                        message: '请至少选择一条记录！'
                    });
                    return;
                }
                this.$confirm('确定删除?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then((a) => {
                    let ids = [];
                    if (scope) {
                        ids.push(scope.row.id);
                    } else {
                        this.$refs.commonTable.selection.map((item) => {
                            ids.push(item.id);
                        });
                    }
                    this.deleteCommonData({
                        tableId: this.tableId,
                        ids
                    });
                });
            },
            handleResetPwd: function (e) {
                ;
            },
            handleSearch: function (e) {
                let searchKeys = Object.keys(this.search);
                this.searchConditions = [];
                searchKeys.map((key) => {
                    this.searchConditions.push({
                        field: key,
                        isFuzzy: this.searchField[key].isFuzzy,
                        like: this.search[key]
                    });
                });
                this.getTableData();
            },
            handleSortChange: function ({ column, prop, order }) {
                if (order) {
                    this.sortConditions = {
                        field: prop,
                        isASC: order === 'ascending'
                    };
                } else {
                    this.sortConditions = {};
                }
                this.getTableData();
            },
            handlePagination: function (type, num) {
                if (type === 'pageSize') {
                    this.tablePagination.pageSize = num;
                } else if (type === 'currentPage') {
                    this.tablePagination.currentPage = num;
                } else if (type === 'prev') {
                    this.tablePagination.currentPage = this.tablePagination.currentPage - 1;
                } else if (type === 'next') {
                    this.tablePagination.currentPage = this.tablePagination.currentPage + 1;
                }
                this.getTableData();
            }
        },
        render: function (h) {
            let buttonheight = this.buttonList && this.buttonList.length > 0 ? 32 : 0;
            let searchHeight = this.searchs && this.searchs.length > 0 ? 32 : 0;
            let otherHeight = 32 + buttonheight + searchHeight;
            return (
                <div class="commonMainPanel">

                    <div class="button-group">
                        {
                            this.buttonList.map((button) => {
                                return <el-button
                                    icon={`el-icon-${button.icon}`}
                                    onClick={this[`handle${button.id}`]}
                                >{button.text}</el-button>;
                            })
                        }
                    </div>
                    <el-form
                        ref="search"
                        inline={true}
                        props={{ model: this.search }}
                        class="demo-form-inline"
                    >
                        {
                            this.searchs.map((searchItem) => {
                                return <el-form-item label={searchItem.caption}>
                                    <el-input v-model={this.search[searchItem.id]}
                                              placeholder={searchItem.caption}></el-input>
                                </el-form-item>;
                            })
                        }
                        {
                            this.searchs && this.searchs.length > 0 ? <el-form-item>
                                <el-button
                                    type="primary"
                                    icon="el-icon-search"
                                    onClick={this.handleSearch}>查询
                                </el-button>
                            </el-form-item> : null
                        }
                    </el-form>
                    <el-table
                        ref="commonTable"
                        size="small"
                        loading={this.loading}
                        data={this.tableData.items}
                        on-sort-change={this.handleSortChange}
                        height={`calc(100% - ${otherHeight}px)`}
                        min-height={`calc(100% - ${otherHeight}px)`}
                        border
                        show-overflow-tooltip
                        stripe
                    >
                        <el-table-column
                            type="selection"
                            align="center"
                            width="55"
                        >
                        </el-table-column>
                        {
                            this.headers.map((header) => {
                                return <el-table-column
                                    key={header.id}
                                    prop={header.id}
                                    label={header.caption}
                                    formatter={this.formatter}
                                    show-overflow-tooltip
                                    sortable={header.sortable ? 'custom' : false}
                                    max-width="100px"
                                    min-width="100px"
                                >
                                </el-table-column>;
                            })
                        }
                        <el-table-column
                            align={'center'}
                            width={'180px'}
                            fixed='right'
                            label={'操作列'}
                            {...{
                                scopedSlots: {
                                    default: scope => {
                                        return (<div>
                                            <el-button
                                                size="mini"
                                                onClick={e => this.handleEdit(e, scope)}
                                            >{'Edit'}
                                            </el-button>
                                            <el-button
                                                size="mini"
                                                type="danger"
                                                onClick={e => this.handleDelete(e, scope)}>{'Delete'}
                                            </el-button>
                                        </div>);
                                    }
                                }
                            }
                            }
                        >
                        </el-table-column>
                    </el-table>
                    <el-pagination
                        ref="pagination"
                        layout={'total, sizes, prev, pager, next, jumper'}
                        page-sizes={[10, 20, 30, 40, 50, 100, 200]}
                        page-size={this.tablePagination.pageSize}
                        total={this.tablePagination.total}
                        current-page={this.tablePagination.currentPage}
                        on-size-change={(n) => this.handlePagination('pageSize', n)}
                        on-current-change={(n) => this.handlePagination('currentPage', n)}
                        on-prev-click={(n) => this.handlePagination('prev', n)}
                        on-next-click={(n) => this.handlePagination('next', n)}
                    >
                    </el-pagination>
                </div>
            );
        }
    };
</script>

<style>
    .commonMainPanel {
        height: calc(100% - 56px);
    }

    .button-group {
        margin: 0 0 16px 0;
    }

    .el-pagination {
        margin: 16px 0;
    }

    /*.el-table {*/
    /*    margin: 16px 0;*/
    /*}*/

    .el-table th, .el-table td {
        padding: 6px 0;
    }

    .el-table th > .cell {
        white-space: nowrap;
        text-align: center;
    }
</style>
