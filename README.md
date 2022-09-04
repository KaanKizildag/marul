```  

.___  ___.      ___      .______       __    __   __      
|   \/   |     /   \     |   _  \     |  |  |  | |  |     
|  \  /  |    /  ^  \    |  |_)  |    |  |  |  | |  |     
|  |\/|  |   /  /_\  \   |      /     |  |  |  | |  |     
|  |  |  |  /  _____  \  |  |\  \----.|  `--'  | |  `----.
|__|  |__| /__/     \__\ | _| `._____| \______/  |_______|
                                                          

```

## Yapılacaklar Listesi

- Satis Servisi
  - Ürün eklenecek
    - Ürün id, ürün adı(unique), fiyat, kdv oranı bilgilerini tutacak.
    - CRUD
    - Yeni bir ürün eklendiğinde stok tablosuna 0 değerinde bir kayıt atılacak.
  - Stok eklenecek.
    - id, ürün id ve stok adedi bilgilerini tutacak.
    - CRUD
    - Ürün id si ile stok sorgulayacak. Yetersiz stok oldugunda **StokYeterliDegilException** fırlatacak.
  - Depo eklenecek.
    - Depo Adı, kasaTutari, stoklistesi
    - Depo giriş çıkışlarında kasa tutarı güncellenecek _hareket tablosu oluşturulabilir_
  - Kasa hareketleri eklenecek.
    - Tüm alım-satım işlemlerinden sonra hareket tablosuna kayıt düşmelidir.
    - DepoId, tarih(dakika saat gün ay yıl bilgisi), tutar