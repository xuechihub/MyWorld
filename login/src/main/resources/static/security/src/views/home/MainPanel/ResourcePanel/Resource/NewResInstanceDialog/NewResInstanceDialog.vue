<template>
    <el-dialog title="收货地址" :visible="dialogFormVisible"
               @close="setDialogFormVisible(false)"
    >
        <el-form :model="form">
            <el-form-item label="活动名称" :label-width="formLabelWidth">
                <el-input v-model="form.name" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="活动区域" :label-width="formLabelWidth">
                <el-select v-model="form.region" placeholder="请选择活动区域">
                    <el-option label="区域一" value="shanghai"></el-option>
                    <el-option label="区域二" value="beijing"></el-option>
                </el-select>
            </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
            <el-button type="primary" @click=setDialogFormVisible(false)>确 定</el-button>
            <el-button @click=setDialogFormVisible(false)>取 消</el-button>
        </div>
    </el-dialog>
</template>

<script>
    export default {
        name: 'NewResTypeDialog',
        props: { dialogFormVisible: Boolean, setDialogFormVisible: Function },
        formData () {
            return {
                dialogTableVisible: false,
                form: {
                    name: '',
                    region: '',
                    date1: '',
                    date2: '',
                    delivery: false,
                    type: [],
                    resource: '',
                    desc: ''
                },
                formLabelWidth: '120px'
            };
        }
    };
</script>

<style scoped>

</style>
