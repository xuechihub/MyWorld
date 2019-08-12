<template>
    <el-dialog
        width="600px"
        :title="dialogTitle"
        :visible="dialogFormVisible"
        @close=setInnerVisible(false)
        append-to-body
        v-el-drag-dialog
    >
        <el-form :model="extForm" ref="form">
            <el-form-item label="属性中文名" :label-width="formLabelWidth"
                          prop="propNameChn"
                          :rules="{
                                    required: true, message: '不能为空', trigger:['blur', 'change']
                                }">
                <el-input v-model="extForm.propNameChn" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="属性英文名" :label-width="formLabelWidth" prop="propNameEng" :rules="{
                                    required: true, message: '不能为空', trigger:['blur', 'change']
                                }">
                <el-input v-model="extForm.propNameEng" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="数据类型" :label-width="formLabelWidth" prop="dataType" :rules="{
                                    required: true, message: '不能为空', trigger:['blur', 'change']
                                }">
                <el-select v-model="extForm.dataType">
                    <el-option label="是" value=1></el-option>
                    <el-option label="否" value=0></el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="显示顺序" :label-width="formLabelWidth" prop="displayOrder" :rules="{
                                    required: true, message: '不能为空', trigger:['blur', 'change']
                                }">
                <el-input-number v-model="extForm.displayOrder" :min="1"
                >
                </el-input-number>
            </el-form-item>
            <el-form-item label="备注信息" :label-width="formLabelWidth">
                <el-input v-model="extForm.remark" autocomplete="off"></el-input>
            </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
            <el-button type="primary" @click="submitForm('form')">确 定</el-button>
            <el-button @click=setInnerVisible(false)>取 消</el-button>
        </div>
    </el-dialog>
</template>

<script>
    import UUID from 'uuid-js';
    import elDragDialog from '../../../../../../directive/el-dragDialog/index';

    export default {
        name: 'EXTFromDialog',
        directives: { elDragDialog },
        props: {
            dialogFormVisible: Boolean,
            setInnerVisible: Function,
            dialogTitle: String
        },
        beforeMount (e) {
            this.init();
        },
        formData () {
            return {
                form: {
                    id: null,
                    resTypeID: null,
                    propNameEng: null,
                    propNameChn: null,
                    dataType: null,
                    displayOrder: 1,
                    remark: null
                },
                formLabelWidth: '100px'
            };
        },
        computed: {
            extForm: function () {
                return this.$store.state.resourceManagement.extForm || {};
            }
        },
        methods: {
            init () {
                if (this.$refs['form']) {
                    this.$refs['form'].resetFields();
                }
            },
            submitForm (form) {
                let that = this;
                this.$refs[form].validate((valid) => {
                    if (valid) {
                        let form = _.cloneDeep(that.$refs.form.model);
                        form.id = UUID.create().hex;
                        this.$store.dispatch('saveExtProp', form).then(() => {
                            this.setInnerVisible(false);
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

</style>
