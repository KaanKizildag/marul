package com.marul.stokservice;

import com.marul.dto.result.SuccessDataResult;
import com.marul.dto.stok.StokDto;
import com.marul.dto.stok.StokKaydetDto;
import com.marul.exception.ServisDonusHatasiException;
import com.marul.stokservice.stok.*;
import com.marul.stokservice.stokhareketi.StokHareketiDto;
import com.marul.stokservice.stokhareketi.StokHarektiService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StokServiceTest {

    @Mock
    private StokRepository stokRepository;
    @Mock
    private SatisFeignClient satisFeignClient;
    @Mock
    private StokMapper stokMapper;
    @Mock
    private StokHarektiService stokHarektiService;
    @InjectMocks
    private StokService stokService;


    @Test
    void itShould_returnTrue_WhenStockIsAvailable() {
        //given
        long urunId = 1L;
        when(satisFeignClient.existsUrunById(urunId))
                .thenReturn(new SuccessDataResult<>(Boolean.TRUE));

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
        when(satisFeignClient.existsUrunById(urunId)).thenThrow(ServisDonusHatasiException.class);

        long stok = 10L;
        //when
        //then
        Assertions.assertThrows(ServisDonusHatasiException.class,
                () -> stokService.yeterliStokVarMi(urunId, stok));
        verify(stokRepository, times(0)).yeterliStokVarMi(urunId, stok);
    }

    @Test
    @DisplayName("Stok güncelleme")
    void stokGuncelle() {
        Long urunId = 1L;
        Long satilanAdet = 10L;

        Stok stok = getStok();
        StokHareketiDto stokHareketiDto = getStokHareketiDto(satilanAdet, urunId);

        when(stokRepository.findByUrunId(urunId))
                .thenReturn(Optional.of(stok));
        when(satisFeignClient.existsUrunById(urunId))
                .thenReturn(new SuccessDataResult<>(Boolean.TRUE));
        when(stokRepository.yeterliStokVarMi(urunId, satilanAdet))
                .thenReturn(Boolean.TRUE);
        when(stokRepository.save(stok))
                .thenReturn(stok);
        when(stokHarektiService.save(any()))
                .thenReturn(stokHareketiDto);
        boolean actual = stokService.stokGuncelle(urunId, satilanAdet);

        Assertions.assertTrue(actual);

    }

    private StokHareketiDto getStokHareketiDto(long satilanAdet, long stokId) {
        return StokHareketiDto.builder()
                .hareketZamani(LocalDateTime.now())
                .aciklama("Stok güncelleme")
                .satisMi(satilanAdet > 0)
                .miktar(satilanAdet)
                .stokId(stokId)
                .build();
    }

    private Stok getStok() {
        Stok stok = new Stok();
        stok.setId(1L);
        stok.setAdet(10L);
        stok.setUrunId(1L);
        return stok;
    }

    @Test
    @DisplayName("Stok kaydet")
    void stoguBulunmayanUruneYeniStokYuklendiginde() {
        StokKaydetDto stokKaydetDto = getStokKaydetDto();
        Stok stok = getStok();

        when(stokMapper.getEntity(stokKaydetDto)).thenReturn(stok);
        when(stokRepository.save(any())).thenReturn(stok);
        when(stokMapper.getDto(stok)).thenReturn(new StokDto());

        StokDto actual = stokService.save(stokKaydetDto);

        verify(stokMapper).getEntity(stokKaydetDto);
        verify(stokMapper).getDto(stok);
        verify(stokRepository).save(stok);
    }

    private StokKaydetDto getStokKaydetDto() {
        StokKaydetDto stokDto = new StokKaydetDto();
        stokDto.setAdet(10L);
        stokDto.setUrunId(1L);
        return stokDto;
    }
}