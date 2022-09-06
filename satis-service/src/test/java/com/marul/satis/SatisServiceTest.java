package com.marul.satis;

import com.marul.dto.MusteriDto;
import com.marul.dto.SatisDto;
import com.marul.dto.result.SuccessDataResult;
import com.marul.exception.BulunamadiException;
import com.marul.urun.UrunDto;
import com.marul.urun.UrunService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.annotation.Description;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SatisServiceTest {

    @Mock
    private SatisRepository satisRepository;
    @Mock
    private MusteriFeignClient musteriFeignClient;
    @Mock
    private SatisMapper satisMapper;
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

        MusteriDto musteriDto = new MusteriDto();
        musteriDto.setId(musteriId);
        when(musteriFeignClient.findById(satis.getMusteriId())).thenReturn(new SuccessDataResult<>(musteriDto));

        //when
        List<SatisDto> satisDtoList = satisService.findAll();

        //then
        SatisDto satisDto = new SatisDto();
        satisDto.setUrunId(urunId);
        satisDto.setMusteriDto(musteriDto);
        List<SatisDto> beklenen = Collections.singletonList(satisDto);
        Assertions.assertEquals(beklenen.size(), satisDtoList.size());
        Assertions.assertEquals(beklenen.get(0).getMusteriDto().getMusteriAdi(),
                satisDtoList.get(0).getMusteriDto().getMusteriAdi());
        verify(satisRepository).findAll();
    }

    @Test
    @Description("Müşteri kaydedilebilmelidir.")
    void save() {
        // given
        SatisDto satisDto = new SatisDto();
        MusteriDto musteriDto = new MusteriDto();
        long musteriId = 1L;
        long urunId = 1L;
        musteriDto.setId(musteriId);

        satisDto.setMusteriDto(musteriDto);
        satisDto.setUrunId(urunId);

        Satis satis = new Satis();
        satis.setMusteriId(musteriId);
        satis.setUrunId(urunId);

        when(satisMapper.getEntity(satisDto))
                .thenReturn(satis);

        when(musteriFeignClient.findById(musteriId))
                .thenReturn(new SuccessDataResult<>(musteriDto));

        when(satisRepository.save(satis))
                .thenReturn(satis);

        when(satisMapper.getDto(satis))
                .thenReturn(satisDto);

        when(urunService.findById(urunId))
                .thenReturn(new UrunDto());

        // when
        SatisDto actual = satisService.save(satisDto);

        //then
        Assertions.assertEquals(actual.getMusteriDto().getMusteriAdi(), satisDto.getMusteriDto().getMusteriAdi());
        Assertions.assertEquals(actual.getMusteriDto().getEmail(), satisDto.getMusteriDto().getEmail());
        Assertions.assertEquals(actual.getUrunId(), satisDto.getUrunId());

        verify(satisMapper).getEntity(satisDto);
        verify(musteriFeignClient).findById(musteriId);
        verify(urunService).findById(urunId);
        verify(satisRepository).save(satis);
        verify(satisMapper).getDto(satis);
    }

    @Test
    void itShouldNot_Save_WhenCustomerIsNotExists() {
        // given
        SatisDto satisDto = new SatisDto();
        MusteriDto musteriDto = new MusteriDto();
        long musteriId = 1L;
        long urunId = 1L;
        musteriDto.setId(musteriId);

        satisDto.setMusteriDto(musteriDto);
        satisDto.setUrunId(urunId);

        Satis satis = new Satis();
        satis.setMusteriId(musteriId);
        satis.setUrunId(urunId);

        when(satisMapper.getEntity(satisDto))
                .thenReturn(satis);

        when(musteriFeignClient.findById(musteriId))
                .thenThrow(BulunamadiException.class);

        // when
        assertThrows(BulunamadiException.class, () -> satisService.save(satisDto));

        //then
        verify(satisMapper).getEntity(satisDto);
        verify(musteriFeignClient).findById(musteriId);
        verify(satisRepository, Mockito.times(0)).save(satis);
        verify(satisMapper, Mockito.times(0)).getDto(satis);
    }

    @Test
    void itShouldNot_Save_WhenProductIsNotExists() {
        // given
        SatisDto satisDto = new SatisDto();
        MusteriDto musteriDto = new MusteriDto();
        long musteriId = 1L;
        long urunId = 1L;
        musteriDto.setId(musteriId);

        satisDto.setMusteriDto(musteriDto);
        satisDto.setUrunId(urunId);

        Satis satis = new Satis();
        satis.setMusteriId(musteriId);
        satis.setUrunId(urunId);

        when(satisMapper.getEntity(satisDto))
                .thenReturn(satis);

        when(musteriFeignClient.findById(musteriId))
                .thenReturn(new SuccessDataResult<>(musteriDto));

        when(urunService.findById(urunId))
                .thenThrow(BulunamadiException.class);

        // when
        assertThrows(BulunamadiException.class, () -> satisService.save(satisDto));

        //then
        verify(satisMapper).getEntity(satisDto);
        verify(musteriFeignClient).findById(musteriId);
        verify(urunService).findById(urunId);
        verify(satisRepository, Mockito.times(0)).save(satis);
        verify(satisMapper, Mockito.times(0)).getDto(satis);
    }

}