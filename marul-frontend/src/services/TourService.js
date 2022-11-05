import {httpRequest} from "./baseHTTP.js"

export class TourService {
    findAll() {
        return httpRequest.get("/musteri-service/v1/tur/findAll")
    }
}