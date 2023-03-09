package com.example.demo.service;

import com.example.demo.enums.Status;
import com.example.demo.model.Client;
import com.example.demo.repository.ClientRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.ExcelNumberFormat;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class ReadAndSaveExcelService {

    @Autowired
    private ClientRepository clientRepository;

    public List<Client> readFile(final String nameFile) {
        log.info("Lendo arquivo {}", nameFile);
        List<Client> clients = new ArrayList<>();

        try (FileInputStream excelFile = new FileInputStream(nameFile)) {
            var workbook = new XSSFWorkbook(excelFile);
            var firstTab = workbook.getSheetAt(0);

            int countLine = 0;
            for (Row line : firstTab) {
                if (++countLine == 1) continue;
                Client client = new Client();
                client.setName(line.getCell(1).getStringCellValue());
                client.setAge((int) line.getCell(2).getNumericCellValue());
                client.setEmail(line.getCell(3).getStringCellValue());
                client.setStatus(Status.valueOf(line.getCell(4).getStringCellValue()));
                clients.add(client);
                log.info("Lendo Cliente {}", client);

                clientRepository.saveAll(clients);
                log.info("Adicionando cliente na DB");
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
