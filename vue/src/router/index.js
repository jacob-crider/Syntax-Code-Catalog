import Vue from 'vue'
import Router from 'vue-router'
import Home from '../views/Home.vue'
import Login from '../views/Login.vue'
import Logout from '../views/Logout.vue'
import Register from '../views/Register.vue'
import store from '../store/index'
import AddExample from '../views/AddExample.vue'
import LanguageView from '../views/LanguageView.vue'
import Compiler from '../views/Compiler.vue'


Vue.use(Router)

/**
 * The Vue Router is used to "direct" the browser to render a specific view component
 * inside of App.vue depending on the URL.
 *
 * It also is used to detect whether or not a route requires the user to have first authenticated.
 * If the user has not yet authenticated (and needs to) they are redirected to /login
 * If they have (or don't need to) they're allowed to go about their way.
 */

const router = new Router({
  mode: 'history',
  base: process.env.BASE_URL,
  routes: [
    {
      path: '/',
      name: 'home',
      component: Home,
      meta: {
        requiresAuth: true,
        requiresAdmin: false
      }
    },
    {
      path: "/login",
      name: "login",
      component: Login,
      meta: {
        requiresAuth: false,
        requiresAdmin: false
      }
    },
    {
      path: "/logout",
      name: "logout",
      component: Logout,
      meta: {
        requiresAuth: false,
        requiresAdmin: false
      }
    },
    {
      path: "/register",
      name: "register",
      component: Register,
      meta: {
        requiresAuth: false,
        requiresAdmin: false
      }
    },
    {
      path: '/addExample',
      name: 'addExample',
      component: AddExample,
      meta: {
        requiresAuth: true,
        requiresAdmin: false
      }
    },
    {
      path: '/language',
      name: 'language',
      component: LanguageView,
      meta: {
        requiresAuth: true,
        requiresAdmin: true
      }
    },
    {
      path: '/compiler',
      name: 'compiler',
      component: Compiler,
      meta: {
        requiresAuth: true,
        requiresAdmin: false
      }
    }
  ]
})

router.beforeEach((to, from, next) => {
  // Determine if the route requires Authentication
  const requiresAuth = to.matched.some(x => x.meta.requiresAuth);

  // If it does and they are not logged in, send the user to "/login"
  if (requiresAuth && store.state.token === '') {
    next("/login");
  } else {
    if (to.meta.requiresAdmin && store.state.user.authorities[0].name !== 'ROLE_ADMIN') {
      next('/');
    } else {
      next();
    }
  }
});

export default router;
