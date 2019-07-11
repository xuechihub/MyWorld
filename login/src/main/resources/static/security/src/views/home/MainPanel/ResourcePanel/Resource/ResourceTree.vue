<template>
    <my-tree
        class="instanceTree"
        :data="data"
        :props="defaultProps"
        @node-click="handleNodeClick">
    </my-tree>
</template>

<script>
    export default {
        name: 'ResourceTree',
        formData () {
            return {
                formData: [{
                    label: '一级 1',
                    children: [{
                        label: '二级 1-1',
                        children: [{
                            label: '三级 1-1-1'
                        }]
                    }]
                }, {
                    label: '一级 2',
                    children: [{
                        label: '二级 2-1',
                        children: [{
                            label: '三级 2-1-1'
                        }]
                    }, {
                        label: '二级 2-2',
                        children: [{
                            label: '三级 2-2-1'
                        }]
                    }]
                }, {
                    label: '一级 3',
                    children: [{
                        label: '二级 3-1',
                        children: [{
                            label: '三级 3-1-1'
                        }]
                    }, {
                        label: '二级 3-2',
                        children: [{
                            label: '三级 3-2-1'
                        }]
                    }]
                }],
                defaultProps: {
                    children: 'children',
                    label: 'label'
                }
            };
        },
        methods: {
            handleNodeClick (data) {
                console.log(data);
            }
        }
    };
</script>

<style>
    .instanceTree {
        padding: 20px 0;
        border-right: 1px solid var(--border-color)
    }
</style>
