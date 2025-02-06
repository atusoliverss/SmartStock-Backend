package br.com.ifba.smartstock.user.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginPostRequestDto {
    @JsonProperty("login")
    @NotBlank(message = "The login cannot be empty")
    @NotNull(message = "The login is mandatory")
    private String login;

    @JsonProperty("password")
    @NotBlank(message = "The password cannot be empty")
    @NotNull(message = "The password is mandatory")
    private String password;
}
