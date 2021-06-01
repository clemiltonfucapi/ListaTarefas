package com.clemilton.listatarefas.model;


public class Tarefa {
    private String tarefa;
    public Tarefa(String tarefa){
        this.tarefa =tarefa;
    }
    public String getTarefa(){
        return tarefa;
    }

    public void setTarefa(String tarefa){
        this.tarefa = tarefa;
    }
}
