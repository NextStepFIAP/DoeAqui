package br.com.fiap.doeaqui.controller;

import br.com.fiap.doeaqui.model.User;
import br.com.fiap.doeaqui.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping
    public ModelAndView index() {
        return service.index();
    }

    @RequestMapping("new")
    public String create(User user) {
        return service.create();
    }

    @PostMapping
    public String save(@Valid User user, BindingResult result, RedirectAttributes redirect) {
        return service.save(user, result, redirect);
    }

    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes redirect) {
        return service.delete(id, redirect);
    }

    //UPDATE USER
    @GetMapping("/{id}")
    public ModelAndView editView(@PathVariable Long id){
        return service.editView(id);
    }

    @PostMapping("/update")
    public String update(@Valid User newUser, BindingResult result, RedirectAttributes redirect){
        return service.edit(newUser, result, redirect);
    }

}
