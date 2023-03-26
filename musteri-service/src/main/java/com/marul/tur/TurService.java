package com.marul.tur;

import com.marul.exception.BulunamadiException;
import com.marul.exception.ZatenKayitliException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        Optional<Tur> tur = turRepository.findById(id);
        return tur.map(turMapper::getTarget)
                .orElseThrow(() -> new BulunamadiException("%s id ile tur bulunamadı", id.toString()));
    }

    public boolean existsByTurId(Long id) {
        return turRepository.existsById(id);
    }

    public void save(TurDto turDto) {
        if (turRepository.existsByTurAdi(turDto.getTurAdi())) {
            log.error("{} ile kaydedilmiş bir tur sisteme kayıtlıdır.", turDto.getTurAdi());
            throw new ZatenKayitliException("%s ile kaydedilmiş bir tur sisteme kayıtlıdır.", turDto.getTurAdi());
        }
        Tur tur = turMapper.getSource(turDto);
        turRepository.save(tur);
    }
}
