import axios from "axios";


const BASE_URL = import.meta.env.VITE_BASE_URL;

export class ProductService {


    findAllPrdocut() {
        return axios.get(BASE_URL + "/satis-service/v1/urun/findAll")
    }

    haftalikSatislariGetir() {
        return axios.get(BASE_URL + "/satis-service/v1/satis/haftalik-satislari-getir")
    }
}