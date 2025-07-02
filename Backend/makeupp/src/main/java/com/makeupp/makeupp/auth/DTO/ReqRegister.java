package com.makeupp.makeupp.auth.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ReqRegister {
    private String name;
    private String email;
    private String password;
    private String address;
    private String phone;
    private String role;
    private boolean status;
}
