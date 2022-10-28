INSERT INTO stok (id, adet, urun_id)
VALUES (nextval('stok_id_seq'), 5, 2);
INSERT INTO stok (id, adet, urun_id)
VALUES (nextval('stok_id_seq'), 40, 4);
INSERT INTO stok (id, adet, urun_id)
VALUES (nextval('stok_id_seq'), 60, 5);
INSERT INTO stok (id, adet, urun_id)
VALUES (nextval('stok_id_seq'), 30, 6);
INSERT INTO stok (id, adet, urun_id)
VALUES (nextval('stok_id_seq'), 16, 8);
INSERT INTO stok (id, adet, urun_id)
VALUES (nextval('stok_id_seq'), 50, 7);
INSERT INTO stok (id, adet, urun_id)
VALUES (nextval('stok_id_seq'), 1, 3);
INSERT INTO stok (id, adet, urun_id)
VALUES (nextval('stok_id_seq'), 15, 1);

INSERT INTO stok_hareketi (id, aciklama, hareket_zamani, miktar, stok_id)
VALUES (nextval('stok_hareketi_id_seq'), 'Stok güncelleme', '2022-10-16 14:36:29.505357', -30, 5);
INSERT INTO stok_hareketi (id, aciklama, hareket_zamani, miktar, stok_id)
VALUES (nextval('stok_hareketi_id_seq'), 'Stok güncelleme', '2022-10-16 14:36:33.370907', -30, 8);
INSERT INTO stok_hareketi (id, aciklama, hareket_zamani, miktar, stok_id)
VALUES (nextval('stok_hareketi_id_seq'), 'Stok güncelleme', '2022-10-16 14:36:34.459426', -30, 1);
INSERT INTO stok_hareketi (id, aciklama, hareket_zamani, miktar, stok_id)
VALUES (nextval('stok_hareketi_id_seq'), 'Stok güncelleme', '2022-10-16 14:36:35.603280', -30, 7);
INSERT INTO stok_hareketi (id, aciklama, hareket_zamani, miktar, stok_id)
VALUES (nextval('stok_hareketi_id_seq'), 'Stok güncelleme', '2022-10-16 14:36:41.985664', -5, 2);
INSERT INTO stok_hareketi (id, aciklama, hareket_zamani, miktar, stok_id)
VALUES (nextval('stok_hareketi_id_seq'), 'Stok güncelleme', '2022-10-16 14:36:43.098073', -5, 3);
INSERT INTO stok_hareketi (id, aciklama, hareket_zamani, miktar, stok_id)
VALUES (nextval('stok_hareketi_id_seq'), 'Stok güncelleme', '2022-10-16 14:36:44.402692', -5, 4);
INSERT INTO stok_hareketi (id, aciklama, hareket_zamani, miktar, stok_id)
VALUES (nextval('stok_hareketi_id_seq'), 'Stok güncelleme', '2022-10-16 14:36:49.221187', -1, 6);
INSERT INTO stok_hareketi (id, aciklama, hareket_zamani, miktar, stok_id)
VALUES (nextval('stok_hareketi_id_seq'), 'Stok güncelleme', '2022-10-16 14:37:02.740144', 5, 8);
INSERT INTO stok_hareketi (id, aciklama, hareket_zamani, miktar, stok_id)
VALUES (nextval('stok_hareketi_id_seq'), 'Stok güncelleme', '2022-10-16 14:37:10.026143', 10, 8);
INSERT INTO stok_hareketi (id, aciklama, hareket_zamani, miktar, stok_id)
VALUES (nextval('stok_hareketi_id_seq'), 'Stok güncelleme', '2022-10-16 14:37:15.391121', 10, 1);
INSERT INTO stok_hareketi (id, aciklama, hareket_zamani, miktar, stok_id)
VALUES (nextval('stok_hareketi_id_seq'), 'Stok güncelleme', '2022-10-16 14:37:20.428302', 7, 1);
INSERT INTO stok_hareketi (id, aciklama, hareket_zamani, miktar, stok_id)
VALUES (nextval('stok_hareketi_id_seq'), 'Stok güncelleme', '2022-10-16 14:37:28.433305', 7, 5);
INSERT INTO stok_hareketi (id, aciklama, hareket_zamani, miktar, stok_id)
VALUES (nextval('stok_hareketi_id_seq'), 'Stok güncelleme', '2022-10-16 14:37:32.051872', 1, 5);
