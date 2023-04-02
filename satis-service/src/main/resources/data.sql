INSERT INTO kategori (id, kategori_adi)
VALUES (nextval('kategori_id_seq'), 'Baharat');
INSERT INTO kategori (id, kategori_adi)
VALUES (nextval('kategori_id_seq'), 'Elektronik');
INSERT INTO kategori (id, kategori_adi)
VALUES (nextval('kategori_id_seq'), 'Yiyecek');
INSERT INTO kategori (id, kategori_adi)
VALUES (nextval('kategori_id_seq'), 'İçecek');
INSERT INTO kategori (id, kategori_adi)
VALUES (nextval('kategori_id_seq'), 'Acı');
INSERT INTO kategori (id, kategori_adi)
VALUES (nextval('kategori_id_seq'), 'Tatlı');
INSERT INTO kategori (id, kategori_adi)
VALUES (nextval('kategori_id_seq'), 'Meyve');
INSERT INTO kategori (id, kategori_adi)
VALUES (nextval('kategori_id_seq'), 'Şarküteri');

INSERT INTO urun (id, barkod, fiyat, kdv, urun_adi, kategori_id)
VALUES (nextval('urun_id_seq'), null, 1500.00, 18, 'BILGISAYAR', 2);
INSERT INTO urun (id, barkod, fiyat, kdv, urun_adi, kategori_id)
VALUES (nextval('urun_id_seq'), null, 55.00, 8, 'KIRMIZI ÇAYKUR', 1);
INSERT INTO urun (id, barkod, fiyat, kdv, urun_adi, kategori_id)
VALUES (nextval('urun_id_seq'), null, 56.00, 8, 'SARI LIPTON', 1);
INSERT INTO urun (id, barkod, fiyat, kdv, urun_adi, kategori_id)
VALUES (nextval('urun_id_seq'), null, 60.00, 8, 'EYNESIL', 1);
INSERT INTO urun (id, barkod, fiyat, kdv, urun_adi, kategori_id)
VALUES (nextval('urun_id_seq'), null, 180.00, 18, 'POWERBANK', 2);
INSERT INTO urun (id, barkod, fiyat, kdv, urun_adi, kategori_id)
VALUES (nextval('urun_id_seq'), null, 50.00, 8, 'TOZ BIBER', 3);
INSERT INTO urun (id, barkod, fiyat, kdv, urun_adi, kategori_id)
VALUES (nextval('urun_id_seq'), null, 15.00, 8, 'KIMYON', 3);
INSERT INTO urun (id, barkod, fiyat, kdv, urun_adi, kategori_id)
VALUES (nextval('urun_id_seq'), null, 70.00, 18, 'KAHVE', 4);
INSERT INTO urun (id, barkod, fiyat, kdv, urun_adi, kategori_id)
VALUES (nextval('urun_id_seq'), null, 15.00, 8, 'TATLI BIBER', 6);
INSERT INTO urun (id, barkod, fiyat, kdv, urun_adi, kategori_id)
VALUES (nextval('urun_id_seq'), null, 25.00, 8, 'MUZ', 7);
INSERT INTO urun (id, barkod, fiyat, kdv, urun_adi, kategori_id)
VALUES (nextval('urun_id_seq'), null, 19.00, 8, 'ÇILEK', 7);
INSERT INTO urun (id, barkod, fiyat, kdv, urun_adi, kategori_id)
VALUES (nextval('urun_id_seq'), null, 19.00, 17, 'SÜT', 8);

INSERT INTO satis (id, musteri_id, satilan_adet, satis_zamani, urun_id)
VALUES (nextval('satis_id_seq'), 2, 2, '2022-10-15 21:09:58.603556', 6);
INSERT INTO satis (id, musteri_id, satilan_adet, satis_zamani, urun_id)
VALUES (nextval('satis_id_seq'), 1, 2, '2022-10-15 21:10:01.141705', 2);
INSERT INTO satis (id, musteri_id, satilan_adet, satis_zamani, urun_id)
VALUES (nextval('satis_id_seq'), 1, 2, '2022-10-15 21:15:50.908758', 3);
INSERT INTO satis (id, musteri_id, satilan_adet, satis_zamani, urun_id)
VALUES (nextval('satis_id_seq'), 2, 1, '2022-10-15 21:15:55.195020', 4);
INSERT INTO satis (id, musteri_id, satilan_adet, satis_zamani, urun_id)
VALUES (nextval('satis_id_seq'), 1, 1, '2022-10-15 21:09:40.923920', 7);
INSERT INTO satis (id, musteri_id, satilan_adet, satis_zamani, urun_id)
VALUES (nextval('satis_id_seq'), 2, 5, '2022-10-15 21:09:43.456125', 8);
INSERT INTO satis (id, musteri_id, satilan_adet, satis_zamani, urun_id)
VALUES (nextval('satis_id_seq'), 2, 2, '2022-10-15 21:09:46.962158', 5);
INSERT INTO satis (id, musteri_id, satilan_adet, satis_zamani, urun_id)
VALUES (nextval('satis_id_seq'), 1, 1, '2022-10-30 00:01:02.831704', 1);
INSERT INTO satis (id, musteri_id, satilan_adet, satis_zamani, urun_id)
VALUES (nextval('satis_id_seq'), 1, 1, '2022-10-30 00:01:02.873119', 2);
INSERT INTO satis (id, musteri_id, satilan_adet, satis_zamani, urun_id)
VALUES (nextval('satis_id_seq'), 1, 1, '2022-10-30 00:01:02.889700', 3);
INSERT INTO satis (id, musteri_id, satilan_adet, satis_zamani, urun_id)
VALUES (nextval('satis_id_seq'), 1, 1, '2022-10-30 00:01:02.905714', 4);
INSERT INTO satis (id, musteri_id, satilan_adet, satis_zamani, urun_id)
VALUES (nextval('satis_id_seq'), 2, 1, '2022-10-30 00:01:17.891070', 1);
INSERT INTO satis (id, musteri_id, satilan_adet, satis_zamani, urun_id)
VALUES (nextval('satis_id_seq'), 2, 1, '2022-10-30 00:01:17.913258', 5);


INSERT INTO kasa_hareketi (id, aciklama, tutar)
VALUES (nextval('kasa_hareketi_id_seq'), 'ürün satışı', 1500.00);
INSERT INTO kasa_hareketi (id, aciklama, tutar)
VALUES (nextval('kasa_hareketi_id_seq'), 'ürün satışı', 55.00);
INSERT INTO kasa_hareketi (id, aciklama, tutar)
VALUES (nextval('kasa_hareketi_id_seq'), 'ürün satışı', 56.00);
INSERT INTO kasa_hareketi (id, aciklama, tutar)
VALUES (nextval('kasa_hareketi_id_seq'), 'ürün satışı', 60.00);
INSERT INTO kasa_hareketi (id, aciklama, tutar)
VALUES (nextval('kasa_hareketi_id_seq'), 'ürün satışı', 1500.00);
INSERT INTO kasa_hareketi (id, aciklama, tutar)
VALUES (nextval('kasa_hareketi_id_seq'), 'ürün satışı', 180.00);
INSERT INTO kasa_hareketi (id, aciklama, tutar)
VALUES (nextval('kasa_hareketi_id_seq'), 'ürün satışı', 50.00);
INSERT INTO kasa_hareketi (id, aciklama, tutar)
VALUES (nextval('kasa_hareketi_id_seq'), 'ürün satışı', 15.00);
INSERT INTO kasa_hareketi (id, aciklama, tutar)
VALUES (nextval('kasa_hareketi_id_seq'), 'ürün satışı', 70.00);
INSERT INTO kasa_hareketi (id, aciklama, tutar)
VALUES (nextval('kasa_hareketi_id_seq'), 'ürün satışı', 56.00);
INSERT INTO kasa_hareketi (id, aciklama, tutar)
VALUES (nextval('kasa_hareketi_id_seq'), 'ürün satışı', 60.00);
INSERT INTO kasa_hareketi (id, aciklama, tutar)
VALUES (nextval('kasa_hareketi_id_seq'), 'ürün satışı', 180.00);
INSERT INTO kasa_hareketi (id, aciklama, tutar)
VALUES (nextval('kasa_hareketi_id_seq'), 'ürün satışı', 50.00);
INSERT INTO kasa_hareketi (id, aciklama, tutar)
VALUES (nextval('kasa_hareketi_id_seq'), 'ürün satışı', 15.00);
INSERT INTO kasa_hareketi (id, aciklama, tutar)
VALUES (nextval('kasa_hareketi_id_seq'), 'ürün satışı', 70.00);
INSERT INTO kasa_hareketi (id, aciklama, tutar)
VALUES (nextval('kasa_hareketi_id_seq'), 'ürün satışı', 56.00);
INSERT INTO kasa_hareketi (id, aciklama, tutar)
VALUES (nextval('kasa_hareketi_id_seq'), 'ürün satışı', 60.00);
INSERT INTO kasa_hareketi (id, aciklama, tutar)
VALUES (nextval('kasa_hareketi_id_seq'), 'ürün satışı', 180.00);
INSERT INTO kasa_hareketi (id, aciklama, tutar)
VALUES (nextval('kasa_hareketi_id_seq'), 'ürün satışı', 50.00);
INSERT INTO kasa_hareketi (id, aciklama, tutar)
VALUES (nextval('kasa_hareketi_id_seq'), 'ürün satışı', 15.00);
INSERT INTO kasa_hareketi (id, aciklama, tutar)
VALUES (nextval('kasa_hareketi_id_seq'), 'ürün satışı', 70.00);
INSERT INTO kasa_hareketi (id, aciklama, tutar)
VALUES (nextval('kasa_hareketi_id_seq'), 'ürün satışı', 70.00);
INSERT INTO kasa_hareketi (id, aciklama, tutar)
VALUES (nextval('kasa_hareketi_id_seq'), 'ürün satışı', 56.00);
INSERT INTO kasa_hareketi (id, aciklama, tutar)
VALUES (nextval('kasa_hareketi_id_seq'), 'ürün satışı', 60.00);
INSERT INTO kasa_hareketi (id, aciklama, tutar)
VALUES (nextval('kasa_hareketi_id_seq'), 'ürün satışı', 180.00);
INSERT INTO kasa_hareketi (id, aciklama, tutar)
VALUES (nextval('kasa_hareketi_id_seq'), 'ürün satışı', 50.00);
INSERT INTO kasa_hareketi (id, aciklama, tutar)
VALUES (nextval('kasa_hareketi_id_seq'), 'ürün satışı', 15.00);
INSERT INTO kasa_hareketi (id, aciklama, tutar)
VALUES (nextval('kasa_hareketi_id_seq'), 'ürün satışı', 70.00);
INSERT INTO kasa_hareketi (id, aciklama, tutar)
VALUES (nextval('kasa_hareketi_id_seq'), 'ürün satışı', 70.00);
INSERT INTO kasa_hareketi (id, aciklama, tutar)
VALUES (nextval('kasa_hareketi_id_seq'), 'ürün satışı', 15.00);
INSERT INTO kasa_hareketi (id, aciklama, tutar)
VALUES (nextval('kasa_hareketi_id_seq'), 'ürün satışı', 50.00);
INSERT INTO kasa_hareketi (id, aciklama, tutar)
VALUES (nextval('kasa_hareketi_id_seq'), 'ürün satışı', 60.00);
INSERT INTO kasa_hareketi (id, aciklama, tutar)
VALUES (nextval('kasa_hareketi_id_seq'), 'ürün satışı', 56.00);
INSERT INTO kasa_hareketi (id, aciklama, tutar)
VALUES (nextval('kasa_hareketi_id_seq'), 'ürün satışı', 50.00);
INSERT INTO kasa_hareketi (id, aciklama, tutar)
VALUES (nextval('kasa_hareketi_id_seq'), 'ürün satışı', 50.00);
INSERT INTO kasa_hareketi (id, aciklama, tutar)
VALUES (nextval('kasa_hareketi_id_seq'), 'ürün satışı', 15.00);
INSERT INTO kasa_hareketi (id, aciklama, tutar)
VALUES (nextval('kasa_hareketi_id_seq'), 'ürün satışı', 70.00);
INSERT INTO kasa_hareketi (id, aciklama, tutar)
VALUES (nextval('kasa_hareketi_id_seq'), 'ürün satışı', 50.00);
INSERT INTO kasa_hareketi (id, aciklama, tutar)
VALUES (nextval('kasa_hareketi_id_seq'), 'ürün satışı', 15.00);
INSERT INTO kasa_hareketi (id, aciklama, tutar)
VALUES (nextval('kasa_hareketi_id_seq'), 'ürün satışı', 70.00);
INSERT INTO kasa_hareketi (id, aciklama, tutar)
VALUES (nextval('kasa_hareketi_id_seq'), 'ürün satışı', 50.00);
INSERT INTO kasa_hareketi (id, aciklama, tutar)
VALUES (nextval('kasa_hareketi_id_seq'), 'ürün satışı', 15.00);
INSERT INTO kasa_hareketi (id, aciklama, tutar)
VALUES (nextval('kasa_hareketi_id_seq'), 'ürün satışı', 70.00);
INSERT INTO kasa_hareketi (id, aciklama, tutar)
VALUES (nextval('kasa_hareketi_id_seq'), 'ürün satışı', 50.00);
INSERT INTO kasa_hareketi (id, aciklama, tutar)
VALUES (nextval('kasa_hareketi_id_seq'), 'ürün satışı', 15.00);
INSERT INTO kasa_hareketi (id, aciklama, tutar)
VALUES (nextval('kasa_hareketi_id_seq'), 'ürün satışı', 70.00);
INSERT INTO kasa_hareketi (id, aciklama, tutar)
VALUES (nextval('kasa_hareketi_id_seq'), 'ürün satışı', 280.00);
INSERT INTO kasa_hareketi (id, aciklama, tutar)
VALUES (nextval('kasa_hareketi_id_seq'), 'ürün satışı', 60.00);
INSERT INTO kasa_hareketi (id, aciklama, tutar)
VALUES (nextval('kasa_hareketi_id_seq'), 'ürün satışı', 150.00);
INSERT INTO kasa_hareketi (id, aciklama, tutar)
VALUES (nextval('kasa_hareketi_id_seq'), 'ürün satışı', 150.00);
INSERT INTO kasa_hareketi (id, aciklama, tutar)
VALUES (nextval('kasa_hareketi_id_seq'), 'ürün satışı', 280.00);
INSERT INTO kasa_hareketi (id, aciklama, tutar)
VALUES (nextval('kasa_hareketi_id_seq'), 'ürün satışı', 60.00);
INSERT INTO kasa_hareketi (id, aciklama, tutar)
VALUES (nextval('kasa_hareketi_id_seq'), 'ürün satışı', 1.25);
INSERT INTO kasa_hareketi (id, aciklama, tutar)
VALUES (nextval('kasa_hareketi_id_seq'), 'ürün satışı', 1.25);
INSERT INTO kasa_hareketi (id, aciklama, tutar)
VALUES (nextval('kasa_hareketi_id_seq'), 'ürün satışı', 1.25);
INSERT INTO kasa_hareketi (id, aciklama, tutar)
VALUES (nextval('kasa_hareketi_id_seq'), 'ürün satışı', 1.25);
INSERT INTO kasa_hareketi (id, aciklama, tutar)
VALUES (nextval('kasa_hareketi_id_seq'), 'ürün satışı', 70.00);
INSERT INTO kasa_hareketi (id, aciklama, tutar)
VALUES (nextval('kasa_hareketi_id_seq'), 'ürün satışı', 70.00);
INSERT INTO kasa_hareketi (id, aciklama, tutar)
VALUES (nextval('kasa_hareketi_id_seq'), 'ürün satışı', 1.25);
INSERT INTO kasa_hareketi (id, aciklama, tutar)
VALUES (nextval('kasa_hareketi_id_seq'), 'ürün satışı', 1.25);
INSERT INTO kasa_hareketi (id, aciklama, tutar)
VALUES (nextval('kasa_hareketi_id_seq'), 'ürün satışı', 1.25);
INSERT INTO kasa_hareketi (id, aciklama, tutar)
VALUES (nextval('kasa_hareketi_id_seq'), 'ürün satışı', 1.25);
INSERT INTO kasa_hareketi (id, aciklama, tutar)
VALUES (nextval('kasa_hareketi_id_seq'), 'ürün satışı', 5.00);
INSERT INTO kasa_hareketi (id, aciklama, tutar)
VALUES (nextval('kasa_hareketi_id_seq'), 'ürün satışı', 6.25);
INSERT INTO kasa_hareketi (id, aciklama, tutar)
VALUES (nextval('kasa_hareketi_id_seq'), 'ürün satışı', 6.25);
INSERT INTO kasa_hareketi (id, aciklama, tutar)
VALUES (nextval('kasa_hareketi_id_seq'), 'ürün satışı', 6.25);
INSERT INTO kasa_hareketi (id, aciklama, tutar)
VALUES (nextval('kasa_hareketi_id_seq'), 'ürün satışı', 70.00);
INSERT INTO kasa_hareketi (id, aciklama, tutar)
VALUES (nextval('kasa_hareketi_id_seq'), 'ürün satışı', 1.25);
INSERT INTO kasa_hareketi (id, aciklama, tutar)
VALUES (nextval('kasa_hareketi_id_seq'), 'ürün satışı', 1.25);
INSERT INTO kasa_hareketi (id, aciklama, tutar)
VALUES (nextval('kasa_hareketi_id_seq'), 'ürün satışı', 1.25);
INSERT INTO kasa_hareketi (id, aciklama, tutar)
VALUES (nextval('kasa_hareketi_id_seq'), 'ürün satışı', 1.25);
INSERT INTO kasa_hareketi (id, aciklama, tutar)
VALUES (nextval('kasa_hareketi_id_seq'), 'ürün satışı', 5.00);
INSERT INTO kasa_hareketi (id, aciklama, tutar)
VALUES (nextval('kasa_hareketi_id_seq'), 'ürün satışı', 6.25);
INSERT INTO kasa_hareketi (id, aciklama, tutar)
VALUES (nextval('kasa_hareketi_id_seq'), 'ürün satışı', 6.25);
INSERT INTO kasa_hareketi (id, aciklama, tutar)
VALUES (nextval('kasa_hareketi_id_seq'), 'ürün satışı', 6.25);
INSERT INTO kasa_hareketi (id, aciklama, tutar)
VALUES (nextval('kasa_hareketi_id_seq'), 'ürün satışı', 70.00);
INSERT INTO kasa_hareketi (id, aciklama, tutar)
VALUES (nextval('kasa_hareketi_id_seq'), 'ürün satışı', 1.25);
INSERT INTO kasa_hareketi (id, aciklama, tutar)
VALUES (nextval('kasa_hareketi_id_seq'), 'ürün satışı', 1.25);
INSERT INTO kasa_hareketi (id, aciklama, tutar)
VALUES (nextval('kasa_hareketi_id_seq'), 'ürün satışı', 1.25);
INSERT INTO kasa_hareketi (id, aciklama, tutar)
VALUES (nextval('kasa_hareketi_id_seq'), 'ürün satışı', 1.25);
INSERT INTO kasa_hareketi (id, aciklama, tutar)
VALUES (nextval('kasa_hareketi_id_seq'), 'ürün satışı', 5.00);
INSERT INTO kasa_hareketi (id, aciklama, tutar)
VALUES (nextval('kasa_hareketi_id_seq'), 'ürün satışı', 6.25);
INSERT INTO kasa_hareketi (id, aciklama, tutar)
VALUES (nextval('kasa_hareketi_id_seq'), 'ürün satışı', 6.25);
INSERT INTO kasa_hareketi (id, aciklama, tutar)
VALUES (nextval('kasa_hareketi_id_seq'), 'ürün satışı', 6.25);
INSERT INTO kasa_hareketi (id, aciklama, tutar)
VALUES (nextval('kasa_hareketi_id_seq'), 'ürün satışı', 6.25);
INSERT INTO kasa_hareketi (id, aciklama, tutar)
VALUES (nextval('kasa_hareketi_id_seq'), 'ürün satışı', 6.25);
INSERT INTO kasa_hareketi (id, aciklama, tutar)
VALUES (nextval('kasa_hareketi_id_seq'), 'ürün satışı', 6.25);
INSERT INTO kasa_hareketi (id, aciklama, tutar)
VALUES (nextval('kasa_hareketi_id_seq'), 'ürün satışı', 6.25);
INSERT INTO kasa_hareketi (id, aciklama, tutar)
VALUES (nextval('kasa_hareketi_id_seq'), 'ürün satışı', 8.75);
INSERT INTO kasa_hareketi (id, aciklama, tutar)
VALUES (nextval('kasa_hareketi_id_seq'), 'ürün satışı', 8.75);
INSERT INTO kasa_hareketi (id, aciklama, tutar)
VALUES (nextval('kasa_hareketi_id_seq'), 'ürün satışı', 15.00);
INSERT INTO kasa_hareketi (id, aciklama, tutar)
VALUES (nextval('kasa_hareketi_id_seq'), 'ürün satışı', 25.00);
INSERT INTO kasa_hareketi (id, aciklama, tutar)
VALUES (nextval('kasa_hareketi_id_seq'), 'ürün satışı', 19.00);
INSERT INTO kasa_hareketi (id, aciklama, tutar)
VALUES (nextval('kasa_hareketi_id_seq'), 'ürün satışı', 1.25);
INSERT INTO kasa_hareketi (id, aciklama, tutar)
VALUES (nextval('kasa_hareketi_id_seq'), 'ürün satışı', 30.00);
INSERT INTO kasa_hareketi (id, aciklama, tutar)
VALUES (nextval('kasa_hareketi_id_seq'), 'ürün satışı', 50.00);
INSERT INTO kasa_hareketi (id, aciklama, tutar)
VALUES (nextval('kasa_hareketi_id_seq'), 'ürün satışı', 38.00);
INSERT INTO kasa_hareketi (id, aciklama, tutar)
VALUES (nextval('kasa_hareketi_id_seq'), 'ürün satışı', 2.50);
INSERT INTO kasa_hareketi (id, aciklama, tutar)
VALUES (nextval('kasa_hareketi_id_seq'), 'ürün satışı', 100.00);
INSERT INTO kasa_hareketi (id, aciklama, tutar)
VALUES (nextval('kasa_hareketi_id_seq'), 'ürün satışı', 76.00);
INSERT INTO kasa_hareketi (id, aciklama, tutar)
VALUES (nextval('kasa_hareketi_id_seq'), 'ürün satışı', 171.00);
