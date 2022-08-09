package com.marul.rapor;

import com.marul.exception.RaporOlusturmaException;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class RaporService {

    public ByteArrayResource generateSimpleReport(List<RaporKriterleriDto> dataList) throws IOException {

        JasperReport compileReport = null;
        try {
            // todo jrxml dosyasi baska yerden okunacaktir.
            String fileName = "raporlar/musteri_email_rapor.jrxml";
            ClassLoader classLoader = this.getClass().getClassLoader();
            File configFile = new File(classLoader.getResource(fileName).getFile());
            FileInputStream inputStream = new FileInputStream(configFile);
            compileReport = JasperCompileManager
                    .compileReport(inputStream);
        } catch (JRException e) {
            log.error("rapor oluştururken hata", e.getMessage());
            throw new RaporOlusturmaException("rapor oluştururken hata", e.getMessage());
        }

        Map<String, Object> reportParameters = new HashMap<>();
//        reportParameters.put("musteriAdi", "müşterinin adı burada görünecektir.");

        return exportReportToPDF(compileReport, reportParameters, dataList);
    }

    private ByteArrayResource exportReportToPDF(JasperReport jasperReport, Map<String, Object> parameters, List<RaporKriterleriDto> data) {
        try {
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters,
                    new JRBeanCollectionDataSource(data));

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);

            byte[] reportContent = outputStream.toByteArray();
            return new ByteArrayResource(reportContent);
        } catch (Exception e) {
            log.error("Exporting report to PDF error: {}", e.getMessage());
            return null;
        }
    }

}
