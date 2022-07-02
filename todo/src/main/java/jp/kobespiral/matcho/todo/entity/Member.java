package jp.kobespiral.matcho.todo.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
//↓getter, setterなどのコンストラクタ自動生成
@AllArgsConstructor
@NoArgsConstructor

@Entity
public class Member {
    @Id
    String mid;   //メンバーID
    String name;  //氏名
    //added 0701
    //パスワードとロール属性を追加。
    //パスワードは暗号化済のものを入れる。
    String password; //暗号化済
    String role;
}