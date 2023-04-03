package com.marul.stokservice.stok;

import com.marul.dto.stok.StokDto;
import com.marul.dto.stok.StokKaydetDto;
import com.marul.stokservice.stokhareketi.StokHarektiService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StokServiceTest {

    @Mock
    private StokRepository stokRepository;
    @Mock
    private StokMapper stokMapper;
    @InjectMocks
    private StokService stokService;
    @Mock
    private StokHarektiService stokHarektiService;


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
        when(stokHarektiService.save(any())).thenReturn(null);

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