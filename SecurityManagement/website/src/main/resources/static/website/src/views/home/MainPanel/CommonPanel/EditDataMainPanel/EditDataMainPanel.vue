<script>
    export default {
        name: 'EditDataMainPanel',
        data () {
            return {
                formData: {}
            };
        },
        computed: {
            tableId: function () {
                return this.$route.meta.objId;
            },
            formId: function () {
                return this.$route.params.id || '';
            },
            data: function () {
                return this.$store.state.commonModule.formData || {};
            },
            tableMateDatas: function () {
                return this.$store.state.commonModule.tableMateDatas || {};
            },
            fields: function () {
                let fields = this.tableMateDatas[this.tableId] ? this.tableMateDatas[this.tableId].header || [] : [];
                // eslint-disable-next-line no-undef
                return _.filter(fields, { editable: true });
            }
        },
        watch: {
            '$route.params.objId': function (oldTableId, newTableId) {
                this.init();
            }
        },
        mounted () {
            this.init();
        },
        methods: {
            init () {
                this.getTableMateData().then(() => {
                    if (this.formId) {
                        this.$store.dispatch('getFormData', { tableId: this.tableId, id: this.formId }).then(() => {
                            this.$set(this, 'formData', this.data);
                        });
                    }
                });
                if (this.$refs.commonForm) {
                    this.$refs.commonForm.resetFields();
                }
            },
            getTableMateData: function () {
                return this.$store.dispatch('getTableMateData', {
                    tableId: this.tableId,
                    isEdit: this.formId
                });
            },
            regExpValidator: function (rule, value, callback) {
                let regExp = new RegExp(rule.regExp);
                if (!regExp.test(value)) {
                    callback(new Error('不通过正则'));
                } else {
                    callback();
                }
            },
            passwordValidator: function (rule, value, callback) {
                if (value !== this.formData.password) {
                    callback(new Error('两次密码不一致'));
                } else {
                    callback();
                }
            },
            getRules: function () {
                let rules = {};
                this.fields.map((field) => {
                    if (field.nullable && !field.regExp) {
                        return;
                    }
                    rules[field.id] = [];
                    if (field.id === 'confirmPassword') {
                        rules[field.id].push({
                            validator: this.passwordValidator,
                            message: '两次密码不一致',
                            trigger: 'blur'
                        });
                    }
                    if (!field.nullable) {
                        rules[field.id].push({
                            required: !field.nullable,
                            message: `${field.caption}不能为空`,
                            trigger: 'blur'
                        });
                    }
                    if (field.regExp) {
                        rules[field.id].push({
                            validator: this.regExpValidator,
                            message: field.regExpTip,
                            trigger: ['change', 'blur'],
                            regExp: field.regExp
                        });
                    }
                });
                return rules;
            },
            addCommonData: function (info) {
                return this.$store.dispatch('addCommonData', info);
            },
            updateCommonData: function (info) {
                return this.$store.dispatch('updateCommonData', info);
            },
            addTableRow: function (e, id, arrays) {
                if (typeof (this.formData[id]) === 'string' || typeof (this.formData[id]) === 'undefined') {
                    this.formData[id] = [];
                }
                let rowData = {
                    index: this.formData[id].length + 1
                };
                let keys = Object.keys(arrays);
                keys.map((key) => {
                    if (arrays[key].dataType === 'int') {
                        rowData[key] = this.formData[id].length + 1;
                    } else if (arrays[key].dataType === 'int') {
                        rowData[key] = '';
                    }
                });

                this.formData[id].push(rowData);
            },
            deleteTableRow: function (e, scope, id) {
                this.$delete(this.formData[id], scope.$index);
            },
            onSubmit: function (e) {
                this.$refs.commonForm.validate((valid) => {
                    if (valid) {
                        let info = {
                            tableId: this.tableId,
                            formData: this.formData
                        };
                        if (this.formId) {
                            this.updateCommonData(info);
                        } else {
                            this.addCommonData(info);
                        }
                        this.$router.push({ path: `/${this.$route.meta.commonId}/${this.$route.meta.objId}` });
                    } else {
                        console.log('error submit!!');
                        return false;
                    }
                });
            },
            cancel: function () {
                this.$router.push({ path: `/${this.$route.meta.commonId}/${this.$route.meta.objId}` });
            }
        },
        render: function (h) {
            if (this.fields && this.fields.length > 0 && Object.keys(this.formData).length === 0) {
                this.fields.map((field) => {
                    this.$set(this.formData, field.id, '');
                });
            } else if (Object.keys(this.formData).length === 0) {
                return null;
            }
            let rules = this.getRules();
            return <el-form
                props={{
                    model: this.formData,
                    rules: rules
                }}
                ref={'commonForm'}
                class={'dataForm'}
                label-position={'left'}
                label-width={'120px'}
                label-suffix={':'}
                validate-on-rule-change={false}
                // inline-message={true}
            >
                {
                    this.fields && this.fields.map((field) => {
                        if (field.dataType === 'enum') {
                            return <el-form-item
                                label={field.caption}
                                prop={field.id}
                            >
                                <el-select v-model={this.formData[field.id]}
                                           placeholder={`请选择${field.caption}`}
                                >
                                    {field.options.map((option) => {
                                        return <el-option
                                            label={option.label} value={option.value}></el-option>;
                                    })}
                                </el-select>
                            </el-form-item>;
                        } else if (field.dataType === 'password') {
                            return <el-form-item
                                label={field.caption}
                                prop={field.id}
                            >
                                <el-input v-model={this.formData[field.id]} show-password></el-input>
                            </el-form-item>;
                        } else if (field.dataType === 'array') {
                            let arrayKeys = Object.keys(field.arrays);
                            if (this.formData[field.id] === '') {
                                arrayKeys.map((key) => {
                                    this.formData[field.id] = [];
                                });
                            }
                            return <el-form-item
                                label={field.caption}
                                prop={field.id}
                            >
                                <el-row>
                                    <el-col>
                                        <el-table data={this.formData[field.id]}>
                                            {
                                                arrayKeys && arrayKeys.map((key) => {
                                                    return <el-table-column
                                                        key={key}
                                                        prop={key}
                                                        label={field.arrays[key].caption}
                                                        {...{
                                                            scopedSlots: {
                                                                default: scope => {
                                                                    if (field.arrays[key].dataType === 'string') {
                                                                        return <el-input
                                                                            v-model={scope.row[key]}
                                                                        ></el-input>;
                                                                    } else if (field.arrays[key].dataType === 'int') {
                                                                        return <el-input-number
                                                                            v-model={scope.row[key]}></el-input-number>;
                                                                    } else {
                                                                        return null;
                                                                    }
                                                                }
                                                            }
                                                        }
                                                        }
                                                    >
                                                    </el-table-column>;
                                                })
                                            }
                                            <el-table-column
                                                align={'center'}
                                                width={'100px'}
                                                fixed='right'
                                                label={'操作列'}
                                                {...{
                                                    scopedSlots: {
                                                        default: scope => {
                                                            return (<div>
                                                                <el-button
                                                                    size="mini"
                                                                    type="danger"
                                                                    onClick={e => this.deleteTableRow(e, scope, field.id)}>{'Delete'}
                                                                </el-button>
                                                            </div>);
                                                        }
                                                    }
                                                }
                                                }
                                            >
                                            </el-table-column>
                                        </el-table>
                                    </el-col>
                                    <el-col>
                                        <el-button
                                            icon="el-icon-plus"
                                            class="addRowButton"
                                            onClick={e => this.addTableRow(e, field.id, field.arrays)}
                                        >
                                            {'增加'}
                                        </el-button>
                                    </el-col>
                                </el-row>
                            </el-form-item>;
                        } else {
                            return <el-form-item
                                label={field.caption}
                                prop={field.id}
                            >
                                <el-input v-model={this.formData[field.id]}></el-input>
                            </el-form-item>;
                        }
                    })
                }
                <el-form-item>
                    <el-button type="primary" onClick={this.onSubmit}>提交</el-button>
                    <el-button onClick={this.cancel}>取消</el-button>
                </el-form-item>
            </el-form>;
        }
    };
</script>

<style scoped>
    .dataForm {
        width: 550px;
    }

    .arrayLabel {
        width: calc(50% - 70px);
        margin-right: 30px;
        text-align: center;
    }

    .array {
        width: calc(50% - 70px);
        margin-right: 30px;
        text-align: center;
    }

    .addRowButton {
        margin-top: 5px;
    }
</style>
