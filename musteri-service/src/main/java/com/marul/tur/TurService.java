package com.marul.tur;

import com.marul.exception.BulunamadiException;
import com.marul.exception.ZatenKayitliException;
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
                .orElseThrow(() -> new BulunamadiException("Tur bulunamadı. id: %d", id));
    }

    public boolean existsByTurId(Long id) {
        return turRepository.existsById(id);
    }

    public void save(TurDto turDto) {
        String turAdi = turDto.getTurAdi();
        if (turRepository.existsByTurAdi(turAdi)) {
            log.error("{} ile kaydedilmiş bir tur sisteme kayıtlıdır.", turAdi);
            throw new ZatenKayitliException("%s ile kaydedilmiş bir tur sisteme kayıtlıdır.", turAdi);
        }
        Tur tur = turMapper.getSource(turDto);
        turRepository.save(tur);
    }
}
