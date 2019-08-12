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
            path: '/404',
            name: 'NotFoundPage',
            component: () => import('../views/NotFoundPage/NotFoundPage')
        }
    ]
});

router.beforeEach((to, from, next) => {
    NProgress.start();
    if (!store.getters.headerMenu.hasLoad) {
        store.dispatch('getMenu').then((menus) => { // 根据roles权限生成可访问的路由表
            // eslint-disable-next-line no-unused-expressions,no-unused-vars
            let routes = {
                path: '/',
                name: 'home',
                component: () => import('../views/home/Home'),
                children: []
            };
            let menuKeys = Object.keys(menus);
            menuKeys.map((key) => {
                let route = {
                    path: menus[key].id,
                    name: menus[key].id,
                    component: CommonPanel,
                    meta: {
                        commonId: menus[key].id
                    },
                    children: []
                };
                let itemKeys = Object.keys(menus[key].item);
                itemKeys.map((itemKey) => {
                    let item = [
                        {
                            path: menus[key].item[itemKey].id,
                            name: menus[key].item[itemKey].id,
                            meta: {
                                commonId: menus[key].id,
                                objId: menus[key].item[itemKey].id
                            },
                            component: CommonMainPanel
                        },
                        {
                            path: `${menus[key].item[itemKey].id}/add`,
                            name: `${menus[key].item[itemKey].id}opt`,
                            meta: {
                                commonId: menus[key].id,
                                objId: menus[key].item[itemKey].id
                            },
                            component: EditDataMainPanel
                        },
                        {
                            path: `${menus[key].item[itemKey].id}/edit/:id`,
                            name: `${menus[key].item[itemKey].id}edit`,
                            meta: {
                                commonId: menus[key].id,
                                objId: menus[key].item[itemKey].id
                            },
                            component: EditDataMainPanel
                        }
                    ];
                    route.children.push(...item);
                });
                routes.children.push(route);
            });
            router.addRoutes([routes, {
                path: '*',
                redirect: '/404'
            }]);
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
