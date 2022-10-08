import axios from "axios";


const BASE_URL = import.meta.env.VITE_BASE_URL;

export class ProductService {


    findAllPrdocut() {
        return axios.get(BASE_URL + "/satis-service/v1/urun/findAll")
    }

    savePrdocut(product) {
        return axios.post(BASE_URL + "/satis-service/v1/urun/save", product, {
            headers: {
                'Content-Type': 'application/json',
                "Access-Control-Allow-Origin": 'http://127.0.0.1:5173/',
                "Access-Control-Allow-Credentials": "true",
                "Access-Control-Expose-Headers": "Set-Cookie",
                "Access-Control-Allow-Headers": "Content-Type, x-xsrf-token, X-Requested-With, Accept, Expires, Last-Modified, Cache-Control",
                "Access-Control-Allow-Methods": "GET, POST, OPTIONS",
                'Accept': '*/*'
            },
        })
    }

    haftalikSatislariGetir() {
        return axios.get(BASE_URL + "/satis-service/v1/satis/haftalik-satislari-getir")
    }
}