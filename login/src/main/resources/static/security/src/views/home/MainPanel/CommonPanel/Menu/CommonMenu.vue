<script>
    export default {
        name: 'CommonMenu',
        props: {
            item: {
                type: Object,
                // eslint-disable-next-line vue/require-valid-default-prop
                default: {}
            }
        },
        render: function (h) {
            let keys = Object.keys(this.$props.item);
            return (
                <el-menu
                    default-active={`/${this.$route.meta.commonId}/${this.$route.meta.objId}`}
                    class="el-menu-vertical-demo"
                    router
                >
                    {
                        keys.map((key) => {
                            return <el-menu-item index={`/${this.$route.meta.commonId}/${this.$props.item[key].id}`}>
                                <i class={`iconfont icon-${this.$props.item[key].id}`}></i>
                                <span slot="title">{this.$props.item[key].name}</span>
                            </el-menu-item>;
                    })
                    }
                </el-menu>
            );
        }
    };
</script>

<style>
    .el-menu-item.is-active
    {
        outline: none;
        background-color: #ecf5ff;
    }

    .el-menu-item [class^='iconfont icon-'] {
        margin-right: 5px;
        width: 24px;
        text-align: center;
        font-size: 18px;
        vertical-align: middle;
    }
</style>
