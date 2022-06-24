package jp.kobespiral.matcho.todo.repository;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import jp.kobespiral.matcho.todo.entity.Member;

@Repository

//Memberの出し入れ、keyはString。
public interface MemberRepository extends CrudRepository<Member, String>{
    List<Member> findAll();
}