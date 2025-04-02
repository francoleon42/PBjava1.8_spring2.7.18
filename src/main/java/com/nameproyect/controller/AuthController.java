package com.nameproyect.controller;

import com.nameproyect.dto.login.GetUserDTO;
import com.nameproyect.dto.login.LoginRequestDTO;
import com.nameproyect.dto.login.RegisterRequestDTO;
import com.nameproyect.dto.login.UpdateRequestDTO;
import com.nameproyect.service.IAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final IAuthService authService;


    @PostMapping("/crear")
    public ResponseEntity<?> crear(@RequestBody RegisterRequestDTO requestDTO) {
        authService.crear(requestDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody UpdateRequestDTO updateRequestDto) {
        authService.update(id, updateRequestDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping("/inhabilitar/{id}")
    public ResponseEntity<?> inhabilitar(@PathVariable Integer id) {
        authService.inhabilitar(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/habilitar/{id}")
    public ResponseEntity<?> habilitar(@PathVariable Integer id) {
        authService.habilitar(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/getAllUsers")
    public ResponseEntity<List<GetUserDTO>> getAll() {
        return new ResponseEntity<>(authService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/getUsers/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Integer id) {
        return new ResponseEntity<>(authService.getById(id), HttpStatus.OK);
    }
}
