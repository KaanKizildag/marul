//package com.marul.satis;
//
//import com.marul.dto.satis.SatisResponseDto;
//import com.marul.dto.musteri.MusteriDto;
//import com.marul.dto.result.SuccessDataResult;
//import com.marul.exception.BulunamadiException;
//import com.marul.urun.UrunService;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.util.Collections;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//class SatisServiceTest {
//
//    @Mock
//    private SatisRepository satisRepository;
//    @Mock
//    private MusteriFeignClient musteriFeignClient;
//    @Mock
//    private SatisMapperImpl satisMapper;
//    @Mock
//    private UrunService urunService;
//
//    @InjectMocks
//    private SatisService satisService;
//
//
//    @Test
//    void findAll() {
//
//        Satis satis = new Satis();
//        long musteriId = 1L;
//        long urunId = 1L;
//        long satisId = 1L;
//        satis.setMusteriId(musteriId);
//        satis.setUrunId(urunId);
//        satis.setId(satisId);
//
//        when(satisRepository.findAll()).thenReturn(Collections.singletonList(satis));
//
//
//        //when
//        List<SatisResponseDto> satisResponseDtoList = satisService.findAll();
//
//        //then
//        SatisResponseDto satisResponseDto = new SatisResponseDto();
//        satisResponseDto.setUrunId(urunId);
//        satisResponseDto.setMusteriId(musteriId);
//        List<SatisResponseDto> beklenen = Collections.singletonList(satisResponseDto);
//        Assertions.assertEquals(beklenen.size(), satisResponseDtoList.size());
//        verify(satisRepository).findAll();
//    }
//
//    @Test
//    void save() {
//        // given
//        SatisResponseDto satisResponseDto = new SatisResponseDto();
//        long musteriId = 1L;
//        long urunId = 1L;
//
//        satisResponseDto.setMusteriId(musteriId);
//        satisResponseDto.setUrunId(urunId);
//
//        Satis satis = new Satis();
//        satis.setMusteriId(musteriId);
//        satis.setUrunId(urunId);
//
//        when(satisMapper.getEntity(satisResponseDto))
//                .thenReturn(satis);
//
//        when(musteriFeignClient.existsById(musteriId))
//                .thenReturn(new SuccessDataResult<>(true));
//
//        when(satisRepository.save(satis))
//                .thenReturn(satis);
//
//        when(satisMapper.getDto(satis))
//                .thenReturn(satisResponseDto);
//
//        when(urunService.existsById(urunId))
//                .thenReturn(true);
//
//        // when
//        SatisResponseDto actual = satisService.save(satisResponseDto);
//
//        //then
//        Assertions.assertEquals(actual.getUrunId(), satisResponseDto.getUrunId());
//
//        verify(satisMapper).getEntity(satisResponseDto);
//        verify(musteriFeignClient).existsById(musteriId);
//        verify(urunService).existsById(urunId);
//        verify(satisRepository).save(satis);
//        verify(satisMapper).getDto(satis);
//    }
//
//    @Test
//    void itShouldNot_Save_WhenCustomerIsNotExists() {
//        // given
//        SatisResponseDto satisResponseDto = new SatisResponseDto();
//        long musteriId = 1L;
//        long urunId = 1L;
//
//        satisResponseDto.setMusteriId(musteriId);
//        satisResponseDto.setUrunId(urunId);
//
//        Satis satis = new Satis();
//        satis.setMusteriId(musteriId);
//        satis.setUrunId(urunId);
//
//        when(musteriFeignClient.existsById(musteriId))
//                .thenThrow(BulunamadiException.class);
//
//        // when
//        assertThrows(BulunamadiException.class, () -> satisService.save(satisResponseDto));
//
//        //then
//        verify(satisMapper, times(0)).getEntity(satisResponseDto);
//        verify(musteriFeignClient).existsById(musteriId);
//        verify(satisRepository, times(0)).save(satis);
//        verify(satisMapper, times(0)).getDto(satis);
//    }
//
//    @Test
//    void itShouldNot_Save_WhenProductIsNotExists() {
//        // given
//        SatisResponseDto satisResponseDto = new SatisResponseDto();
//        MusteriDto musteriDto = new MusteriDto();
//        long musteriId = 1L;
//        long urunId = 1L;
//        musteriDto.setId(musteriId);
//
//        satisResponseDto.setMusteriId(musteriId);
//        satisResponseDto.setUrunId(urunId);
//
//        Satis satis = new Satis();
//        satis.setMusteriId(musteriId);
//        satis.setUrunId(urunId);
//
//        when(musteriFeignClient.existsById(musteriId))
//                .thenReturn(new SuccessDataResult<>(true));
//
//        when(urunService.existsById(urunId))
//                .thenReturn(false);
//
//        // when
//        assertThrows(BulunamadiException.class, () -> satisService.save(satisResponseDto));
//
//        //then
//        verify(satisMapper, times(0)).getEntity(satisResponseDto);
//        verify(musteriFeignClient).existsById(musteriId);
//        verify(urunService).existsById(urunId);
//        verify(satisRepository, times(0)).save(satis);
//        verify(satisMapper, times(0)).getDto(satis);
//    }
//
//}