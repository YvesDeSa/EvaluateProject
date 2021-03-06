package com.evaluate.controller.views;

import com.evaluate.model.Evaluation;
import com.evaluate.service.ClientService;
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
@RequestMapping(path = "/evaluations")
public class EvaluationViewController {
    @Autowired
    private EvaluationService service;
    @Autowired
    private ClientService client;
    
    @GetMapping
    public String getAll(Model model){
        model.addAttribute("evaluations", service.findAll());
        model.addAttribute("client", client.findAll());
        return "evaluations";
    }
    
    @GetMapping(path = "/evaluation")
    public String evaluations(Model model){
        model.addAttribute("evaluation", new Evaluation());
        model.addAttribute("client", client.findAll());
        return "formEvaluation";
    }
    
    @PostMapping(path = "/evaluation")
    public String save(@Valid @ModelAttribute Evaluation evaluation, BindingResult result , Model model){
        model.addAttribute("client", client.findAll());
        
        if(result.hasErrors()){
            model.addAttribute("msgErros", result.getAllErrors());
            return "formEvaluation";
        }
        
        evaluation.setId(null);
        
        try {
            service.save(evaluation);
            model.addAttribute("msgSuccess", "Evaluation done successfully");
            model.addAttribute("evaluation", new Evaluation());
            return "formEvaluation";
        }catch (Exception e) {
            model.addAttribute("msgErros", new ObjectError("Evaluation", e.getMessage()));
            return "formEvaluation";
        }
    }
    
    @GetMapping(path = "/evaluation/{id}")
    public String evaluation( @PathVariable("id") Long id, Model model){
        model.addAttribute("evaluation", service.findById(id).get());
        model.addAttribute("client", client.findAll());
        return "formEvaluation";
    }
    
    @PostMapping(path = "/evaluation/{id}")
    public String update(@Valid @ModelAttribute Evaluation evaluation, @PathVariable("id") Long id, BindingResult result , Model model){
        model.addAttribute("client", client.findAll());
        
        if(result.hasErrors()){
            model.addAttribute("msgErros", result.getAllErrors());
            return "formEvaluation";
        }
        
        evaluation.setId(id);
        
        try {
            service.update(evaluation);
            model.addAttribute("msgSuccess", "Current evaluation successfully completed");
            model.addAttribute("evaluation", evaluation);
            return "formEvaluation";
        }catch (Exception e) {
            model.addAttribute("msgErros", new ObjectError("Evaluation", e.getMessage()));
            return "formEvaluation";
        }
    }
    
    @GetMapping(path = "/{id}/delete")
    public String deletar(@PathVariable("id") Long id) {
        service.delete(id);
        return "redirect:/evaluations";
    }
}
