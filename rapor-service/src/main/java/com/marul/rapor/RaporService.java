package com.marul.rapor;

import com.marul.dto.rapor.RaporDto;
import com.marul.dto.rapor.RaporOlusturmaDto;
import com.marul.exception.RaporOlusturmaException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import static net.sf.jasperreports.engine.JasperFillManager.fillReport;

@Service
@Slf4j
@RequiredArgsConstructor
public class RaporService {

    private final RaporServiceConfigData raporServiceConfigData;

    public byte[] generateSimpleReport(RaporOlusturmaDto raporOlusturmaDto, ExportReportType exportReportType) {

        JasperReport compileReport;
        try {
            String raporDizini = raporServiceConfigData.getRaporlarDizini();
            String raporAdi = raporOlusturmaDto.getRaporAdi();
            String fileName = raporDizini + raporAdi;
            ClassLoader classLoader = getClass().getClassLoader();
            InputStream inputStream = classLoader.getResourceAsStream(fileName);
            log.debug("file: {}", classLoader.getResource(fileName).getFile());
            compileReport = JasperCompileManager.compileReport(inputStream);
        } catch (Exception e) {
            String message = String.format("rapor oluştururken hata: %s", e.getMessage());
            log.error(message);
            throw new RaporOlusturmaException(message);
        }

        List<RaporDto> raporDtoList = raporOlusturmaDto.getRaporDtoList();
        Map<String, Object> raporParametreleri = raporOlusturmaDto.getRaporParametreleri();

        return switch (exportReportType) {
            case PDF -> exportReportToPDF(compileReport, raporParametreleri, raporDtoList);
            case EXCEL -> exportReportToXlsx(compileReport, raporParametreleri, raporDtoList);
        };
    }

    private byte[] exportReportToPDF(JasperReport jasperReport, Map<String, Object> parameters, List<RaporDto> data) {
        try {
            log.debug("exportReportToPDF: rapor kriterleri: {} tane , parametreler: {}", data.size(), parameters);
            JasperPrint jasperPrint = fillReport(jasperReport, parameters, new JRBeanCollectionDataSource(data));

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);

            return outputStream.toByteArray();
        } catch (Exception e) {
            String message = String.format("rapor oluştururken hata: %s", e.getMessage());
            log.error(message);
            throw new RaporOlusturmaException(message);
        }
    }

    private byte[] exportReportToXlsx(JasperReport jasperReport, Map<String, Object> parameters, List<RaporDto> data) {

        try {
            JasperPrint jasperPrint = fillReport(jasperReport, parameters, new JRBeanCollectionDataSource(data));
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            SimpleXlsxReportConfiguration reportConfigXLS = excelReportConfigurations();

            JRXlsxExporter exporter = new JRXlsxExporter();
            exporter.setConfiguration(reportConfigXLS);
            exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
            exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputStream));

            exporter.exportReport();

            return outputStream.toByteArray();
        } catch (Exception e) {
            String message = String.format("rapor oluştururken hata: %s", e.getMessage());
            log.error(message);
            throw new RaporOlusturmaException(message);
        }
    }

    private SimpleXlsxReportConfiguration excelReportConfigurations() {
        SimpleXlsxReportConfiguration reportConfigXLS = new SimpleXlsxReportConfiguration();
        reportConfigXLS.setSheetNames(new String[]{"Sheet 1"});
        reportConfigXLS.setCellHidden(false);
        reportConfigXLS.setWhitePageBackground(true);
        reportConfigXLS.setDetectCellType(true);
        return reportConfigXLS;
    }

}
