import {createRouter, createWebHistory} from "vue-router";
import Home from "./components/home/Home.vue";
import Product from "./components/product/Product.vue";
import Sales from "./components/sales/Sales.vue";


const routes = [
    {path: "/", component: Home},
    {path: "/product", component: Product},
    {path: "/sales", component: Sales},
];

export const router = createRouter({
    routes: routes,
    history: createWebHistory(),
})

export default router;