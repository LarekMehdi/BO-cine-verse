package fr.perso.cineverse.auth.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SigninDto {

    @NotBlank(message = "Le pseudo ne doit pas être vide") 
    @Size(min = 2, max = 50, message = "Le pseudo doit contenir entre 2 et 50 caractères")
    private String      pseudo;

    @NotBlank(message = "Le password ne doit pas être vide")
    @Size(min = 6, message = "Le password doit contenir au moins 6 caractères")
    private String      password;
    
}
