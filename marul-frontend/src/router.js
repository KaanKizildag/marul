import {createRouter, createWebHistory} from "vue-router";
import Product from "./components/product/Product.vue";


const routes = [
    { path: "/", component: Product },
    { path: "/product", component: Product },
];

export const router = createRouter({
    routes: routes,
    history: createWebHistory(),
})

export default router;