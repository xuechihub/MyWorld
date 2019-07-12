<template>
    <div class="notfound">
        <p><img src="../../assets/img/404.gif" width="520" height="320"/></p>
        <h1>抱歉，这个页面已经被外星人绑架了</h1>
        <p>{{time}}秒钟后将带您返回地球</p>
    </div>
</template>

<script>
    export default {
        name: 'NotFoundPage',
        data () {
            return {
                time: 5,
                Interval: null
            };
        },
        watch: {
            '$route': function (old, newV) {
                this.interval();
            }
        },
        methods: {
            interval: function () {
                let that = this;
                this.Interval = setInterval(function () {
                    that.time = that.time - 1;
                    if (that.time === 0) {
                        clearInterval(that.Interval);
                        that.$router.push({ path: '/' });
                    }
                }, 1000);
            }
        },
        mounted () {
            this.interval();
        }

    };
</script>

<style scoped>
    body {
        text-align: center
    }

    h1 {
        font-family: "微软雅黑"
    }

    .notfound {
        text-align: center;
    }
</style>
