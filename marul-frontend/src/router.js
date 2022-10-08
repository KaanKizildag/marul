import {createRouter, createWebHistory} from "vue-router";
import Home from "./components/home/Home.vue";
import Product from "./components/product/Product.vue";


const routes = [
    { path: "/", component: Home },
    { path: "/product", component: Product },
];

export const router = createRouter({
    routes: routes,
    history: createWebHistory(),
})

export default router;