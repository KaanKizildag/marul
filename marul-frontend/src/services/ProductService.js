import {httpRequest} from "./baseHTTP.js"

export class ProductService {

    findAllPrdocut() {
        return httpRequest.get("/satis-service/v1/urun/findAll")
    }

    savePrdocut(product) {
        return httpRequest.post("/satis-service/v1/urun/save", product, {
            headers: {
                "Accept": "application/json, application/json;charset=UTF-8",
                "Content-Type": "application/json;charset=UTF-8"
            }
        })
    }

    haftalikSatislariGetir() {
        return httpRequest.get("/satis-service/v1/satis/onceki-haftaya-gore-satis-dustu-mu")
    }
}