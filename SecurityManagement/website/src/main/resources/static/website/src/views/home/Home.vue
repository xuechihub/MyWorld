<template>
    <el-container>
        <el-header>
            <i class="web-font logo">My World</i>
            <my-menu-header/>
        </el-header>
        <el-container>
            <router-view/>
        </el-container>
    </el-container>
</template>
<script>
    import MenuHeader from './Menu/MenuHeader';
    export default {
        name: 'Home',
        components: {
            MyMenuHeader: MenuHeader
        }
    };
</script>

<style>
    html, body {
        height: 100%;
        width: 100%;
        margin: 0;
    }
    .el-container{
        height: 100%;
        width: 100%;
    }
    .el-header{
        box-shadow: 0 1px 3px rgba(26, 26, 26, 0.1);
        background-color: var(--color-primary);
    }

    .logo{
        display: inline-block;
        float: left;
        margin: 0 80px 0 48px;
    }
</style>
