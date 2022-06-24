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
import jp.kobespiral.matcho.todo.dto.ToDoForm;
import jp.kobespiral.matcho.todo.entity.Member;
import jp.kobespiral.matcho.todo.entity.ToDo;
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
   public String showList(@PathVariable String mid, Model model){
      Member m = mService.getMember(mid);
      List<ToDo> toDos = tService.getToDoList(mid);
      List<ToDo> dones = tService.getDoneList(mid);
      ToDoForm form = new ToDoForm();
      model.addAttribute("toDos", toDos);
      model.addAttribute("dones", dones);
      model.addAttribute("member", m);
      model.addAttribute("ToDoForm", form);
      return "list";
   }

   @PostMapping("/{mid}/list/add_toDo")
   public String addToDo(@PathVariable String mid, @ModelAttribute(name = "ToDoForm") ToDoForm form, Model model){
      ToDo t = tService.createToDo(mid, form);
      return "redirect:/" + mid + "/list";
   }
   
   @GetMapping("/{seq}/{mid}/list/doneToDo")
   public String doneToDo(@PathVariable String seq, @PathVariable String mid, Model model){
      Long seq_l = Long.parseLong(seq);
      tService.changeToDone(seq_l);
      return "redirect:/" + mid + "/list";
   }

   @GetMapping("/{seq}/{mid}/list/InvDoneToDo")
   public String InvDoneToDo(@PathVariable String seq, @PathVariable String mid, Model model){
      Long seq_l = Long.parseLong(seq);
      tService.changeToDo(seq_l);
      return "redirect:/" + mid + "/list";
   }

   @GetMapping("/allUserToDoList")
   public String showAllUsersList(Model model){
      List<ToDo> allToDos = tService.getToDoList();
      List<ToDo> allDones = tService.getDoneList();
      model.addAttribute("allToDos", allToDos);
      model.addAttribute("allDones", allDones);
      return "allUsersList";
   }
}
