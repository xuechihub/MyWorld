import Vue from 'vue';
import Router from 'vue-router';
import NProgress from 'nprogress';
import 'nprogress/nprogress.css';
import store from '../store';
import CommonPanel from '../views/home/MainPanel/CommonPanel/CommonPanel';
import CommonMainPanel from '../views/home/MainPanel/CommonPanel/CommonMainPanel/CommonMainPanel';
import EditDataMainPanel from '../views/home/MainPanel/CommonPanel/EditDataMainPanel/EditDataMainPanel';

Vue.use(Router);
const router = new Router({
    mode: 'history',
    routes: [
        {
            path: '/login',
            name: 'login',
            component: () => import('../views/login/index')
        },
        {
            path: '/',
            name: 'home',
            component: () => import('../views/home/Home'),
            children: [
                {
                    path: ':commonId',
                    name: 'commonId',
                    component: CommonPanel,
                    children: [
                        {
                            path: ':objId',
                            name: 'objId',
                            component: CommonMainPanel
                        },
                        {
                            path: ':objId/:optType',
                            name: 'opt',
                            component: EditDataMainPanel
                        },
                        {
                            path: ':objId/:optType/:id',
                            name: 'edit',
                            component: EditDataMainPanel
                        }
                    ]
                }
            ]
        }

    ]
});

router.beforeEach((to, from, next) => {
    NProgress.start();
    if (!store.getters.headerMenu.hasLoad) {
        store.dispatch('getMenu').then(() => { // 根据roles权限生成可访问的路由表
            // router.addRoutes(store.getters.addRouters); // 动态添加可访问路由表
            NProgress.done();
        });
    }
    next();
});

router.beforeResolve((to, from, next) => {
    next();
});

router.afterEach((to, from) => {
    NProgress.done();
});

export default router;
