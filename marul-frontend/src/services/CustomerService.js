import {httpRequest} from "./baseHTTP.js"

export class CustomerService {

    findAllCustomer() {
        return httpRequest.get("/musteri-service/v1/musteri/findAll")
    }

    saveCustomer(customer) {
        return httpRequest.post("/musteri-service/v1/musteri/save", customer)
    }

    updateCustomer(customer) {
        return httpRequest.put("/musteri-service/v1/musteri/update", customer)
    }

    deleteById(id) {
        return httpRequest.delete("/musteri-service/v1/musteri/delete?id=" + id)
    }
}