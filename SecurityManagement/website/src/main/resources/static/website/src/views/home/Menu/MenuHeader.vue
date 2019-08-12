<script>
    export default {
        name: 'MenuHeader',
        data () {
            return {
                defaultActive: ''
            };
        },
        computed: {
            headerMenu: function () {
                return this.$store.getters.headerMenu || {};
            }
        },
        mounted () {
            this.$forceUpdate();
        },
        methods: {
            handleSelect (key, keyPath) {

            },
            getItems: function (h, Items, parentPath) {
                let menuKeys = Object.keys(Items);
                return menuKeys.map((key) => {
                    let item = Items[key];
                    if (item.type === 'string') {
                        let index = item.item && Object.keys(item.item).length > 0 ? `${item.id}/${item.item[Object.keys(item.item)[0]].id}` : item.id;
                        let parent = parentPath === '/' ? '' : parentPath;
                        return <el-menu-item
                            index={`${parent}/${index}`}
                        >
                            {item.name}
                        </el-menu-item>;
                    } else if (item.type === 'list') {
                        return <el-submenu
                            index={`${parentPath}${item.id}/`}
                        >
                            <template slot="title">{item.name}</template>
                            {this.getItems(h, item.item, `${parentPath}${item.id}`)}
                        </el-submenu>;
                    }
                    return null;
                });
            }
        },
        render: function (h) {
            let items = this.getItems(h, this.headerMenu.menu, '/');
            let defaultActive = '';
            if (this.$refs.headerMenu && this.$refs.headerMenu.items) {
                let indexs = Object.keys(this.$refs.headerMenu.items);
                // eslint-disable-next-line no-undef
                let index = _.findIndex(indexs, (index) => {
                    return this.$route.path.startsWith(index);
                });
                defaultActive = indexs[index];
            }
            return (
                <el-menu
                    ref="headerMenu"
                    defaultActive={`${defaultActive}`}
                    class="header-operations"
                    mode="horizontal"
                    onSelect={this.handleSelect}
                    router
                >
                    {
                        items
                    }
                </el-menu>
            );
        }
    };
</script>

<style>
    .header-operations.el-menu--horizontal.el-menu {
        display: inline-block;
        float: left;
        padding-right: 30px;
        height: 100%;
        background-color: var(--color-primary);
        border: none;
    }

    .el-menu--horizontal > .el-submenu .el-submenu__title,
    .el-menu--horizontal > .el-menu-item {
        color: var(--color-white);
        border: none;
    }

    .el-submenu.is-active .el-submenu__title i,
    .el-submenu.is-opened .el-submenu__title i {
        color: var(--color-black);
    }

    .el-submenu__title i {
        color: var(--color-white);
    }

    .el-menu--horizontal > .el-submenu.is-opened .el-submenu__title,
    .el-menu--horizontal > .el-submenu.is-active .el-submenu__title,
    .el-menu--horizontal > .el-menu-item.is-active {
        color: var(--color-black);
        background-color: var(--color-white);
        border: none;
    }
</style>
