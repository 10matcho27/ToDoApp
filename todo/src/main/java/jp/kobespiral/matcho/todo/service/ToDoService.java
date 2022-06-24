package jp.kobespiral.matcho.todo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service; 

import jp.kobespiral.matcho.todo.dto.MemberForm;
import jp.kobespiral.matcho.todo.dto.ToDoForm;
import jp.kobespiral.matcho.todo.entity.Member;
import jp.kobespiral.matcho.todo.entity.ToDo;
import jp.kobespiral.matcho.todo.exception.ToDoAppException;
import jp.kobespiral.matcho.todo.repository.MemberRepository;
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
        ToDo t = form.toEntity();
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
}
