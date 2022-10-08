import axios from "axios";


const BASE_URL = import.meta.env.VITE_BASE_URL;

export class ProductService {


    findAllPrdocut() {
        return axios.get(BASE_URL + "/satis-service/v1/urun/findAll")
    }

    savePrdocut(product) {
        return axios.post(BASE_URL + "/satis-service/v1/urun/save", product, {
            headers: {
                "Accept": "application/json, application/json;charset=UTF-8",
                "Content-Type": "application/json;charset=UTF-8"
            }
        })
    }

    haftalikSatislariGetir() {
        return axios.get(BASE_URL + "/satis-service/v1/satis/haftalik-satislari-getir")
    }
}