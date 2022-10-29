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
INSERT INTO satis (id, musteri_id, satilan_adet, satis_zamani, urun_id)
VALUES (nextval('satis_id_seq'), 2, 1, '2022-10-30 00:01:17.934907', 6);
INSERT INTO satis (id, musteri_id, satilan_adet, satis_zamani, urun_id)
VALUES (nextval('satis_id_seq'), 2, 1, '2022-10-30 00:01:17.952574', 7);
INSERT INTO satis (id, musteri_id, satilan_adet, satis_zamani, urun_id)
VALUES (nextval('satis_id_seq'), 2, 1, '2022-10-30 00:01:17.968864', 8);
INSERT INTO satis (id, musteri_id, satilan_adet, satis_zamani, urun_id)
VALUES (nextval('satis_id_seq'), 2, 1, '2022-10-30 00:01:43.942895', 3);
INSERT INTO satis (id, musteri_id, satilan_adet, satis_zamani, urun_id)
VALUES (nextval('satis_id_seq'), 2, 1, '2022-10-30 00:01:43.965200', 4);
INSERT INTO satis (id, musteri_id, satilan_adet, satis_zamani, urun_id)
VALUES (nextval('satis_id_seq'), 2, 1, '2022-10-30 00:01:43.979543', 5);
INSERT INTO satis (id, musteri_id, satilan_adet, satis_zamani, urun_id)
VALUES (nextval('satis_id_seq'), 2, 1, '2022-10-30 00:01:43.992704', 6);
INSERT INTO satis (id, musteri_id, satilan_adet, satis_zamani, urun_id)
VALUES (nextval('satis_id_seq'), 2, 1, '2022-10-30 00:01:44.007947', 7);
INSERT INTO satis (id, musteri_id, satilan_adet, satis_zamani, urun_id)
VALUES (nextval('satis_id_seq'), 2, 1, '2022-10-30 00:01:44.023873', 8);
INSERT INTO satis (id, musteri_id, satilan_adet, satis_zamani, urun_id)
VALUES (nextval('satis_id_seq'), 2, 1, '2022-10-30 00:01:56.041843', 3);
INSERT INTO satis (id, musteri_id, satilan_adet, satis_zamani, urun_id)
VALUES (nextval('satis_id_seq'), 2, 1, '2022-10-30 00:01:56.068248', 4);
INSERT INTO satis (id, musteri_id, satilan_adet, satis_zamani, urun_id)
VALUES (nextval('satis_id_seq'), 2, 1, '2022-10-30 00:01:56.084813', 5);
INSERT INTO satis (id, musteri_id, satilan_adet, satis_zamani, urun_id)
VALUES (nextval('satis_id_seq'), 2, 1, '2022-10-30 00:01:56.099349', 6);
INSERT INTO satis (id, musteri_id, satilan_adet, satis_zamani, urun_id)
VALUES (nextval('satis_id_seq'), 2, 1, '2022-10-30 00:01:56.113872', 7);
INSERT INTO satis (id, musteri_id, satilan_adet, satis_zamani, urun_id)
VALUES (nextval('satis_id_seq'), 2, 1, '2022-10-30 00:01:56.129730', 8);
INSERT INTO satis (id, musteri_id, satilan_adet, satis_zamani, urun_id)
VALUES (nextval('satis_id_seq'), 2, 1, '2022-10-30 00:01:56.145900', 8);
INSERT INTO satis (id, musteri_id, satilan_adet, satis_zamani, urun_id)
VALUES (nextval('satis_id_seq'), 2, 1, '2022-10-30 00:02:00.249848', 3);
INSERT INTO satis (id, musteri_id, satilan_adet, satis_zamani, urun_id)
VALUES (nextval('satis_id_seq'), 2, 1, '2022-10-30 00:02:00.265571', 4);
INSERT INTO satis (id, musteri_id, satilan_adet, satis_zamani, urun_id)
VALUES (nextval('satis_id_seq'), 2, 1, '2022-10-30 00:02:00.280638', 5);
INSERT INTO satis (id, musteri_id, satilan_adet, satis_zamani, urun_id)
VALUES (nextval('satis_id_seq'), 2, 1, '2022-10-30 00:02:00.300386', 6);
INSERT INTO satis (id, musteri_id, satilan_adet, satis_zamani, urun_id)
VALUES (nextval('satis_id_seq'), 2, 1, '2022-10-30 00:02:00.318567', 7);
INSERT INTO satis (id, musteri_id, satilan_adet, satis_zamani, urun_id)
VALUES (nextval('satis_id_seq'), 2, 1, '2022-10-30 00:02:00.336040', 8);
INSERT INTO satis (id, musteri_id, satilan_adet, satis_zamani, urun_id)
VALUES (nextval('satis_id_seq'), 2, 1, '2022-10-30 00:02:00.354557', 8);
INSERT INTO satis (id, musteri_id, satilan_adet, satis_zamani, urun_id)
VALUES (nextval('satis_id_seq'), 2, 1, '2022-10-30 00:02:00.369578', 7);
INSERT INTO satis (id, musteri_id, satilan_adet, satis_zamani, urun_id)
VALUES (nextval('satis_id_seq'), 2, 1, '2022-10-30 00:02:18.848231', 6);
INSERT INTO satis (id, musteri_id, satilan_adet, satis_zamani, urun_id)
VALUES (nextval('satis_id_seq'), 2, 1, '2022-10-30 00:02:18.868783', 4);
INSERT INTO satis (id, musteri_id, satilan_adet, satis_zamani, urun_id)
VALUES (nextval('satis_id_seq'), 2, 1, '2022-10-30 00:02:18.884481', 3);
INSERT INTO satis (id, musteri_id, satilan_adet, satis_zamani, urun_id)
VALUES (nextval('satis_id_seq'), 2, 1, '2022-10-30 00:02:34.156885', 6);
INSERT INTO satis (id, musteri_id, satilan_adet, satis_zamani, urun_id)
VALUES (nextval('satis_id_seq'), 2, 1, '2022-10-30 00:02:39.245331', 6);
INSERT INTO satis (id, musteri_id, satilan_adet, satis_zamani, urun_id)
VALUES (nextval('satis_id_seq'), 2, 1, '2022-10-30 00:02:39.261122', 7);
INSERT INTO satis (id, musteri_id, satilan_adet, satis_zamani, urun_id)
VALUES (nextval('satis_id_seq'), 2, 1, '2022-10-30 00:02:39.275088', 8);
INSERT INTO satis (id, musteri_id, satilan_adet, satis_zamani, urun_id)
VALUES (nextval('satis_id_seq'), 2, 1, '2022-10-30 00:02:42.330642', 6);
INSERT INTO satis (id, musteri_id, satilan_adet, satis_zamani, urun_id)
VALUES (nextval('satis_id_seq'), 2, 1, '2022-10-30 00:02:42.349635', 7);
INSERT INTO satis (id, musteri_id, satilan_adet, satis_zamani, urun_id)
VALUES (nextval('satis_id_seq'), 2, 1, '2022-10-30 00:02:42.364641', 8);
INSERT INTO satis (id, musteri_id, satilan_adet, satis_zamani, urun_id)
VALUES (nextval('satis_id_seq'), 2, 1, '2022-10-30 00:02:43.146361', 6);
INSERT INTO satis (id, musteri_id, satilan_adet, satis_zamani, urun_id)
VALUES (nextval('satis_id_seq'), 2, 1, '2022-10-30 00:02:43.168168', 7);
INSERT INTO satis (id, musteri_id, satilan_adet, satis_zamani, urun_id)
VALUES (nextval('satis_id_seq'), 2, 1, '2022-10-30 00:02:43.180648', 8);
INSERT INTO satis (id, musteri_id, satilan_adet, satis_zamani, urun_id)
VALUES (nextval('satis_id_seq'), 2, 1, '2022-10-30 00:03:01.883028', 6);
INSERT INTO satis (id, musteri_id, satilan_adet, satis_zamani, urun_id)
VALUES (nextval('satis_id_seq'), 2, 1, '2022-10-30 00:03:01.911328', 7);
INSERT INTO satis (id, musteri_id, satilan_adet, satis_zamani, urun_id)
VALUES (nextval('satis_id_seq'), 2, 1, '2022-10-30 00:03:01.925377', 8);
INSERT INTO satis (id, musteri_id, satilan_adet, satis_zamani, urun_id)
VALUES (nextval('satis_id_seq'), 2, 4, '2022-10-30 00:03:01.940419', 8);
INSERT INTO satis (id, musteri_id, satilan_adet, satis_zamani, urun_id)
VALUES (nextval('satis_id_seq'), 2, 4, '2022-10-30 00:03:01.955386', 7);
INSERT INTO satis (id, musteri_id, satilan_adet, satis_zamani, urun_id)
VALUES (nextval('satis_id_seq'), 2, 3, '2022-10-30 00:03:01.967929', 6);
INSERT INTO satis (id, musteri_id, satilan_adet, satis_zamani, urun_id)
VALUES (nextval('satis_id_seq'), 2, 3, '2022-10-30 00:03:01.979848', 6);
INSERT INTO satis (id, musteri_id, satilan_adet, satis_zamani, urun_id)
VALUES (nextval('satis_id_seq'), 2, 4, '2022-10-30 00:03:01.993869', 8);
INSERT INTO satis (id, musteri_id, satilan_adet, satis_zamani, urun_id)
VALUES (nextval('satis_id_seq'), 2, 4, '2022-10-30 00:03:02.006930', 7);
INSERT INTO satis (id, musteri_id, satilan_adet, satis_zamani, urun_id)
VALUES (nextval('satis_id_seq'), 2, 1, '2022-10-30 00:07:13.160951', 12);
INSERT INTO satis (id, musteri_id, satilan_adet, satis_zamani, urun_id)
VALUES (nextval('satis_id_seq'), 2, 1, '2022-10-30 00:07:13.174442', 11);
INSERT INTO satis (id, musteri_id, satilan_adet, satis_zamani, urun_id)
VALUES (nextval('satis_id_seq'), 2, 1, '2022-10-30 00:07:13.190271', 10);
INSERT INTO satis (id, musteri_id, satilan_adet, satis_zamani, urun_id)
VALUES (nextval('satis_id_seq'), 2, 1, '2022-10-30 00:07:13.203748', 9);
INSERT INTO satis (id, musteri_id, satilan_adet, satis_zamani, urun_id)
VALUES (nextval('satis_id_seq'), 2, 1, '2022-10-30 00:07:13.215622', 8);
INSERT INTO satis (id, musteri_id, satilan_adet, satis_zamani, urun_id)
VALUES (nextval('satis_id_seq'), 2, 1, '2022-10-30 00:07:44.107996', 8);
INSERT INTO satis (id, musteri_id, satilan_adet, satis_zamani, urun_id)
VALUES (nextval('satis_id_seq'), 2, 1, '2022-10-30 00:07:44.137397', 9);
INSERT INTO satis (id, musteri_id, satilan_adet, satis_zamani, urun_id)
VALUES (nextval('satis_id_seq'), 2, 1, '2022-10-30 00:07:44.150547', 10);
INSERT INTO satis (id, musteri_id, satilan_adet, satis_zamani, urun_id)
VALUES (nextval('satis_id_seq'), 2, 1, '2022-10-30 00:07:44.161762', 11);
INSERT INTO satis (id, musteri_id, satilan_adet, satis_zamani, urun_id)
VALUES (nextval('satis_id_seq'), 2, 1, '2022-10-30 00:07:44.175509', 12);
INSERT INTO satis (id, musteri_id, satilan_adet, satis_zamani, urun_id)
VALUES (nextval('satis_id_seq'), 2, 4, '2022-10-30 00:07:44.187436', 9);
INSERT INTO satis (id, musteri_id, satilan_adet, satis_zamani, urun_id)
VALUES (nextval('satis_id_seq'), 2, 5, '2022-10-30 00:07:44.202495', 10);
INSERT INTO satis (id, musteri_id, satilan_adet, satis_zamani, urun_id)
VALUES (nextval('satis_id_seq'), 2, 5, '2022-10-30 00:07:44.215294', 11);
INSERT INTO satis (id, musteri_id, satilan_adet, satis_zamani, urun_id)
VALUES (nextval('satis_id_seq'), 2, 5, '2022-10-30 00:07:44.226634', 12);
INSERT INTO satis (id, musteri_id, satilan_adet, satis_zamani, urun_id)
VALUES (nextval('satis_id_seq'), 2, 1, '2022-10-30 00:07:45.216892', 8);
INSERT INTO satis (id, musteri_id, satilan_adet, satis_zamani, urun_id)
VALUES (nextval('satis_id_seq'), 2, 1, '2022-10-30 00:07:45.229349', 9);
INSERT INTO satis (id, musteri_id, satilan_adet, satis_zamani, urun_id)
VALUES (nextval('satis_id_seq'), 2, 1, '2022-10-30 00:07:45.244766', 10);
INSERT INTO satis (id, musteri_id, satilan_adet, satis_zamani, urun_id)
VALUES (nextval('satis_id_seq'), 2, 1, '2022-10-30 00:07:45.259582', 11);
INSERT INTO satis (id, musteri_id, satilan_adet, satis_zamani, urun_id)
VALUES (nextval('satis_id_seq'), 2, 1, '2022-10-30 00:07:45.272261', 12);
INSERT INTO satis (id, musteri_id, satilan_adet, satis_zamani, urun_id)
VALUES (nextval('satis_id_seq'), 2, 4, '2022-10-30 00:07:45.286989', 9);
INSERT INTO satis (id, musteri_id, satilan_adet, satis_zamani, urun_id)
VALUES (nextval('satis_id_seq'), 2, 5, '2022-10-30 00:07:45.304274', 10);
INSERT INTO satis (id, musteri_id, satilan_adet, satis_zamani, urun_id)
VALUES (nextval('satis_id_seq'), 2, 5, '2022-10-30 00:07:45.320600', 11);
INSERT INTO satis (id, musteri_id, satilan_adet, satis_zamani, urun_id)
VALUES (nextval('satis_id_seq'), 2, 5, '2022-10-30 00:07:45.335276', 12);
INSERT INTO satis (id, musteri_id, satilan_adet, satis_zamani, urun_id)
VALUES (nextval('satis_id_seq'), 2, 1, '2022-10-30 00:07:51.578819', 8);
INSERT INTO satis (id, musteri_id, satilan_adet, satis_zamani, urun_id)
VALUES (nextval('satis_id_seq'), 2, 1, '2022-10-30 00:07:51.593137', 9);
INSERT INTO satis (id, musteri_id, satilan_adet, satis_zamani, urun_id)
VALUES (nextval('satis_id_seq'), 2, 1, '2022-10-30 00:07:51.607432', 10);
INSERT INTO satis (id, musteri_id, satilan_adet, satis_zamani, urun_id)
VALUES (nextval('satis_id_seq'), 2, 1, '2022-10-30 00:07:51.636623', 11);
INSERT INTO satis (id, musteri_id, satilan_adet, satis_zamani, urun_id)
VALUES (nextval('satis_id_seq'), 2, 1, '2022-10-30 00:07:51.653144', 12);
INSERT INTO satis (id, musteri_id, satilan_adet, satis_zamani, urun_id)
VALUES (nextval('satis_id_seq'), 2, 4, '2022-10-30 00:07:51.669386', 9);
INSERT INTO satis (id, musteri_id, satilan_adet, satis_zamani, urun_id)
VALUES (nextval('satis_id_seq'), 2, 5, '2022-10-30 00:07:51.684072', 10);
INSERT INTO satis (id, musteri_id, satilan_adet, satis_zamani, urun_id)
VALUES (nextval('satis_id_seq'), 2, 5, '2022-10-30 00:07:51.697661', 11);
INSERT INTO satis (id, musteri_id, satilan_adet, satis_zamani, urun_id)
VALUES (nextval('satis_id_seq'), 2, 5, '2022-10-30 00:07:51.710453', 12);
INSERT INTO satis (id, musteri_id, satilan_adet, satis_zamani, urun_id)
VALUES (nextval('satis_id_seq'), 2, 5, '2022-10-30 00:08:08.183973', 12);
INSERT INTO satis (id, musteri_id, satilan_adet, satis_zamani, urun_id)
VALUES (nextval('satis_id_seq'), 2, 5, '2022-10-30 00:08:08.198456', 12);
INSERT INTO satis (id, musteri_id, satilan_adet, satis_zamani, urun_id)
VALUES (nextval('satis_id_seq'), 2, 5, '2022-10-30 00:08:08.211005', 11);
INSERT INTO satis (id, musteri_id, satilan_adet, satis_zamani, urun_id)
VALUES (nextval('satis_id_seq'), 2, 5, '2022-10-30 00:08:08.222817', 11);
INSERT INTO satis (id, musteri_id, satilan_adet, satis_zamani, urun_id)
VALUES (nextval('satis_id_seq'), 2, 7, '2022-10-30 00:08:08.236878', 10);
INSERT INTO satis (id, musteri_id, satilan_adet, satis_zamani, urun_id)
VALUES (nextval('satis_id_seq'), 2, 7, '2022-10-30 00:08:08.249187', 10);
INSERT INTO satis (id, musteri_id, satilan_adet, satis_zamani, urun_id)
VALUES (nextval('satis_id_seq'), 2, 1, '2022-10-30 00:08:59.873697', 12);
INSERT INTO satis (id, musteri_id, satilan_adet, satis_zamani, urun_id)
VALUES (nextval('satis_id_seq'), 2, 1, '2022-10-30 00:08:59.887282', 11);
INSERT INTO satis (id, musteri_id, satilan_adet, satis_zamani, urun_id)
VALUES (nextval('satis_id_seq'), 2, 1, '2022-10-30 00:08:59.900955', 10);
INSERT INTO satis (id, musteri_id, satilan_adet, satis_zamani, urun_id)
VALUES (nextval('satis_id_seq'), 2, 1, '2022-10-30 00:08:59.915124', 9);
INSERT INTO satis (id, musteri_id, satilan_adet, satis_zamani, urun_id)
VALUES (nextval('satis_id_seq'), 2, 2, '2022-10-30 00:08:59.928554', 12);
INSERT INTO satis (id, musteri_id, satilan_adet, satis_zamani, urun_id)
VALUES (nextval('satis_id_seq'), 2, 2, '2022-10-30 00:08:59.943545', 11);
INSERT INTO satis (id, musteri_id, satilan_adet, satis_zamani, urun_id)
VALUES (nextval('satis_id_seq'), 2, 2, '2022-10-30 00:08:59.958896', 10);
INSERT INTO satis (id, musteri_id, satilan_adet, satis_zamani, urun_id)
VALUES (nextval('satis_id_seq'), 2, 2, '2022-10-30 00:08:59.969427', 9);
INSERT INTO satis (id, musteri_id, satilan_adet, satis_zamani, urun_id)
VALUES (nextval('satis_id_seq'), 2, 4, '2022-10-30 00:08:59.980791', 11);
INSERT INTO satis (id, musteri_id, satilan_adet, satis_zamani, urun_id)
VALUES (nextval('satis_id_seq'), 2, 4, '2022-10-30 00:08:59.994477', 10);
INSERT INTO satis (id, musteri_id, satilan_adet, satis_zamani, urun_id)
VALUES (nextval('satis_id_seq'), 2, 9, '2022-10-30 00:09:35.978212', 9);


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
