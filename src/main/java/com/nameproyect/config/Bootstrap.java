package com.nameproyect.config;


import com.nameproyect.model.*;
import com.nameproyect.repository.*;
import com.nameproyect.utils.enums.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Profile("!prod")
@Component
@RequiredArgsConstructor
public class Bootstrap implements ApplicationRunner {
    private final IUsuarioRepository userRepository;
    private final IChoferRepository choferRepository;



    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("Ejecutando Bootstrap...");
        // Crear usuarios con builder
        Usuario admin = Usuario.builder()
                .usuario("admin")
                .contrasena("$2a$10$RRAzywJFxaAG3pRlHXep6u6VNKi5KOTT3M8GCxDPHpAyZ0ofX2Bcu")
                .estadoUsuario(EstadoUsuario.HABILITADO)
                .rol(Rol.ADMINISTRADOR)
                .build();

        Usuario operador = Usuario.builder()
                .usuario("operador")
                .contrasena("$2a$10$RRAzywJFxaAG3pRlHXep6u6VNKi5KOTT3M8GCxDPHpAyZ0ofX2Bcu")
                .estadoUsuario(EstadoUsuario.HABILITADO)
                .rol(Rol.OPERADOR)
                .build();

        Usuario supervisor = Usuario.builder()
                .usuario("supervisor")
                .contrasena("$2a$10$RRAzywJFxaAG3pRlHXep6u6VNKi5KOTT3M8GCxDPHpAyZ0ofX2Bcu")
                .estadoUsuario(EstadoUsuario.HABILITADO)
                .rol(Rol.SUPERVISOR)
                .build();

        Usuario gerente = Usuario.builder()
                .usuario("gerente")
                .contrasena("$2a$10$RRAzywJFxaAG3pRlHXep6u6VNKi5KOTT3M8GCxDPHpAyZ0ofX2Bcu")
                .estadoUsuario(EstadoUsuario.HABILITADO)
                .rol(Rol.GERENTE)
                .build();

        userRepository.saveAll(Arrays.asList(admin, operador, supervisor, gerente));



        Usuario usuarioChofer = Usuario.builder()
                .usuario("chofer")
                .contrasena("$2a$10$RRAzywJFxaAG3pRlHXep6u6VNKi5KOTT3M8GCxDPHpAyZ0ofX2Bcu")
                .rol(Rol.CHOFER)
                .estadoUsuario(EstadoUsuario.HABILITADO)
                .build();

        Chofer chofer = Chofer.builder()
                .usuario(usuarioChofer)
                .nombre("chofer1")
                .build();

        choferRepository.saveAll(Collections.singletonList(chofer));

    }

}
