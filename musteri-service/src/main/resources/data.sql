INSERT INTO tur (id, tur_adi)
VALUES (nextval('tur_id_seq'), 'Akdeniz');
INSERT INTO tur (id, tur_adi)
VALUES (nextval('tur_id_seq'), 'Karadeniz');

INSERT INTO musteri (id, borc, email, musteri_adi, telefon_no, teslimat_noktasi, tur_id)
VALUES (nextval('musteri_id_seq'), 6, 'hkizildag@marul.com.tr', 'Hüseyin Kaan Kızıldağ', '99999999', 'Kahramanmaraş',
        1);
INSERT INTO musteri (id, borc, email, musteri_adi, telefon_no, teslimat_noktasi, tur_id)
VALUES (nextval('musteri_id_seq'), 15, 'tdede@marul.com.tr', 'Turgay DEDE', '99999999', 'Giresun', 2);
