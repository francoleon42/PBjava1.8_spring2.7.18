package com.nameproyect.service.impl;

import com.nameproyect.dto.login.*;
import com.nameproyect.exception.NotFoundException;
import com.nameproyect.model.Chofer;
import com.nameproyect.repository.IChoferRepository;
import com.nameproyect.repository.IUsuarioRepository;
import com.nameproyect.utils.enums.EstadoUsuario;
import com.nameproyect.model.Usuario;
import com.nameproyect.service.IAuthService;
import com.nameproyect.utils.enums.Rol;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements IAuthService {
    private final IUsuarioRepository userRepository;
    private final IChoferRepository choferRepository;



    private RolEntityDTO generateChoferResponseDTO(Usuario user) {
        Chofer chofer = choferRepository.findByUsuario_Id(user.getId())
                .orElseThrow(() -> new NotFoundException("No se encontró el chofer del id asignado al usuario id: " + user.getId()));
        return ChoferLoginResponseDTO.builder()
                .id(chofer.getId())
                .nombre(chofer.getNombre())
                .build();
    }


    @Override
    public void crear(RegisterRequestDTO requestDTO) {
        Usuario u  = Usuario.builder()
                .usuario(requestDTO.getUsername())
                .rol(Rol.getRol(requestDTO.getRole()))
                .contrasena(requestDTO.getPassword())
                .estadoUsuario(EstadoUsuario.HABILITADO)
                .build();
        userRepository.save(u);
    }

    @Override
    public void update(Integer id, UpdateRequestDTO userToUpdateDto) {
        Usuario user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("No se encontró el usuario con id: " + id));

        Optional.ofNullable(userToUpdateDto.getUsername()).ifPresent(user::setUsuario);

        userRepository.save(user);
    }

    @Override
    public void habilitar(Integer id) {
        Usuario user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("No se encontró el usuario con id: " + id));
        if(user.getEstadoUsuario() == EstadoUsuario.INHABILITADO) {
            user.setEstadoUsuario(EstadoUsuario.HABILITADO);
            userRepository.save(user);
        }
    }

    @Override
    public void inhabilitar(Integer id) {
        Usuario user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("No se encontró el usuario con id: " + id));
        if(user.getEstadoUsuario() == EstadoUsuario.HABILITADO) {
            user.setEstadoUsuario(EstadoUsuario.INHABILITADO);
            userRepository.save(user);
        }
    }


    @Override
    public List<GetUserDTO> getAll() {
        return userRepository.findAll().stream()
                .map(this::converTirGetUserDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Usuario getById(Integer id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Mensaje de error: " + id));
    }

    private GetUserDTO converTirGetUserDTO(Usuario user) {
        return GetUserDTO.builder()
                .id(user.getId())
                .username(user.getUsuario())
                .estado(user.getEstadoUsuario().toString())
                .role(user.getRol())
                .build();
    }

//    @Override
//    public void remove(Integer id) {
//        Usuario user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("No se encontró el usuario con id: " + id));
//        userRepository.delete(user);
//    }



    private GetUserDTO convertToGetUserDTO(Usuario user) {
        return GetUserDTO.builder()
                .id(user.getId())
                .username(user.getUsuario())
                .role(user.getRol())
                .estado(user.getEstadoUsuario().toString())
                .build();
    }
}
