package com.marul.tur;

import com.marul.exception.AlreadyExistsException;
import com.marul.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TurService {

    private final TurMapper turMapper;
    private final TurRepository turRepository;

    public List<TurDto> findAll() {
        List<Tur> turList = turRepository.findAll();
        return turMapper.getTargetList(turList);
    }

    public TurDto findById(Long id) {
        return turRepository.findById(id)
                .map(turMapper::getTarget)
                .orElseThrow(() -> new NotFoundException("Tur bulunamadı. id: %d", id));
    }

    public boolean existsByTurId(Long id) {
        return turRepository.existsById(id);
    }

    public TurDto save(TurDto turDto) {
        String turAdi = turDto.getTurAdi();
        if (turRepository.existsByTurAdi(turAdi)) {
            String errorMessage = String.format("%s ile kaydedilmiş bir tur sisteme kayıtlıdır.", turAdi);
            log.error(errorMessage);
            throw new AlreadyExistsException(errorMessage);
        }
        Tur tur = turMapper.getSource(turDto);
        tur = turRepository.save(tur);
        return turMapper.getTarget(tur);
    }
}
