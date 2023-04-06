package com.marul.musteri;

import com.marul.dto.musteri.MusteriDto;
import com.marul.exception.AlreadyExistsException;
import com.marul.tur.TurDto;
import com.marul.tur.TurService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MusteriServiceTest {

    @Mock
    private MusteriRepository musteriRepository;
    @Mock
    private MusteriMapper musteriMapper;
    @Mock
    private TurService turService;
    @InjectMocks
    private MusteriService musteriService;

    @Test
    void findAll() {
        // given
        List<Musteri> musteriList = Collections.singletonList(getMusteri());
        List<MusteriDto> musteriDtoList = Collections.singletonList(getMusteriDto());
        when(musteriRepository.findAll()).thenReturn(musteriList);
        when(musteriMapper.getTargetList(musteriList)).thenReturn(musteriDtoList);
        when(turService.findById(anyLong())).thenReturn(new TurDto());
        // when
        List<MusteriDto> actual = musteriService.findAll();
        //then
        Assertions.assertEquals(actual.size(), musteriDtoList.size());
        verify(musteriRepository).findAll();
        verify(musteriMapper).getTargetList(musteriList);
    }

    @Test
    void findById() {
        long musteriId = 1L;
        Musteri musteri = getMusteri();
        MusteriDto musteriDto = getMusteriDto();
        when(musteriRepository.findById(musteriId))
                .thenReturn(Optional.of(musteri));
        when(musteriMapper.getTarget(musteri))
                .thenReturn(musteriDto);

        MusteriDto actual = musteriService.findById(musteriId);

        assertEquals(actual.getId(), musteriDto.getId());
        assertEquals(actual.getMusteriAdi(), musteriDto.getMusteriAdi());
        assertEquals(actual.getBorc(), musteriDto.getBorc());
        assertEquals(actual.getEmail(), musteriDto.getEmail());
        assertEquals(actual.getTeslimatNoktasi(), musteriDto.getTeslimatNoktasi());

        verify(musteriRepository).findById(musteriId);
        verify(musteriMapper).getTarget(musteri);

    }

    private MusteriDto getMusteriDto() {
        MusteriDto musteriDto = new MusteriDto();
        musteriDto.setId(1L);
        musteriDto.setTurId(1L);
        musteriDto.setTurAdi("Ankara");
        musteriDto.setMusteriAdi("marul");
        musteriDto.setTeslimatNoktasi("Çankaya");
        musteriDto.setTelefonNo("00000");
        musteriDto.setEmail("musteri@marul.com.tr");
        return musteriDto;
    }

    private Musteri getMusteri() {
        Musteri musteri = new Musteri();
        musteri.setId(1L);
        musteri.setTurId(1L);
        musteri.setMusteriAdi("marul");
        musteri.setTeslimatNoktasi("Çankaya");
        musteri.setTelefonNo("00000");
        musteri.setEmail("musteri@marul.com.tr");
        return musteri;
    }

    @Test
    void delete() {
        long musteriId = 1L;

        Mockito.when(musteriRepository.findById(any()))
                .thenReturn(Optional.of(getMusteri()));

        musteriService.deleteById(musteriId);
        verify(musteriRepository).delete(any());
    }


    @Test
    void ayniMailIleKayitliMusteriYokIkenMusteriKaydedilebilmeli() {

        Musteri musteri = getMusteri();

        MusteriDto musteriDto = getMusteriDto();

        when(musteriRepository.existsByEmail(musteriDto.getEmail()))
                .thenReturn(Boolean.FALSE);
        when(musteriMapper.getSource(musteriDto))
                .thenReturn(musteri);
        when(musteriRepository.save(musteri))
                .thenReturn(musteri);
        when(turService.existsByTurId(anyLong())).thenReturn(Boolean.TRUE);

        musteriService.save(musteriDto);

        verify(musteriRepository).save(musteri);
        verify(musteriMapper).getSource(musteriDto);
        verify(musteriRepository).existsByEmail(musteriDto.getEmail());
    }

    @Test
    void ayniMailIleKayitliMusteriVarIkenMusteriyiKaydetmemeli() {
        long musteriId = 1L;
        String musteriAdi = "Kaan";
        String email = musteriAdi + "@marul.com.tr";
        long turId = 1L;

        Musteri musteri = getMusteri();
        musteri.setTurId(turId);

        MusteriDto musteriDto = new MusteriDto();
        musteriDto.setId(musteriId);
        musteriDto.setMusteriAdi(musteriAdi);
        musteriDto.setTeslimatNoktasi("Çankaya");
        musteriDto.setTelefonNo("00000");
        musteriDto.setTurId(turId);
        musteriDto.setEmail(email);

        when(musteriRepository.existsByEmail(email))
                .thenReturn(Boolean.TRUE);

        Assertions.assertThrows(AlreadyExistsException.class, () -> musteriService.save(musteriDto));

        verify(musteriRepository, times(0)).save(musteri);
        verify(musteriMapper, times(0)).getSource(musteriDto);
        verify(musteriRepository).existsByEmail(email);
    }
}