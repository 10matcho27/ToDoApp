package jp.kobespiral.matcho.todo.dto;

import jp.kobespiral.matcho.todo.entity.Member;
import lombok.Data;

//ブラウザからのリクエストえお受けるために必要。
//市役所でのフォーム。

@Data
public class MemberForm {
    String mid; //メンバーID．
    String name; //名前

    public Member toEntity() {
        Member m = new Member(mid, name);
        return m;
    }
}