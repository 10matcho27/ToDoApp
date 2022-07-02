package jp.kobespiral.matcho.todo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jp.kobespiral.matcho.todo.dto.LoginForm;
import jp.kobespiral.matcho.todo.dto.ToDoForm;
import jp.kobespiral.matcho.todo.entity.Member;
import jp.kobespiral.matcho.todo.entity.ToDo;
import jp.kobespiral.matcho.todo.exception.ToDoAppException;
import jp.kobespiral.matcho.todo.service.MemberService;
import jp.kobespiral.matcho.todo.service.ToDoService;
import jp.kobespiral.matcho.todo.dto.UserDetailsImpl;

@Controller
public class ToDoController {
   @Autowired
   ToDoService tService;

   @Autowired
   MemberService mService;

/**
     * トップページ
     */
    @GetMapping("/sign_in")
    String showIndex(@RequestParam Map<String, String> params, @ModelAttribute LoginForm form, Model model) {
        //パラメータ処理．ログアウト時は?logout, 認証失敗時は?errorが帰ってくる（WebSecurityConfig.java参照） 
		if (params.containsKey("sign_out")) {
			model.addAttribute("message", "サインアウトしました");
		} else if (params.containsKey("error")) {
			model.addAttribute("message", "サインインに失敗しました");
		} 
        //model.addAttribute("loginForm", loginForm);
        return "signin";
    }

    /**
     * ログイン処理．midの存在確認をして，ユーザページにリダイレクト
     */
    @GetMapping("/sign_in_success")
    String login() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Member m  = ((UserDetailsImpl) auth.getPrincipal()).getMember();
        if (m.getRole().equals("ADMIN")) {
            return "redirect:/admin/register";
        }
        return "redirect:/" + m.getMid() + "/list";
    }


   // @GetMapping("/")
   // public String loginPage(Model model){
   //  LoginForm l = new LoginForm();
   //  //こちらの情報をhtmlに渡す
   //  model.addAttribute("LoginForm", l);
   //  return "login";
   // }

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
   public String showList(@ModelAttribute(name = "ToDoForm") ToDoForm form, @PathVariable String mid, Model model){
      checkIdentity(mid);
      Member m = mService.getMember(mid);
      List<ToDo> toDos = tService.getToDoList(mid);
      List<ToDo> dones = tService.getDoneList(mid);
      model.addAttribute("toDos", toDos);
      model.addAttribute("dones", dones);
      model.addAttribute("member", m);
      model.addAttribute("ToDoForm", form);
      return "list";
      // return showList(form, mid, model);
   }

   @PostMapping("/{mid}/list/add_toDo")
   public String addToDo(@Validated @PathVariable String mid, @ModelAttribute(name = "ToDoForm") ToDoForm form, Model model){
      checkIdentity(mid);
      ToDo t = tService.createToDo(mid, form);
      // return "redirect:/" + mid + "/list";
   //    if (bindingResult.hasErrors()) {
   //       // GETリクエスト用のメソッドを呼び出して、ユーザー登録画面に戻る
   //       return showList(form, mid, model);
   //   }
      // return showList(form, mid, model);
      return "redirect:/" + mid + "/list";
   }
   
   @GetMapping("/{seq}/{mid}/list/doneToDo")
   public String doneToDo(@PathVariable String seq, @PathVariable String mid, Model model){
      checkIdentity(mid);
      Long seq_l = Long.parseLong(seq);
      tService.changeToDone(seq_l);
      return "redirect:/" + mid + "/list";
   }

   @GetMapping("/{seq}/{mid}/list/InvDoneToDo")
   public String InvDoneToDo(@PathVariable String seq, @PathVariable String mid, Model model){
      checkIdentity(mid);
      Long seq_l = Long.parseLong(seq);
      tService.changeToDo(seq_l);
      return "redirect:/" + mid + "/list";
   }

   @GetMapping("/{mid}/allUserToDoList")
   public String showAllUsersList(@PathVariable String mid, @Validated Model model){
      checkIdentity(mid);
      List<ToDo> allToDos = tService.getToDoList();
      List<ToDo> allDones = tService.getDoneList();
      model.addAttribute("allToDos", allToDos);
      model.addAttribute("allDones", allDones);
      return "allUsersList";
   }

   /**
     * 認可チェック．与えられたmidがログイン中のmidに等しいかチェックする
     */
    private void checkIdentity(String mid) {
      Authentication auth = SecurityContextHolder.getContext().getAuthentication();
      Member m  = ((UserDetailsImpl) auth.getPrincipal()).getMember();
      if (!mid.equals(m.getMid())) {
          throw new ToDoAppException(ToDoAppException.INVALID_TODO_OPERATION, 
          m.getMid() + ": not authorized to access resources of " + mid);
      }
  }
}
