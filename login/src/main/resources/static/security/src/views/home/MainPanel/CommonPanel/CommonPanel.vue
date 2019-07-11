<script>
    import CommonMenu from './Menu/CommonMenu';

    export default {
        name: 'CommonPanel',
        components: {
            CommonMenu: CommonMenu
        },
        computed: {
            item: function () {
                let target = this.$store.getters.headerMenu.menu[this.$route.params.commonId];
                return target && target.type === 'string' ? target.item : {};
            }
        },
        render: function (h) {
            let menu = null;
            if (this.item && Object.keys(this.item).length > 0) {
                menu = <el-aside width="260px">
                    <common-menu
                        item={this.item}
                    ></common-menu>
                </el-aside>;
            }
            return (
                <el-container>
                    {menu}
                    <el-main>
                        <router-view/>
                    </el-main>
                </el-container>
            );
        }
    };
</script>

<style scoped>
    .el-aside {
        border: 1px solid var(--border-color);
    }
</style>
