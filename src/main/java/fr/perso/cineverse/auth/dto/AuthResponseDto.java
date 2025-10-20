package fr.perso.cineverse.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponseDto {

    private String  accessToken;
    private String  tokenType = "Bearer";
    private Long    id;
    private String  pseudo;
    private String  role;

    public AuthResponseDto(String accessToken, Long id, String pseudo, String role) {
        this.accessToken = accessToken;
        this.id = id;
        this.pseudo = pseudo;
        this.role = role;
    }
}
