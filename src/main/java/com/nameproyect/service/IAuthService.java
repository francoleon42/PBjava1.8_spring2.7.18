package com.nameproyect.service;

import com.nameproyect.dto.login.*;
import com.nameproyect.model.Usuario;

import java.util.List;

public interface IAuthService {

    void crear(RegisterRequestDTO requestDTO);
    void update(Integer id, UpdateRequestDTO userToUpdateDto);
    void habilitar(Integer id);
    void inhabilitar(Integer id);
    List<GetUserDTO> getAll();
    Usuario getById(Integer id);
}
