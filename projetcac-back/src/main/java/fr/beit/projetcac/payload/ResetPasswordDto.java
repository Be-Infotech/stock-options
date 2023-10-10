package fr.beit.projetcac.payload;

import lombok.Data;

@Data
public class ResetPasswordDto {
    private String usernameOrMail;
}
