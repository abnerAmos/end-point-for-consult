package com.example.demo.controller;

import com.example.demo.request.RequestClient;
import com.example.demo.service.UpdateClientServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class UpdateController {

    private final UpdateClientServiceImpl updateClientService;

    // Altera apenas uma informação de um recurso
    @PatchMapping("/update-name/{id}")
    public ResponseEntity<?> updateName(@PathVariable Integer id,
                                        @RequestBody RequestClient name) {

        return updateClientService.updateName(id, name);
    }

    // Altera um recurso (2 ou mais informações)
    @PutMapping("/update-client/{id}")
    public ResponseEntity<?> updateClient(@PathVariable Integer id,
                                          @RequestBody RequestClient client) {

        return updateClientService.updateClient(id, client);
    }
}
