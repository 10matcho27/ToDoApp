package jp.kobespiral.matcho.todo.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service; 

import jp.kobespiral.matcho.todo.dto.ToDoForm;
import jp.kobespiral.matcho.todo.entity.ToDo;
import jp.kobespiral.matcho.todo.exception.ToDoAppException;
import jp.kobespiral.matcho.todo.repository.ToDoRepository;

@Service
public class ToDoService {
    @Autowired
    ToDoRepository tRepo;
    /**
     * メンバーmidが新しくToDoを作成する
     * @param mid
     * @param form
     * @return
     */
    public ToDo createToDo(String mid, ToDoForm form){
        Date date = new Date();
        ToDo t = new ToDo(null, form.getTitle(), mid, false, date, null);
        return tRepo.save(t);
    }

    /**
     * 番号を指定してToDoを取得
     * @param seq
     * @return
     */
    public ToDo getToDo(Long seq){
        ToDo t = tRepo.findById(seq).orElseThrow(
            () -> new ToDoAppException(ToDoAppException.NO_SUCH_TODO_EXISTS, seq + ": No such ToDo exists"));
        return t;
    }

    /**
     * midのToDoリストを取得
     * @param mid
     * @return
     */
    public List<ToDo> getToDoList(String mid){
        return tRepo.findByMidAndDone(mid, false);
    }

    /**
     * midのDoneリストを取得
     * @param mid
     * @return
     */
    public List<ToDo> getDoneList(String mid){
        return tRepo.findByMidAndDone(mid, true);
    }

    /**
     * 全員のToDoリストを取得
     * @return
     */
    public List<ToDo> getToDoList(){
        return tRepo.findByDone(false);
    }

    /**
     * 全員のDoneリストを取得
     * @return
     */
    public List<ToDo> getDoneList(){
        return tRepo.findByDone(true);
    }

    /**
     * ToDoを完了に変更
     * @param seq
     */
    public ToDo changeToDone(Long seq){
        Date date = new Date();
        ToDo t = getToDo(seq);
        t.setDone(true);
        t.setDoneAt(date);
        return tRepo.save(t);
    }

    public ToDo changeToDo(Long seq){
        ToDo t = getToDo(seq);
        t.setDone(false);
        t.setDoneAt(null);
        return tRepo.save(t);
    }
}
