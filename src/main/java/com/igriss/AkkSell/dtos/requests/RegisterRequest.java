package com.igriss.AkkSell.dtos.requests;
import com.igriss.AkkSell.security.roles.Role;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private String firstname;
    private String lastname;
    private String phoneNumber;
    private String email;
    private String password;
    private Role role;
}
