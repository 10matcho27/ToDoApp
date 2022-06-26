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

import jp.kobespiral.matcho.todo.dto.MemberForm;
import jp.kobespiral.matcho.todo.entity.Member;
import jp.kobespiral.matcho.todo.service.MemberService;

@Controller
@RequestMapping("/admin")
public class MemberController {
   @Autowired
   MemberService mService;
   /**
    * 管理者用・ユーザ登録ページ HTTP-GET /admin/register
    * @param model
    * @return
    */
   @GetMapping("/register")
   String showUserForm(Model model) {
       List<Member> members = mService.getAllMembers();
       model.addAttribute("members", members);
       MemberForm form = new MemberForm();
       model.addAttribute("MemberForm", form);
       
       return "register";
   }
   /**
    * 管理者用・ユーザ登録確認ページを表示 HTTP-POST /admin/check
    * @param form
    * @param model
    * @return
    */
   @PostMapping("/check") 
   String checkUserForm(@ModelAttribute(name = "MemberForm") MemberForm form,  Model model) {
       model.addAttribute("MemberForm", form);
       return "check";
   }
   /**
    * 管理者用・ユーザ登録処理 -> 完了ページ HTTP-POST /admin/register
    * @param form
    * @param model
    * @return
    */
   @PostMapping("/register")
   String createUser(@ModelAttribute(name = "MemberForm") MemberForm form,  Model model) {
       Member m =  mService.createMember(form);
       model.addAttribute("MemberForm", m);
       return "registered";
   }
   /**
    * 管理者用・ユーザ削除処理　HTTP-GET /admin/delete/{mid}
    * @param mid
    * @param model
    * @return
    */
   @GetMapping("/delete/{mid}")
   String deleteUser(@PathVariable String mid, Model model) {
       mService.deleteMember(mid);
       return showUserForm(model);
       // return "redirect:/admin/register
   }
}
