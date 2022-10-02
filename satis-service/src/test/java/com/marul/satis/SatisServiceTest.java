package com.marul.satis;

import com.marul.dto.SatisDto;
import com.marul.dto.musteri.MusteriDto;
import com.marul.dto.result.SuccessDataResult;
import com.marul.exception.BulunamadiException;
import com.marul.urun.UrunService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SatisServiceTest {

    @Mock
    private SatisRepository satisRepository;
    @Mock
    private MusteriFeignClient musteriFeignClient;
    @Mock
    private SatisMapperImpl satisMapper;
    @Mock
    private UrunService urunService;

    @InjectMocks
    private SatisService satisService;


    @Test
    void findAll() {

        Satis satis = new Satis();
        long musteriId = 1L;
        long urunId = 1L;
        long satisId = 1L;
        satis.setMusteriId(musteriId);
        satis.setUrunId(urunId);
        satis.setId(satisId);

        when(satisRepository.findAll()).thenReturn(Collections.singletonList(satis));


        //when
        List<SatisDto> satisDtoList = satisService.findAll();

        //then
        SatisDto satisDto = new SatisDto();
        satisDto.setUrunId(urunId);
        satisDto.setMusteriId(musteriId);
        List<SatisDto> beklenen = Collections.singletonList(satisDto);
        Assertions.assertEquals(beklenen.size(), satisDtoList.size());
        verify(satisRepository).findAll();
    }

    @Test
    void save() {
        // given
        SatisDto satisDto = new SatisDto();
        long musteriId = 1L;
        long urunId = 1L;

        satisDto.setMusteriId(musteriId);
        satisDto.setUrunId(urunId);

        Satis satis = new Satis();
        satis.setMusteriId(musteriId);
        satis.setUrunId(urunId);

        when(satisMapper.getEntity(satisDto))
                .thenReturn(satis);

        when(musteriFeignClient.existsById(musteriId))
                .thenReturn(new SuccessDataResult<>(true));

        when(satisRepository.save(satis))
                .thenReturn(satis);

        when(satisMapper.getDto(satis))
                .thenReturn(satisDto);

        when(urunService.existsById(urunId))
                .thenReturn(true);

        // when
        SatisDto actual = satisService.save(satisDto);

        //then
        Assertions.assertEquals(actual.getUrunId(), satisDto.getUrunId());

        verify(satisMapper).getEntity(satisDto);
        verify(musteriFeignClient).existsById(musteriId);
        verify(urunService).existsById(urunId);
        verify(satisRepository).save(satis);
        verify(satisMapper).getDto(satis);
    }

    @Test
    void itShouldNot_Save_WhenCustomerIsNotExists() {
        // given
        SatisDto satisDto = new SatisDto();
        long musteriId = 1L;
        long urunId = 1L;

        satisDto.setMusteriId(musteriId);
        satisDto.setUrunId(urunId);

        Satis satis = new Satis();
        satis.setMusteriId(musteriId);
        satis.setUrunId(urunId);

        when(musteriFeignClient.existsById(musteriId))
                .thenThrow(BulunamadiException.class);

        // when
        assertThrows(BulunamadiException.class, () -> satisService.save(satisDto));

        //then
        verify(satisMapper, times(0)).getEntity(satisDto);
        verify(musteriFeignClient).existsById(musteriId);
        verify(satisRepository, times(0)).save(satis);
        verify(satisMapper, times(0)).getDto(satis);
    }

    @Test
    void itShouldNot_Save_WhenProductIsNotExists() {
        // given
        SatisDto satisDto = new SatisDto();
        MusteriDto musteriDto = new MusteriDto();
        long musteriId = 1L;
        long urunId = 1L;
        musteriDto.setId(musteriId);

        satisDto.setMusteriId(musteriId);
        satisDto.setUrunId(urunId);

        Satis satis = new Satis();
        satis.setMusteriId(musteriId);
        satis.setUrunId(urunId);

        when(musteriFeignClient.existsById(musteriId))
                .thenReturn(new SuccessDataResult<>(true));

        when(urunService.existsById(urunId))
                .thenReturn(false);

        // when
        assertThrows(BulunamadiException.class, () -> satisService.save(satisDto));

        //then
        verify(satisMapper, times(0)).getEntity(satisDto);
        verify(musteriFeignClient).existsById(musteriId);
        verify(urunService).existsById(urunId);
        verify(satisRepository, times(0)).save(satis);
        verify(satisMapper, times(0)).getDto(satis);
    }

}