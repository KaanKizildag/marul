package com.marul.musteri;

import com.marul.dto.MusteriDto;
import com.marul.exception.EmailDahaOnceAlinmisException;
import com.marul.tur.TurService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MusteriServiceTest {

    @Mock
    private MusteriRepository musteriRepository;
    @Mock
    private RaporServiceFeignClient raporServiceFeignClient;
    @Mock
    private MailSenderFeignClient mailSenderFeignClient;
    @Mock
    private TurService turService;
    @Mock
    private MusteriMapper musteriMapper;

    @InjectMocks
    private MusteriService musteriService;

    @Test
    void findAll() {
        // given
        List<Musteri> musteriList = Stream
                .of("Huseyin", "Kaan")
                .map(musteriAdi -> {
                    Musteri musteri = new Musteri();
                    musteri.setMusteriAdi(musteriAdi);
                    musteri.setTeslimatNoktasi("Çankaya");
                    musteri.setTelefonNo("00000");
                    musteri.setEmail(musteriAdi + "@marul.com.tr");
                    return musteri;
                })
                .collect(Collectors.toList());
        List<MusteriDto> musteriDtoList = Stream
                .of("Huseyin", "Kaan")
                .map(musteriAdi -> {
                    MusteriDto musteri = new MusteriDto();
                    musteri.setMusteriAdi(musteriAdi);
                    musteri.setTeslimatNoktasi("Çankaya");
                    musteri.setTelefonNo("00000");
                    musteri.setEmail(musteriAdi + "@marul.com.tr");
                    return musteri;
                })
                .collect(Collectors.toList());
        when(musteriRepository.findAll())
                .thenReturn(musteriList);
        when(musteriMapper.getTargetList(musteriList))
                .thenReturn(musteriDtoList);

        //when
        List<MusteriDto> actual = musteriService.findAll();
        //then

        List<MusteriDto> musteriDtoList2 = Stream
                .of("Huseyin", "Kaan")
                .map(musteriAdi -> {
                    MusteriDto musteri = new MusteriDto();
                    musteri.setMusteriAdi(musteriAdi);
                    musteri.setTeslimatNoktasi("Çankaya");
                    musteri.setTelefonNo("00000");
                    musteri.setEmail(musteriAdi + "@marul.com.tr");
                    return musteri;
                })
                .collect(Collectors.toList());
        assertEquals(musteriDtoList.size(), actual.size());

        for (int i = 0; i < musteriList.size(); i++) {
            assertEquals(musteriDtoList.get(i).getEmail(), actual.get(i).getEmail());
            assertEquals(musteriDtoList.get(i).getMusteriAdi(), actual.get(i).getMusteriAdi());
            assertEquals(musteriDtoList.get(i).getBorc(), actual.get(i).getBorc());
            assertEquals(musteriDtoList.get(i).getTelefonNo(), actual.get(i).getTelefonNo());
            assertEquals(musteriDtoList.get(i).getTeslimatNoktasi(), actual.get(i).getTeslimatNoktasi());
        }
    }

    @Test
    void findById() {
        String musteriAdi = "Kaan";
        long musteriId = 1L;
        Musteri musteri = new Musteri();
        musteri.setId(musteriId);
        musteri.setMusteriAdi(musteriAdi);
        musteri.setTeslimatNoktasi("Çankaya");
        musteri.setTelefonNo("00000");
        musteri.setEmail(musteriAdi + "@marul.com.tr");

        MusteriDto musteriDto = new MusteriDto();
        musteriDto.setId(musteriId);
        musteriDto.setMusteriAdi(musteriAdi);
        musteriDto.setTeslimatNoktasi("Çankaya");
        musteriDto.setTelefonNo("00000");
        musteriDto.setEmail(musteriAdi + "@marul.com.tr");

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

    @Test
    void delete() {
        long musteriId = 1L;
        musteriService.deleteById(musteriId);
        verify(musteriRepository).deleteById(musteriId);
    }

    @Test
    @Disabled
    void update() {
    }

    @Test
    void ayniMailIleKayitliMusteriYokIkenMusteriKaydedilebilmeli() {
        long musteriId = 1L;
        String musteriAdi = "Kaan";
        String email = musteriAdi + "@marul.com.tr";
        long turId = 1L;

        Musteri musteri = new Musteri();
        musteri.setId(musteriId);
        musteri.setMusteriAdi(musteriAdi);
        musteri.setTeslimatNoktasi("Çankaya");
        musteri.setTelefonNo("00000");
        musteri.setEmail(email);
        musteri.setTurId(turId);

        MusteriDto musteriDto = new MusteriDto();
        musteriDto.setId(musteriId);
        musteriDto.setMusteriAdi(musteriAdi);
        musteriDto.setTeslimatNoktasi("Çankaya");
        musteriDto.setTelefonNo("00000");
        musteriDto.setTurId(turId);
        musteriDto.setEmail(email);

        when(musteriRepository.existsByEmail(email))
                .thenReturn(Boolean.FALSE);
        when(musteriMapper.getSource(musteriDto))
                .thenReturn(musteri);
        when(musteriRepository.save(musteri))
                .thenReturn(musteri);

        musteriService.save(musteriDto);

        verify(musteriRepository).save(musteri);
        verify(musteriMapper).getSource(musteriDto);
        verify(musteriRepository).existsByEmail(email);
    }

    @Test
    void ayniMailIleKayitliMusteriVarIkenMusteriyiKaydetmemeli() {
        long musteriId = 1L;
        String musteriAdi = "Kaan";
        String email = musteriAdi + "@marul.com.tr";
        long turId = 1L;

        Musteri musteri = new Musteri();
        musteri.setId(musteriId);
        musteri.setMusteriAdi(musteriAdi);
        musteri.setTeslimatNoktasi("Çankaya");
        musteri.setTelefonNo("00000");
        musteri.setEmail(email);
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

        Assertions.assertThrows(EmailDahaOnceAlinmisException.class, () -> musteriService.save(musteriDto));

        verify(musteriRepository, times(0)).save(musteri);
        verify(musteriMapper, times(0)).getSource(musteriDto);
        verify(musteriRepository).existsByEmail(email);
    }
}