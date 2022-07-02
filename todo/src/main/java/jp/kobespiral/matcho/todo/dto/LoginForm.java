package jp.kobespiral.matcho.todo.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class LoginForm {
    @NotBlank
    @Pattern(regexp ="[a-z0-9_\\-]{4,16}")
    String mid;
    
    @NotBlank
    @Size(min = 8)
    String password;
}

//Formにアノテーション(@varidationなど)をつけることでチェックしてくれる。