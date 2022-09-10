package com.marul.urun;

import com.marul.dto.urun.UrunDto;
import com.marul.exception.BulunamadiException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UrunServiceTest {

    @Mock
    private UrunRepository urunRepository;

    @Mock
    private UrunMapper urunMapper;

    @MockBean
    private UrunService urunService;

    @BeforeEach
    void setUp() {
        urunService = new UrunService(urunRepository, urunMapper);
    }

    @Test
    void save() {
        // given
        UrunDto urunDto = new UrunDto();
        String urunAdi = "Marul";
        urunDto.setUrunAdi(urunAdi);
        urunDto.setFiyat(BigDecimal.ZERO);
        urunDto.setKdv(0);

        Urun urun = new Urun();
        urun.setUrunAdi(urunAdi);
        urun.setFiyat(BigDecimal.ZERO);
        urun.setKdv(0);

        when(urunMapper.getEntity(urunDto))
                .thenReturn(urun);

        when(urunMapper.getDto(urun))
                .thenReturn(urunDto);

        when(urunRepository.existsByUrunAdi(urunAdi))
                .thenReturn(false);

        when(urunRepository.save(urun))
                .thenReturn(urun);

        //when
        UrunDto actual = urunService.save(urunDto);
        //then
        assertEquals(urunDto.getUrunAdi(), actual.getUrunAdi());
        assertEquals(urunDto.getFiyat(), actual.getFiyat());
        assertEquals(urunDto.getKdv(), actual.getKdv());

        verify(urunMapper).getEntity(urunDto);
        verify(urunRepository).existsByUrunAdi(urunAdi);
    }

    @Test
    void findAll() {
        List<Urun> urunList = new ArrayList<>();
        //given
        Urun urun1 = new Urun();
        String marul = "Marul";
        urun1.setUrunAdi(marul);
        urun1.setFiyat(BigDecimal.ZERO);
        urun1.setKdv(0);

        Urun urun2 = new Urun();
        String limon = "Limon";
        urun2.setUrunAdi(limon);
        urun2.setFiyat(BigDecimal.ZERO);
        urun2.setKdv(0);

        urunList.add(urun1);
        urunList.add(urun2);

        List<UrunDto> urunDtoList = new ArrayList<>();

        UrunDto urunDto1 = new UrunDto();
        urunDto1.setUrunAdi(marul);
        urunDto1.setFiyat(BigDecimal.ZERO);
        urunDto1.setKdv(0);

        UrunDto urunDto2 = new UrunDto();
        urunDto2.setUrunAdi(limon);
        urunDto2.setFiyat(BigDecimal.ZERO);
        urunDto2.setKdv(0);

        urunDtoList.add(urunDto1);
        urunDtoList.add(urunDto2);


        when(urunRepository.findAll()).thenReturn(urunList);
        when(urunMapper.getDtoList(urunList)).thenReturn(urunDtoList);

        //when
        List<UrunDto> actual = urunService.findAll();
        //then
        assertEquals(urunDtoList.size(), actual.size());

        int bound = urunDtoList.size();
        for (int i = 0; i < bound; i++) {
            String expectedUrunAdi = urunDtoList.get(i).getUrunAdi();
            String actualUrunAdi = actual.get(i).getUrunAdi();
            assertEquals(expectedUrunAdi, actualUrunAdi);

            BigDecimal expectedUrunFiyati = urunDtoList.get(i).getFiyat();
            BigDecimal actualUrunFiyati = actual.get(i).getFiyat();
            assertEquals(expectedUrunFiyati, actualUrunFiyati);

            int expectedKdv = urunDtoList.get(i).getKdv();
            int actualKdv = actual.get(i).getKdv();
            assertEquals(expectedKdv, actualKdv);
        }

        verify(urunMapper).getDtoList(urunList);
        verify(urunRepository).findAll();

    }

    @Test
    void findById() {
        //given
        String urunAdi = "Marul";
        long urunId = 1L;

        Urun urun = new Urun();
        urun.setId(urunId);
        urun.setUrunAdi(urunAdi);
        urun.setFiyat(BigDecimal.ZERO);
        urun.setKdv(0);

        UrunDto urunDto = new UrunDto();
        urunDto.setId(urunId);
        urunDto.setUrunAdi(urunAdi);
        urunDto.setFiyat(BigDecimal.ZERO);
        urunDto.setKdv(0);

        when(urunRepository.findById(urunId)).thenReturn(Optional.of(urun));
        when(urunMapper.getDto(urun)).thenReturn(urunDto);
        //when
        UrunDto actual = urunService.findById(urunId);

        //then
        assertEquals(urunDto.getUrunAdi(), actual.getUrunAdi());
        assertEquals(urunDto.getKdv(), actual.getKdv());
        assertEquals(urunDto.getFiyat(), actual.getFiyat());
        assertEquals(urunDto.getId(), actual.getId());

        verify(urunMapper).getDto(urun);
        verify(urunRepository).findById(urunId);

    }

    @Test
    void kaydiOlmayanUrunSorgulamafindById() {
        //given
        String urunAdi = "Marul";
        long urunId = 1L;

        Urun urun = new Urun();
        urun.setId(urunId);
        urun.setUrunAdi(urunAdi);
        urun.setFiyat(BigDecimal.ZERO);
        urun.setKdv(0);

        when(urunRepository.findById(urunId)).thenThrow(BulunamadiException.class);
        //when
        assertThrows(BulunamadiException.class, () -> urunService.findById(urunId));
        //then
        verify(urunMapper, times(0)).getDto(urun);
        verify(urunRepository, times(1)).findById(urunId);

    }
}