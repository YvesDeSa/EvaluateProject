package com.evaluate.controller.views;

import com.evaluate.model.Client;
import com.evaluate.repository.PermitRepository;
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
@RequestMapping(path = "/clients")
public class ClientViewController {

    @Autowired
    private ClientService service;
    @Autowired
    private EvaluationService evaluation;
    @Autowired
    private CommentService comment;
    @Autowired
    private PermitRepository permit;
    
    
    @GetMapping
    public String getAll(Model model){
        model.addAttribute("clients", service.findAll());
        model.addAttribute("evaluation", evaluation.findAll());
        model.addAttribute("comment", comment.findAll());
        model.addAttribute("permits", permit.findAll());
        
        return "clients";
    }
    
    @GetMapping(path = "/client")
    public String evaluations(Model model){
        model.addAttribute("client", new Client());
        model.addAttribute("evaluation", evaluation.findAll());
        model.addAttribute("permits", permit.findAll());
        
        return "formClient";
    }
    
    @PostMapping(path = "/client")
    public String save(@Valid @ModelAttribute Client client, BindingResult result , Model model){
        
        model.addAttribute("permits", permit.findAll());
        
        if(result.hasErrors()){
            model.addAttribute("msgErros", result.getAllErrors());
            return "formClient";
        }
        
        client.setId(null);
        
        try {
            service.save(client);
            model.addAttribute("msgSuccess", "Client done successfully");
            model.addAttribute("client", new Client());
            return "formClient";
        }catch (Exception e) {
            model.addAttribute("msgErros", new ObjectError("Client", e.getMessage()));
            return "formClient";
        }
    }
    
    @GetMapping(path = "/client/{id}")
    public String evaluation( @PathVariable("id") Long id, Model model){
        model.addAttribute("client", service.findById(id).get());
        model.addAttribute("evaluation", evaluation.findAll());
        model.addAttribute("comment", comment.findAll());
        model.addAttribute("permits", permit.findAll());
        
        return "formClient";
    }
    
    @PostMapping(path = "/client/{id}")
    public String update(@Valid @ModelAttribute Client client, @PathVariable("id") Long id, BindingResult result , Model model){
        
        model.addAttribute("permits", permit.findAll());
        
        if(result.hasErrors()){
            model.addAttribute("msgErros", result.getAllErrors());
            return "formClient";
        }
        
        client.setId(id);
        
        try {
            service.update(client);
            model.addAttribute("msgSuccess", "Current client successfully completed");
            model.addAttribute("client", client);
            return "formClient";
        }catch (Exception e) {
            model.addAttribute("msgErros", new ObjectError("Client", e.getMessage()));
            return "formClients";
        }
    }
    
    @GetMapping(path = "/{id}/delete")
    public String deletar(@PathVariable("id") Long id) {

        service.delete(id);
        return "redirect:/clients";
    }
}

