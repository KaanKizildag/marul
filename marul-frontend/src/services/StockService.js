import {httpRequest} from "./baseHTTP.js"

export class StockService {
    getCriticalStockInfo() {
        return httpRequest.get("/stok-service/v1/stok/kritik-stoklari-getir")
    }
}