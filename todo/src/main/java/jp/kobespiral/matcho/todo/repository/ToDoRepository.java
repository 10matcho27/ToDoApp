package jp.kobespiral.matcho.todo.repository;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import jp.kobespiral.matcho.todo.entity.ToDo;

@Repository

//ToDoの出し入れ、keyはLong。
public interface ToDoRepository extends CrudRepository<ToDo, Long>{
    List<ToDo> findAll();
    //midとdoneの値でToDoを検索するメソッド
    // → ユーザのToDoリスト，Doneリストを取得するのに使う
    List<ToDo> findByMidAndDone(String mid, Boolean done);
    //doneの値でToDoを検索するメソッド
    // → 全員のToDoリスト，Doneリストを取得するのに使う
    List<ToDo> findByDone(Boolean done);
    List<ToDo> findByMid(String mid);
}