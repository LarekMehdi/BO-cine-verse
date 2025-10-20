package fr.perso.cineverse.user.dto;

import fr.perso.cineverse.constants.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    
    @NotBlank(message = "L'email ne doit pas être vide")
    @Email(message = "L'email doit être valide")
    private String      email;

    @NotBlank(message = "Le pseudo ne doit pas être vide") 
    @Size(min = 2, max = 50, message = "Le pseudo doit contenir entre 2 et 50 caractères")
    private String      pseudo;

    @NotBlank(message = "Le password ne doit pas être vide")
    @Size(min = 6, message = "Le password doit contenir au moins 6 caractères")
    private String      password;

    private Role        role;

}
