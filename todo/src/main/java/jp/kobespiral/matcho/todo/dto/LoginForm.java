package jp.kobespiral.matcho.todo.dto;

import lombok.Data;

@Data
public class LoginForm {
    String mid;    
}

//Formにアノテーション(@varidationなど)をつけることでチェックしてくれる。