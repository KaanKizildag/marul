import {httpRequest} from "./baseHTTP.js"

export class KasaHareketiService {

    kasaToplamTutariGetir() {
        return httpRequest.get("/satis-service/v1/kasa-hareketi/kasa-toplam-tutari")
    }
}