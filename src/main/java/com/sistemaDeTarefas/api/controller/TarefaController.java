package com.sistemaDeTarefas.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.sistemaDeTarefas.api.model.Tarefa;
import com.sistemaDeTarefas.api.repository.TarefaRepositorie;



@Controller
public class TarefaController {
    
    @Autowired
    private TarefaRepositorie tarefaRepositorie;

    public TarefaController(TarefaRepositorie tarefaRepositorie) {
        this.tarefaRepositorie = tarefaRepositorie;
    }
    
    

    @GetMapping("/index")
    public String index(Model model) {
        model.addAttribute("tarefa", new Tarefa());
        model.addAttribute("tarefas", tarefaRepositorie.findAll());
        return "index";
    }
    
    @GetMapping("/novaTarefa")
    public String novaTarefa(Model model) {
        model.addAttribute("tarefa", new Tarefa());
        return "novaTarefa";
    }




    @PostMapping("/novaTarefa")
    public String salvarTarefa(@ModelAttribute Tarefa tarefa) {
        tarefaRepositorie.save(tarefa);
        return "redirect:/index";
    }
    
    @GetMapping("/editar/{id}")
    public String editarTarefa(@PathVariable Long id, Model model) {
        Tarefa tarefa = tarefaRepositorie.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("ID inválido: " + id));

        model.addAttribute("tarefa", tarefa);
        return "editarTarefa";
    }

    @PostMapping("/editar")
    public String atualizarTarefa(@ModelAttribute Tarefa tarefaAtualizada) {
        Tarefa tarefaExistente = tarefaRepositorie.findById(tarefaAtualizada.getId())
            .orElseThrow(() -> new IllegalArgumentException("ID inválido: " + tarefaAtualizada.getId()));

        tarefaExistente.setTitulo(tarefaAtualizada.getTitulo());
        tarefaExistente.setDescricao(tarefaAtualizada.getDescricao());
        tarefaExistente.setStatus(tarefaAtualizada.isStatus());

        tarefaRepositorie.save(tarefaExistente);
        return "redirect:/index";
    }

    @GetMapping("/deletar/{id}")
    public String deleteTarefa(@PathVariable Long id) {
        Tarefa tarefa = tarefaRepositorie.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("ID inválido: " + id));

        tarefaRepositorie.delete(tarefa);    
        return "redirect:/index";
    }




    
    

    

    

    

}
