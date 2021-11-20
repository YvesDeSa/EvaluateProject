package com.evaluate.controller.views;

import com.evaluate.model.Comment;
import com.evaluate.service.ClientService;
import com.evaluate.service.CommentService;
import com.evaluate.service.EvaluationService;
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
@RequestMapping(path = "/comments")
public class CommentViewController {
    @Autowired
    private CommentService service;
    @Autowired
    private EvaluationService evaluation;
    @Autowired
    private ClientService client;
    
    @GetMapping
    public String getAll(Model model){
        model.addAttribute("comments", service.findAll());
        model.addAttribute("client", client.findAll());
        
        return "comments";
    }
    
    @GetMapping(path = "/comment")
    public String evaluations(Model model){
        model.addAttribute("comment", new Comment());
        model.addAttribute("client", client.findAll());
        model.addAttribute("evaluation", evaluation.findAll());
        return "formComment";
    }
    
    @PostMapping(path = "/comment")
    public String save(@Valid @ModelAttribute Comment comment, BindingResult result , Model model){
        model.addAttribute("client", client.findAll());
        
        if(result.hasErrors()){
            model.addAttribute("msgErros", result.getAllErrors());
            return "formComment";
        }
        
        comment.setId(null);
        
        try {
            service.save(comment);
            model.addAttribute("msgSuccess", "Evaluation done successfully");
            model.addAttribute("comment", new Comment());
            return "formComment";
        }catch (Exception e) {
            model.addAttribute("msgErros", new ObjectError("Evaluation", e.getMessage()));
            return "formComment";
        }
    }
    
    @GetMapping(path = "/comment/{id}")
    public String evaluation( @PathVariable("id") Long id, Model model){
        model.addAttribute("comment", service.findById(id));
        model.addAttribute("client", client.findAll());
        model.addAttribute("evaluation", evaluation.findAll());
        return "formComment";
    }
    
    @PostMapping(path = "/comment/{id}")
    public String update(@Valid @ModelAttribute Comment comment, @PathVariable("id") Long id, BindingResult result , Model model){
        model.addAttribute("client", client.findAll());
        model.addAttribute("evaluation", evaluation.findAll());
        
        if(result.hasErrors()){
            model.addAttribute("msgErros", result.getAllErrors());
            return "formComment";
        }
        
        comment.setId(id);
        
        try {
            service.update(comment);
            model.addAttribute("msgSuccess", "Current evaluation successfully completed");
            model.addAttribute("comment", comment);
            return "formComment";
        }catch (Exception e) {
            model.addAttribute("msgErros", new ObjectError("Comment", e.getMessage()));
            return "formComment";
        }
    }
    
    @GetMapping(path = "/{id}/delete")
    public String deletar(@PathVariable("id") Long id) {
        service.delete(id);
        return "redirect:/comments";
    }
}
