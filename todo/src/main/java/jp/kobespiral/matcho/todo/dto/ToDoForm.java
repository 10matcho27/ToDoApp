package jp.kobespiral.matcho.todo.dto;

import lombok.Data;

@Data
public class ToDoForm {
    // Long seq;
    String title;

    // public ToDo toEntity(){
    //     Date date = new Date();
    //     ToDo t = new ToDo(null, title, null, false, date, null);
    //     return t;
    // }
}
