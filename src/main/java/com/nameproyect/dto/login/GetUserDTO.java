package com.nameproyect.dto.login;

import com.nameproyect.utils.enums.Rol;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetUserDTO {
    private Integer id;
    private String username;
    private Rol role;
    private String estado;
}
