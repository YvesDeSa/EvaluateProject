package com.evaluate.controller.views;

import com.evaluate.model.Evaluation;
import com.evaluate.service.EvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/evaluations")
public class EvaluationViewController {
    @Autowired
    private EvaluationService service;
    
    @GetMapping
    public String getAll(Model model){
        model.addAttribute("evaluations", service.findAll());
        return "evaluations";
    }
    
    @GetMapping(path = "/evaluation")
    public String addOne(Model model){
        model.addAttribute("evaluation", new Evaluation());
        return "formEvaluation";
    }
}
