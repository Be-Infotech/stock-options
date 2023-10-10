package fr.beit.projetcac.payload;

import lombok.Data;

@Data
public class LoginDto {
    private String usernameOrMail;
    private String password;
}
