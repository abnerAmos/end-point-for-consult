package com.example.demo.service;

import com.example.demo.model.Client;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@Slf4j
@Service
public class CreateExcelService {

    // Possivel substituir o "throws IOException" por "@SneakThrows" do Lombok
    public void createFile(final String fileName, final List<Client> client, HttpServletResponse response) throws IOException {
        log.info("Gerando o arquivo: {}", fileName);

        try (
                Workbook workbook = new XSSFWorkbook()) {
            var spreadsheet = workbook.createSheet("Lista de Clientes");
            int countNumberLine = 0;

            addHeader((XSSFSheet) spreadsheet, countNumberLine++);

            for (Client e : client) {
                var linha = spreadsheet.createRow(countNumberLine++);
                addCell(linha, 0, e.getId());
                addCell(linha, 1, e.getName());
                addCell(linha, 2, e.getAge().longValue());
                addCell(linha, 3, e.getEmail());
                addCell(linha, 4, String.valueOf(e.getStatus()));
            }

            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            String headerKey = "Content-Disposition";
            String headerValue = "attachment; filename= " + "file_test_" + fileName;
            response.setHeader(headerKey, headerValue);

            ServletOutputStream outputStream = response.getOutputStream();
            workbook.write(outputStream);

        } catch (FileNotFoundException e) {
            log.error("Arquivo não encontrado: {}", fileName);
        } catch (IOException e) {
            log.error("Erro ao processar o arquivo: {} ", fileName);
    }
        log.info("Arquivo gerado com sucesso!");
    }

    private void addHeader(XSSFSheet spreadsheet, int countNumberLine) {
        XSSFRow line = spreadsheet.createRow(countNumberLine);
        addCell(line, 0, "ID");
        addCell(line, 1, "NAME");
        addCell(line, 2, "AGE");
        addCell(line, 3, "EMAIL");
        addCell(line, 4, "STATUS");
    }

    private void addCell(Row line, int column, String valor) {
        Cell cell = line.createCell(column);
        cell.setCellValue(valor);
    }

    private void addCell(Row line, int column, Long valor) {
        Cell cell = line.createCell(column);
        cell.setCellValue(valor);
    }
}
