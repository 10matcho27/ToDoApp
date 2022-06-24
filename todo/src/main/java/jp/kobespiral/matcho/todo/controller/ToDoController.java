package jp.kobespiral.matcho.todo.controller;

import java.util.List; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.kobespiral.matcho.todo.dto.LoginForm;
import jp.kobespiral.matcho.todo.dto.MemberForm;
import jp.kobespiral.matcho.todo.entity.Member;
import jp.kobespiral.matcho.todo.service.MemberService;
import jp.kobespiral.matcho.todo.service.ToDoService;

@Controller
public class ToDoController {
   @Autowired
   ToDoService tService;

   @Autowired
   MemberService mService;

   @GetMapping("/")
   public String loginPage(Model model){
    LoginForm l = new LoginForm();
    //こちらの情報をhtmlに渡す
    model.addAttribute("LoginForm", l);
    return "login";
   }

   @PostMapping("/")
   //htmlからの情報をもらう
   public String login(@ModelAttribute(name = "LoginForm") LoginForm form, Model model){
    // ログインできるかifで確認
    /// できない場合　return "error"
    /// できる場合
    // return "redirect:/" + form.getMid() + "/list";
    return "redirect:/" + form.getMid() + "/list";
   }

   @GetMapping("/{mid}/list")
   public String showList(@PathVariable String mid){
    return "list";
   }

}
