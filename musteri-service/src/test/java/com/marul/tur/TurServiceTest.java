package com.marul.tur;

import com.marul.exception.AlreadyExistsException;
import com.marul.exception.NotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TurServiceTest {

    @Mock
    private TurMapper turMapper;

    @Mock
    private TurRepository turRepository;

    @InjectMocks
    private TurService turService;

    @Test
    void testFindAll() {
        // Given
        List<Tur> turList = Arrays.asList(new Tur(), new Tur());
        List<TurDto> turDtoList = Arrays.asList(new TurDto(), new TurDto());
        when(turRepository.findAll()).thenReturn(turList);
        when(turMapper.getTargetList(turList)).thenReturn(turDtoList);

        // When
        List<TurDto> result = turService.findAll();

        // Then
        assertEquals(turDtoList, result);
        verify(turRepository).findAll();
        verify(turMapper).getTargetList(turList);
    }

    @Test
    void testFindById() {
        // Given
        Long id = 1L;
        Tur tur = new Tur();
        TurDto turDto = new TurDto();
        when(turRepository.findById(id)).thenReturn(Optional.of(tur));
        when(turMapper.getTarget(tur)).thenReturn(turDto);

        // When
        TurDto result = turService.findById(id);

        // Then
        assertEquals(turDto, result);
        verify(turRepository).findById(id);
        verify(turMapper).getTarget(tur);
    }

    @Test
    void testFindByIdNotFound() {
        // Given
        Long id = 1L;
        when(turRepository.findById(id)).thenReturn(Optional.empty());

        // When
        Assertions.assertThrows(NotFoundException.class, () -> turService.findById(id));

        // Then an exception is thrown
    }

    @Test
    void testExistsByTurId() {
        // Given
        Long id = 1L;
        when(turRepository.existsById(id)).thenReturn(true);

        // When
        boolean result = turService.existsByTurId(id);

        // Then
        assertTrue(result);
        verify(turRepository).existsById(id);
    }

    @Test
    void testSave() {
        // Given
        String turAdi = "Tur Adı";
        TurDto turDto = new TurDto();
        turDto.setTurAdi(turAdi);
        when(turRepository.existsByTurAdi(turAdi)).thenReturn(false);
        Tur tur = new Tur();
        when(turMapper.getSource(turDto)).thenReturn(tur);

        // When
        turService.save(turDto);

        // Then
        verify(turRepository).existsByTurAdi(turAdi);
        verify(turMapper).getSource(turDto);
        verify(turRepository).save(tur);
    }

    @Test
    void testSaveAlreadyExists() {
        // Given
        String turAdi = "Tur Adı";
        TurDto turDto = new TurDto();
        turDto.setTurAdi(turAdi);
        when(turRepository.existsByTurAdi(turAdi)).thenReturn(true);

        // When
        Assertions.assertThrows(AlreadyExistsException.class, () -> turService.save(turDto));

        // Then an exception is thrown
    }
}