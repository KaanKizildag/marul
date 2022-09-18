package com.marul.rapor;

import com.marul.dto.rapor.RaporDto;
import com.marul.dto.rapor.RaporOlusturmaDto;
import com.marul.exception.RaporOlusturmaException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class RaporService {

    private final RaporServiceConfigData raporServiceConfigData;

    public byte[] generateSimpleReport(RaporOlusturmaDto raporOlusturmaDto) throws IOException {

        JasperReport compileReport;
        try {
            String raporDizini = raporServiceConfigData.getRaporlarDizini();
            String raporAdi = raporOlusturmaDto.getRaporAdi();
            String fileName = raporDizini + raporAdi;
            ClassLoader classLoader = getClass().getClassLoader();
            InputStream inputStream = classLoader.getResourceAsStream(fileName);
            log.info("file: {}", classLoader.getResource(fileName).getFile());
            compileReport = JasperCompileManager.compileReport(inputStream);
        } catch (Exception e) {
            log.error("rapor oluştururken hata: {}", e.getMessage());
            throw new RaporOlusturmaException("rapor oluştururken hata: %s", e.getMessage());
        }

        List<RaporDto> raporDtoList = raporOlusturmaDto.getRaporDtoList();
        Map<String, Object> raporParametreleri = raporOlusturmaDto.getRaporParametreleri();

        return exportReportToPDF(compileReport, raporParametreleri, raporDtoList);
    }

    private byte[] exportReportToPDF(JasperReport jasperReport, Map<String, Object> parameters, List<RaporDto> data) {
        try {
            log.info("exportReportToPDF:\n rapor kriterleri: {},\n parametreler: {}\n", data, parameters);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters,
                    new JRBeanCollectionDataSource(data));

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);

            return outputStream.toByteArray();
        } catch (Exception e) {
            log.error("Exporting report to PDF error: {}", e.getMessage());
            throw new RaporOlusturmaException("rapor oluştururken hata: %s", e.getMessage());
        }
    }

}
