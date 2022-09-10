package com.marul.stokservice;

import com.marul.exception.BulunamadiException;
import com.marul.stokservice.stok.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StokServiceTest {

    @Mock
    private StokRepository stokRepository;
//
//    @Mock
//    private UrunService urunService;

    @Mock
    private StokMapper stokMapper;

    @InjectMocks
    private StokService stokService;


    @Test
    void itShould_returnTrue_WhenStockIsAvailable() {
        //given
        long urunId = 1L;
//        when(urunService.existsById(urunId)).thenReturn(Boolean.TRUE);

        long stok = 10L;
        when(stokRepository.yeterliStokVarMi(urunId, stok))
                .thenReturn(true);
        //when
        boolean actual = stokService.yeterliStokVarMi(urunId, stok);
        //then
        Assertions.assertTrue(actual);
    }

    @Test
    void itShould_ThrowsException_WhenProductIfNotExists() {
        //given
        long urunId = 1L;
//        when(urunService.existsById(urunId)).thenThrow(BulunamadiException.class);

        long stok = 10L;
        //when
        //then
        Assertions.assertThrows(BulunamadiException.class,
                () -> stokService.yeterliStokVarMi(urunId, stok));
        verify(stokRepository, times(0)).yeterliStokVarMi(urunId, stok);
    }

    @Test
    void stoguBulunanUruneYeniStokYuklendiginde() {
        // given
        long stokId = 1L;
        long urunId = 1L;
        long stokYuklemeAdet = 5L;
        long stokMevcutAdet = 15L;

        StokDto stokDto = new StokDto();
        stokDto.setId(stokId);
        stokDto.setAdet(stokYuklemeAdet);
        stokDto.setUrunId(urunId);

        Stok stok = new Stok();

        stok.setId(stokId);
        stok.setAdet(stokMevcutAdet);
        stok.setUrunId(urunId);

        Stok yuklemeSonrasiStok = new Stok();

        yuklemeSonrasiStok.setId(stokId);
        yuklemeSonrasiStok.setAdet(stokMevcutAdet + stokYuklemeAdet);
        yuklemeSonrasiStok.setUrunId(urunId);

        StokDto yuklemeSonrasistokDto = new StokDto();
        yuklemeSonrasistokDto.setId(stokId);
        yuklemeSonrasistokDto.setAdet(stokMevcutAdet + stokYuklemeAdet);
        yuklemeSonrasistokDto.setUrunId(urunId);

        when(stokRepository.findByUrunId(urunId))
                .thenReturn(Optional.of(stok));
        when(stokRepository.save(stok))
                .thenReturn(yuklemeSonrasiStok);
        when(stokMapper.getDto(yuklemeSonrasiStok))
                .thenReturn(yuklemeSonrasistokDto);

        //when
        StokDto actual = stokService.save(stokDto);
        //then
        assertEquals(yuklemeSonrasistokDto.getAdet(), actual.getAdet());
        assertEquals(yuklemeSonrasistokDto.getUrunId(), actual.getUrunId());
        assertEquals(yuklemeSonrasistokDto.getId(), actual.getId());

        verify(stokRepository).findByUrunId(urunId);
        verify(stokMapper, times(0)).getEntity(stokDto);
        verify(stokRepository).save(stok);
        verify(stokMapper).getDto(yuklemeSonrasiStok);
    }

    @Test
    void stoguBulunmayanUruneYeniStokYuklendiginde() {
        // given
        long stokId = 1L;
        long urunId = 1L;
        long stokAdet = 0L;

        StokDto stokDto = new StokDto();
        stokDto.setId(stokId);
        stokDto.setAdet(stokAdet);
        stokDto.setUrunId(urunId);

        Stok stok = new Stok();

        stok.setId(stokId);
        stok.setAdet(stokAdet);
        stok.setUrunId(urunId);

        when(stokRepository.findByUrunId(urunId))
                .thenReturn(Optional.empty());
        when(stokMapper.getEntity(stokDto))
                .thenReturn(stok);
        when(stokRepository.save(stok))
                .thenReturn(stok);
        when(stokMapper.getDto(stok))
                .thenReturn(stokDto);

        //when
        StokDto actual = stokService.save(stokDto);
        //then
        assertEquals(stokDto.getAdet(), actual.getAdet());
        assertEquals(stokDto.getUrunId(), actual.getUrunId());
        assertEquals(stokDto.getId(), actual.getId());

        verify(stokRepository).findByUrunId(urunId);
        verify(stokMapper).getEntity(stokDto);
        verify(stokRepository).save(stok);
        verify(stokMapper).getDto(stok);
    }
}