import {httpRequest} from "./baseHTTP.js"

export class SalesService {
    getLastSales() {
        return httpRequest.get("/satis-service/v1/satis/son-satislari-getir")
    }

    save(salesDto) {
        return httpRequest.post("/satis-service/v1/satis/save",salesDto)
    }
}