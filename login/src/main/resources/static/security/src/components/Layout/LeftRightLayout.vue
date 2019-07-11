<template>
    <el-container>
        <el-aside width="260px">
            <slot name="left"></slot>
        </el-aside>
        <el-main>
            <slot name="right"></slot>
        </el-main>
    </el-container>
</template>

<script>
    export default {
        name: 'LeftRightLayout'
    };
</script>

<style scoped>
    .el-aside {
        background-color: var(--color-white);
        border: 1px solid var(--border-color);
    }

    .el-main {
        background-color: var(--color-white);
    }
</style>
