package br.com.ifba.smartstock.user.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginPostRequestDto {
    @JsonProperty("login")
    @NotEmpty
    @NotNull
    private String login;

    @JsonProperty("password")
    @NotEmpty
    @NotNull
    private String password;
}
