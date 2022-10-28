import {httpRequest} from "./baseHTTP.js"

export class CategoryService {
    findAllCategories() {
        return httpRequest.get("/satis-service/v1/kategori/findAll")
    }
}