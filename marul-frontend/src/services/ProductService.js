import { httpRequest } from "./baseHTTP.js"

export class ProductService {

    findAllPrdocut() {
        return httpRequest.get("/satis-service/v1/urun/findAll")
    }

    savePrdocut(product) {
        return httpRequest.post("/satis-service/v1/urun/save", product)
    }

    haftalikSatislariGetir() {
        return httpRequest.get("/satis-service/v1/satis/onceki-haftaya-gore-satis-dustu-mu")
    }
}