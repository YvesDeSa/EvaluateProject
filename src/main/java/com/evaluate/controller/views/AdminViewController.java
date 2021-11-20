package com.evaluate.controller.views;

import com.evaluate.model.Admin;
import com.evaluate.service.AdminService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/admins")
public class AdminViewController {
    @Autowired
    private AdminService service;
    
    @GetMapping
    public String getAll(Model model){
        model.addAttribute("admins", service.findAll());
        return "admins";
    }
    
    @GetMapping(path = "/admin")
    public String getAdmins(Model model){
        model.addAttribute("admin", new Admin());

        return "formAdmin";
    }
    
    @PostMapping(path = "/admin")
    public String save(@Valid @ModelAttribute Admin admin, BindingResult result , Model model){
        
        if(result.hasErrors()){
            model.addAttribute("msgErros", result.getAllErrors());
            return "formAdmin";
        }
        
        admin.setId(null);
        
        try {
            service.save(admin);
            model.addAttribute("msgSuccess", "Admin done successfully");
            model.addAttribute("admin", new Admin());
            return "formAdmin";
        }catch (Exception e) {
            model.addAttribute("msgErros", new ObjectError("admin", e.getMessage()));
            return "formAdmin";
        }
    }
    
    @GetMapping(path = "/admin/{id}")
    public String admin( @PathVariable("id") Long id, Model model){
        model.addAttribute("admin", service.findById(id).get());
        return "formAdmin";
    }
    
    @PostMapping(path = "/admin/{id}")
    public String update(@Valid @ModelAttribute Admin admin, @PathVariable("id") Long id, BindingResult result , Model model){
        
        if(result.hasErrors()){
            model.addAttribute("msgErros", result.getAllErrors());
            return "formAdmin";
        }
        
        admin.setId(id);
        
        try {
            service.update(admin);
            model.addAttribute("msgSuccess", "Current admin successfully completed");
            model.addAttribute("admin", admin);
            return "formAdmin";
        }catch (Exception e) {
            model.addAttribute("msgErros", new ObjectError("Admin", e.getMessage()));
            return "formAdmin";
        }
    }
    
    @GetMapping(path = "/{id}/delete")
    public String delete(@PathVariable("id") Long id) {
        service.delete(id);
        return "redirect:/admins";
    }
}
