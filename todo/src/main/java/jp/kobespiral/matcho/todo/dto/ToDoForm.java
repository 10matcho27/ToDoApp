package jp.kobespiral.matcho.todo.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class ToDoForm {
    // Long seq;
    @NotBlank
    @Size(min = 1, max = 64)
    String title;

    // public ToDo toEntity(){
    //     Date date = new Date();
    //     ToDo t = new ToDo(null, title, null, false, date, null);
    //     return t;
    // }
}
