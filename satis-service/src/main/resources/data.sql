INSERT INTO kategori (id, kategori_adi)
VALUES (nextval('kategori_id_seq'), 'Baharat');
INSERT INTO kategori (id, kategori_adi)
VALUES (nextval('kategori_id_seq'), 'Elektronik');
INSERT INTO kategori (id, kategori_adi)
VALUES (nextval('kategori_id_seq'), 'Yiyecek');
INSERT INTO kategori (id, kategori_adi)
VALUES (nextval('kategori_id_seq'), 'İçecek');


INSERT INTO urun (id, barkod, fiyat, kategori_id, kdv, urun_adi)
VALUES (nextval('urun_id_seq'), null, 1500.00, 2, 18, 'BILGISAYAR');
INSERT INTO urun (id, barkod, fiyat, kategori_id, kdv, urun_adi)
VALUES (nextval('urun_id_seq'), null, 55.00, 1, 8, 'KIRMIZI ÇAYKUR');
INSERT INTO urun (id, barkod, fiyat, kategori_id, kdv, urun_adi)
VALUES (nextval('urun_id_seq'), null, 56.00, 1, 8, 'SARI LIPTON');
INSERT INTO urun (id, barkod, fiyat, kategori_id, kdv, urun_adi)
VALUES (nextval('urun_id_seq'), null, 60.00, 1, 8, 'EYNESIL');
INSERT INTO urun (id, barkod, fiyat, kategori_id, kdv, urun_adi)
VALUES (nextval('urun_id_seq'), null, 180.00, 2, 18, 'POWERBANK');
INSERT INTO urun (id, barkod, fiyat, kategori_id, kdv, urun_adi)
VALUES (nextval('urun_id_seq'), null, 50.00, 3, 8, 'TOZ BIBER');
INSERT INTO urun (id, barkod, fiyat, kategori_id, kdv, urun_adi)
VALUES (nextval('urun_id_seq'), null, 15.00, 3, 8, 'KIMYON');
INSERT INTO urun (id, barkod, fiyat, kategori_id, kdv, urun_adi)
VALUES (nextval('urun_id_seq'), null, 70.00, 4, 18, 'KAHVE');


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
