package com.nameproyect.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nameproyect.model.Usuario;
import com.nameproyect.repository.IUsuarioRepository;
import com.nameproyect.utils.enums.EstadoUsuario;
import com.nameproyect.utils.enums.Rol;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class UsuarioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private IUsuarioRepository userRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private Integer adminId;

    @BeforeEach
    void setup() {
        Usuario admin = Usuario.builder()
                .usuario("admin")
                .contrasena("123456")
                .estadoUsuario(EstadoUsuario.HABILITADO)
                .rol(Rol.ADMINISTRADOR)
                .build();

        admin = userRepository.save(admin);
        adminId = admin.getId();
    }

    @Test
    void testGetUserById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/auth/getUsers/{id}", adminId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(adminId))
                .andExpect(jsonPath("$.usuario").value("admin"))
                .andExpect(jsonPath("$.rol").value("ADMINISTRADOR"))
                .andExpect(jsonPath("$.estadoUsuario").value("HABILITADO"));
    }

    @Test
    void testGetAllUsers() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/auth/getAllUsers")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void testCrearUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/auth/crear")
                        .content("{\n" +
                                "    \"username\": \"franchesco\",\n" +
                                "    \"password\": \"123\",\n" +
                                "    \"role\": \"ADMINISTRADOR\"\n" +
                                "}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }
}