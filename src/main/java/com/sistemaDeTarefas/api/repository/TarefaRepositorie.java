package com.sistemaDeTarefas.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sistemaDeTarefas.api.model.Tarefa;

public interface TarefaRepositorie extends JpaRepository<Tarefa, Long> {
    
}
