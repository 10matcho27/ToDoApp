package jp.kobespiral.matcho.todo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jp.kobespiral.matcho.todo.dto.MemberForm;
import jp.kobespiral.matcho.todo.entity.Member;
import jp.kobespiral.matcho.todo.service.MemberService;

@Controller
@RequestMapping("/sign_up")
public class SignUpController {
    @Autowired
    MemberService mService;

    /**
     * 一般ユーザの登録ページ HTTP-GET /sign_up
     * 
     * @param model
     * @return
     */
    @GetMapping("")
    String showSignUpForm(@ModelAttribute MemberForm form, Model model) {
        model.addAttribute("MemberForm", form);

        return "signup";
    }

    /**
     * ユーザ登録確認ページを表示 HTTP-POST /sign_up/check
     * 
     * @param form
     * @param model
     * @return
     */
    @PostMapping("/check")
    String checkMemberForm(@Validated @ModelAttribute(name = "MemberForm") MemberForm form, BindingResult bindingResult,
            Model model) {
        // 入力チェックに引っかかった場合、ユーザー登録画面に戻る
        if (bindingResult.hasErrors()) {
            // GETリクエスト用のメソッドを呼び出して、ユーザー登録画面に戻る
            return showSignUpForm(form, model);
        }

        model.addAttribute("MemberForm", form);

        return "signup_check";
    }

    /**
     * ユーザ登録処理 -> 完了ページ HTTP-POST /sign_up/register
     * 
     * @param form
     * @param model
     * @return
     */
    @PostMapping("")
    String createMember(@ModelAttribute(name = "MemberForm") MemberForm form, Model model) {
        System.out.println("test1");
        Member m = mService.createMember(form);
        System.out.println("test2");
        model.addAttribute("MemberForm", m);
        System.out.println("test3");

        return "signup_complete";
    }

}