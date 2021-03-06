package jp.kobespiral.matcho.todo.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import jp.kobespiral.matcho.todo.entity.Member;
import lombok.Data;

//ブラウザからのリクエストえお受けるために必要。
//市役所でのフォーム。

@Data
public class MemberForm {
    @NotBlank
    @Pattern(regexp ="[a-z0-9_\\-]{4,16}")
    String mid; //メンバーID．英小文字，数字，ハイフン，アンダーバー．4文字以上16文字以下．

    @NotBlank
    @Size(min = 1, max = 32)
    String name; //名前 最大32文字

    @NotBlank
    @Size(min = 8)
    String password; //パスワード。暗号化済。

    String role = "MEMBER"; //ロール。default = "MEMBER"

    public Member toEntity() {
        Member m = new Member(mid, name, password, role); //後で記述
        return m;
    }
}