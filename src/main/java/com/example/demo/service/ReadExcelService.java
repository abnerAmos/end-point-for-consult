package com.example.demo.service;

import com.example.demo.enums.Status;
import com.example.demo.model.Client;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class ReadExcelService {

    public List<Client> readFile(final String nameFile) {
        log.info("Lendo arquivo {}", nameFile);
        List<Client> clients = new ArrayList<>();

        try (FileInputStream excelFile = new FileInputStream(nameFile)) {
            var workbook = new XSSFWorkbook(excelFile);
            var firstTab = workbook.getSheetAt(0);

            int countLine = 0;
            for (Row line : firstTab) {
                if (++countLine == 1) continue;
                Client client = Client.builder()
                        .id((int) line.getCell(0).getNumericCellValue())
                        .name(line.getCell(1).getStringCellValue())
                        .age((int) line.getCell(2).getNumericCellValue())
                        .email(line.getCell(3).getStringCellValue())
                        .status(Status.valueOf(line.getCell(4).getStringCellValue()))
                        .build();
                clients.add(client);
                log.info("Lendo Cliente {}", client);
            }

        } catch (FileNotFoundException e) {
            log.error("Arquivo nao encontrado {}", nameFile);
        } catch (IOException e) {
            log.error("Erro ao processar o arquivo {}", nameFile);
        }
        log.info("Total de clientes lidos {}", clients.size());
        return clients;
    }
}
