package br.com.ifba.smartstock.user.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserPutRequestDto {
    @JsonProperty("name")
    @NotBlank(message = "The name cannot be empty")
    @NotNull(message = "The name is mandatory")
    private String name;

    @JsonProperty("login")
    @NotBlank( message = "The login cannot be empty")
    @NotNull(message = "The login is mandatory")
    @Size(min = 3, max = 30, message = "Login must have a minimum of 3 characters and a maximum of 30 characters")
    private String login;

    @JsonProperty("email")
    @Email(message = "Invalid email")
    private String email;
}
