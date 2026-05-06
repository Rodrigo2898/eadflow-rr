package com.rr.plataformaead.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface BaseController<RequestDTO, ResponseDTO, UpdateRequestDTO> {

    @GetMapping("/{id}")
    ResponseEntity<ResponseDTO> findById(@PathVariable Long id);

    @GetMapping
    ResponseEntity<List<ResponseDTO>> findAll();

    @PostMapping
    ResponseEntity<String> create(@RequestBody @Valid RequestDTO dto);

    @PutMapping("/{id}")
    ResponseEntity<String> update(@PathVariable Long id, @RequestBody @Valid UpdateRequestDTO dto);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@PathVariable Long id);
}
