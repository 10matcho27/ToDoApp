package jp.kobespiral.matcho.todo.dto;

import java.util.Date;

import jp.kobespiral.matcho.todo.entity.ToDo;
import lombok.Data;

@Data

public class ToDoForm {
    // Long seq;
    String title;

    public ToDo toEntity(){
        Date date = new Date();
        ToDo t = new ToDo(null, title, null, false, date, null);
        return t;
    }
}
