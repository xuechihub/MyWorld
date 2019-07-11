<template>
    <el-dialog :title="dialogTitle"
               :visible="dialogFormVisible"
               @close="setDialogFormVisible(false)"
               v-el-drag-dialog
               width="952px"
               top="90px"
    >
        <ext-from-dialog ref="extFromDialog" :dialogFormVisible="innerVisible"
                             :setInnerVisible="setInnerVisible"
                             :dialogTitle="innerTitle"
        ></ext-from-dialog>
        <el-form ref="form" :model="resTypeForm">
            <el-form-item label="资源类型名称" :label-width="formLabelWidth" prop="name"
                          :rules="{
                                    required: true, message: '不能为空', trigger: 'blur'
                                }">
                <el-input v-model="resTypeForm.name" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="资源类型编码" :label-width="formLabelWidth" prop="code"
                          :rules="{
                                    required: true, message: '不能为空', trigger: 'blur'
                                }">
                <el-input v-model="resTypeForm.code" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="是否受控" :label-width="formLabelWidth" prop="effectValidFlag"
                          :rules="{
                                    required: true, message: '不能为空', trigger: 'change'
                                }">
                <el-select v-model="resTypeForm.effectValidFlag" placeholder="请选择是否受控">
                    <el-option label="是" value=1></el-option>
                    <el-option label="否" value=0></el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="定义权限效力" :label-width="formLabelWidth" prop="effect"
                          :rules="{
                                    required: true, message: '不能为空', trigger: 'change'
                                }">
                <el-select v-model="resTypeForm.effect" placeholder="请选择定义权限效力" multiple>
                    <el-option label="禁止" value=1></el-option>
                    <el-option label="允许" value=2></el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="备注信息" :label-width="formLabelWidth">
                <el-input v-model="resTypeForm.remark" autocomplete="off"></el-input>
            </el-form-item>
        </el-form>
        <label class="EXLabel">扩展属性列表：</label>
        <div class="button-group">
            <el-button icon="el-icon-plus" @click="newExtClick">新建</el-button>
            <el-button icon="el-icon-edit" @click="editExtClick">编辑</el-button>
            <el-button icon="el-icon-delete" @click="deleteExtClick">删除</el-button>
        </div>
        <el-table
            ref="extTable"
            size="small"
            v-loading="loading"
            :data="tableData"
            height="280px"
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
            <el-table-column
                v-for="head in tableHeader"
                :key="head.id"
                :prop="head.id"
                :label="head.caption"
                :type="head.editorType"
                show-overflow-tooltip
                max-width="100px"
                min-width="100px"
            >
            </el-table-column>
        </el-table>
        <div slot="footer" class="dialog-footer">
            <el-button type="primary" @click=submitForm>确 定</el-button>
            <el-button @click=setDialogFormVisible(false)>取 消</el-button>
        </div>
    </el-dialog>
</template>

<script>
    import UUID from 'uuid-js';
    import elDragDialog from '../../../../../../directive/el-dragDialog/index';
    import tableHeader from './tableHeader';
    import ExtFromDialog from '../EXTFromDialog/ExtFromDialog';

    export default {
        name: 'ResTypeDialog',
        components: { ExtFromDialog },
        directives: { elDragDialog },
        props: {
            dialogFormVisible: Boolean,
            setDialogFormVisible: Function,
            dialogTitle: String
        },
        formData () {
            return {
                dialogTableVisible: false,
                formLabelWidth: '120px',
                tableHeader: tableHeader,
                innerVisible: false,
                innerTitle: '新建扩展属性'
            };
        },
        computed: {
            resTypeForm: function () {
                return this.$store.state.resourceManagement.resTypeForm || {};
            },
            tableData: function () {
                return this.$store.state.resourceManagement.extList || [];
            },
            loading: function () {
                return this.$store.state.resourceManagement.loading || false;
            }
        },
        methods: {
            setInnerVisible: function (visible) {
                this.innerVisible = visible;
            },
            newExtClick: function (e) {
                this.innerVisible = true;
                this.$refs['extFromDialog'].init();
            },
            editExtClick: function (e) {
                if (this.$refs.extTable.selection && (this.$refs.extTable.selection.length <= 0 || this.$refs.extTable.selection.length > 1)) {
                    this.$message({
                        showClose: true,
                        message: '请选择一条记录！'
                    });
                    return;
                }
                this.$store.dispatch('getExtFrom', this.$refs.extTable.selection[0]).then(() => {
                    this.innerTitle = '编辑扩展属性';
                    this.innerVisible = true;
                });
            },
            deleteExtClick: function (e) {
                if (this.$refs.extTable.selection && this.$refs.extTable.selection.length > 0) {
                    this.$store.dispatch('deleteExtProp', this.$refs.extTable.selection);
                } else {
                    this.$message({
                        showClose: true,
                        message: '请选择至少一条记录！'
                    });
                }
            },
            submitForm: function (e) {
                let that = this;
                this.$refs.form.validate((valid) => {
                    if (valid) {
                        let form = _.cloneDeep(that.$refs.form.model);
                        form.id = UUID.create().hex;
                        let list = this.$refs.extTable.store.states.formData;
                        let requestBody = {
                            form: form,
                            list: list
                        };
                        this.$store.dispatch('saveResType', JSON.stringify(requestBody)).then(() => {
                            this.setDialogFormVisible(false);
                        });
                    } else {
                        return false;
                    }
                });
            }
        }
    };
</script>

<style scoped>
    .EXLabel {
        font-size: 16px;
        font-weight: bold;
    }

    .button-group {
        margin: 16px 0;
    }

    .el-table {
        margin-top: 16px;
    }

    .el-table th, .el-table td {
        padding: 6px 0;
    }

    .el-table th > .cell {
        white-space: nowrap;
        text-align: center;
    }
</style>
