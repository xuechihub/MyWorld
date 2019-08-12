<template>
    <div>
        <new-user-dialog :dialogFormVisible="dialogVisible"
                         :setDialogFormVisible="setDialogFormVisible"
        ></new-user-dialog>
        <div class="button-group">
            <el-button icon="el-icon-plus" @click="dialogVisible = true">新建</el-button>
            <el-button icon="el-icon-edit">编辑</el-button>
            <el-button icon="el-icon-delete">删除</el-button>
        </div>
        <el-select size="small" v-model="value8" filterable placeholder="请选择">
            <el-option
                v-for="item in options"
                :key="item.value"
                :label="item.label"
                :value="item.value">
            </el-option>
        </el-select>
        <el-table
            size="mini"
            v-loading="loading"
            :data="tableData"
            @selection-change="handleSelectionChange"
            :default-sort="{prop: 'date', order: 'descending'}"
            height="320"
            min-height="320"
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
    </div>
</template>

<script>
    export default {
        name: 'ResourceResponse'
    };
</script>

<style scoped>
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
