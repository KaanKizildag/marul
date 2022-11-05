import {createRouter, createWebHistory} from "vue-router";
import Home from "./components/home/Home.vue";
import Product from "./components/product/Product.vue";
import Sales from "./components/sales/Sales.vue";
import Customer from "./components/customer/Customer.vue";


const routes = [
    {path: "/", component: Home},
    {path: "/product", component: Product},
    {path: "/sales", component: Sales},
    {path: "/customers", component: Customer},
];

export const router = createRouter({
    routes: routes,
    history: createWebHistory(),
})

export default router;