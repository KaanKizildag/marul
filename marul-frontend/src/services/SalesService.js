import {httpRequest} from "./baseHTTP.js"

export default class SalesService {
    sonSatislariGetir() {
        return httpRequest.get("/satis-service/v1/satis/son-satislari-getir")
    }
}