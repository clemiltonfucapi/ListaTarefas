package com.clemilton.listatarefas.model;


import java.io.Serializable;

public class Tarefa implements Serializable {
    private Long id;
    private String tarefa;
    public Tarefa(){

    }
    public Tarefa(String tarefa) {
        this.tarefa = tarefa;
    }
    public Tarefa(Long id, String tarefa){
        this.id = id;
        this.tarefa = tarefa;
    }

    public String getTarefa() {
        return tarefa;
    }

    public void setTarefa(String tarefa) {
        this.tarefa = tarefa;
    }

    public Long getId(){
        return id;
    }
    public void setId(Long id){
        this.id = id;
    }
}
