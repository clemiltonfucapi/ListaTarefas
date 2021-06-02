package com.clemilton.listatarefas.model;


import java.io.Serializable;

public class Tarefa implements Serializable {
    private Long id;
    private String tarefa;
    private String data;
    private String hora;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public Tarefa(Long id, String tarefa, String data, String hora ) {
        this.id = id;
        this.tarefa = tarefa;
        this.data = data;
        this.hora = hora;
    }


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
