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

    @PatchMapping("/update-name/{id}")
    public ResponseEntity<?> updateName(@PathVariable Integer id,
                                        @RequestBody RequestClient name) {

        return updateClientService.updateName(id, name);
    }

}
