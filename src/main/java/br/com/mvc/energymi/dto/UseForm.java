package br.com.mvc.energymi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UseForm {
    @NotBlank
    private String username;

    @NotBlank
    @Size(min = 5)
    private String password;
}
