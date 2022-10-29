package com.marul.satis;

import com.marul.dto.satis.KategoriSatisAnalizDto;
import com.marul.dto.satis.SatisDto;
import com.marul.dto.urun.UrunDto;
import com.marul.kategori.KategoriDto;
import com.marul.kategori.KategoriService;
import com.marul.urun.UrunService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SatisAnalizService {

    private final SatisService satisService;
    private final UrunService urunService;
    private final KategoriService kategoriService;

    public Map<String, BigDecimal> haftalikSatisToMap(List<KategoriSatisAnalizDto> kategoriSatisAnalizDtoList) {
        Map<String, BigDecimal> haftalikSatisMap = new HashMap<>();
        kategoriSatisAnalizDtoList.forEach(
                kategoriSatisAnalizDto -> {
                    String kategoriAdi = kategoriSatisAnalizDto.getKategoriAdi();
                    BigDecimal toplamSatisTutari = kategoriSatisAnalizDto.getToplamSatisTutari();
                    if (!haftalikSatisMap.containsKey(kategoriAdi)) {
                        haftalikSatisMap.put(kategoriAdi, toplamSatisTutari);
                    } else {
                        BigDecimal toplamSatis = haftalikSatisMap.get(kategoriAdi)
                                .add(toplamSatisTutari);
                        haftalikSatisMap.put(kategoriAdi, toplamSatis);
                    }
                }
        );
        return haftalikSatisMap;
    }

    public Map<String, BigDecimal> haftalikSatislariGetir() {
        LocalDateTime baslangiZamani = LocalDateTime.now().minusDays(7);
        LocalDateTime bitisZamani = LocalDateTime.now();
        List<SatisDto> satisDtoList = satisService.baslangicVeBitisZamaninaGoreSatisGetir(baslangiZamani, bitisZamani);
        List<KategoriSatisAnalizDto> kategoriSatisAnalizDtoList = satisDtoList
                .stream()
                .map(satisDto -> {
                            UrunDto urunDto = urunService.findById(satisDto.getUrunId());
                            BigDecimal fiyat = urunDto.getFiyat();
                            Long satilanAdet = satisDto.getSatilanAdet();
                            BigDecimal toplam = fiyat.multiply(BigDecimal.valueOf(satilanAdet));
                            Long kategoriId = urunDto.getKategoriId();
                            KategoriDto kategoriDto = kategoriService.findById(kategoriId);
                            return KategoriSatisAnalizDto.builder()
                                    .toplamSatisTutari(toplam)
                                    .kategoriAdi(kategoriDto.getKategoriAdi())
                                    .build();
                        }
                ).collect(Collectors.toList());
        return haftalikSatisToMap(kategoriSatisAnalizDtoList);
    }


}
