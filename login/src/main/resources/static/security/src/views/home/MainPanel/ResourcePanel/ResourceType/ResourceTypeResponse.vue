<template>
    <el-main class="instanceResponse">
        <res-type-dialog :dialogFormVisible="dialogVisible"
                             :setDialogFormVisible="setDialogFormVisible"
                             :dialogTitle="dialogTitle"
        ></res-type-dialog>
        <div class="button-group">
            <el-button icon="el-icon-plus" @click="newClick">新建</el-button>
            <el-button icon="el-icon-edit" >编辑</el-button>
            <el-button icon="el-icon-delete">删除</el-button>
        </div>
        <el-table
            size="small"
            v-loading="loading"
            :data="tableData"
            :default-sort="{prop: 'date', order: 'descending'}"
            height="calc(100% - 160px)"
            min-height="calc(100% - 160px)"
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
                :formatter="formatter"
                :type="head.editorType"
                show-overflow-tooltip
                sortable
                max-width="100px"
                min-width="100px"
            >
            </el-table-column>
            <el-table-column
                align="center"
                width="180px"
                fixed="right"
            >
                <template slot="header" slot-scope="slot">
                    {{"操作列"}}
                </template>
                <template slot-scope="scope">
                    <el-button
                        size="mini"
                        @click="handleEdit(scope.$index, scope.row)"
                    >Edit
                    </el-button>
                    <el-button
                        size="mini"
                        type="danger"
                        @click="handleDelete(scope.$index, scope.row)">Delete
                    </el-button>
                </template>
            </el-table-column>
        </el-table>
        <el-pagination
            :page-sizes="[10,20,30,40,50,100,200]"
            :page-size="20"
            layout="total, sizes, prev, pager, next, jumper"
            :total="400">
        </el-pagination>
    </el-main>
</template>

<script>
    import tableHeader from './Service/tableHeader';
    import ResTypeDialog from './ResTypeDialog/ResTypeDialog';

    export default {
        name: 'ResourceTypeResponse',
        components: {
            ResTypeDialog
        },
        formData () {
            return {
                tableHeader: tableHeader,
                dialogVisible: false,
                dialogTitle: '',
                tableData: [],
                loading: false
            };
        },
        methods: {
            formatter (row, column) {
                if (row[column.property] && (column.type === 'DropDownEditor' || column.type === 'DropDownTreeEditor')) {
                    let dict = _.filter(this.dicts, { name: column.property })[0];
                    let dictItem = _.filter(dict.values, { value: String(row[column.property]) });
                    return dictItem.length > 0 && dictItem[0] ? dictItem[0].text : row[column.property];
                }
                if (column.type === 'DropDownTreeEditor' && row[column.property]) {
                    let dict = _.filter(this.dicts, { name: column.property })[0];
                    let dictItem = _.filter(dict.values, { value: String(row[column.property]) });
                    return dictItem.length > 0 && dictItem[0] ? dictItem[0].text : row[column.property];
                }
                return row[column.property];
            },
            newClick (e) {
                this.dialogTitle = '新建资源类型';
                this.dialogVisible = true;
            },
            setDialogFormVisible (visible) {
                this.dialogVisible = visible;
            }
        }
    };
</script>

<style scoped>
    .instanceResponse {
        height: calc(100% - 0px);
    }

    .button-group {
        margin: 0 0 16px 0;
    }

    .el-pagination {
        margin: 16px 0;
    }

    .el-table {
        margin: 16px 0;
    }

    .el-table th, .el-table td {
        padding: 6px 0;
    }

    .el-table th > .cell {
        white-space: nowrap;
        text-align: center;
    }
</style>
