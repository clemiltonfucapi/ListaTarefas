package com.clemilton.listatarefas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.clemilton.listatarefas.adapter.OnItemClickListener;
import com.clemilton.listatarefas.adapter.TarefaAdapter;
import com.clemilton.listatarefas.model.Tarefa;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Tarefa> listaTarefas = new ArrayList<>();
    private RecyclerView recyclerView;
    private FloatingActionButton fab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
        fab= findViewById(R.id.floatingActionButton);
        //recyclerView.setOnItemClickListener();
        preencherLista();


        TarefaAdapter tarefaAdapter = new TarefaAdapter(
                                            getApplicationContext(),
                                            listaTarefas
                                            );
        tarefaAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Tarefa tarefa = listaTarefas.get(position);
                Toast.makeText(getApplicationContext(),
                                tarefa.getTarefa() + position,
                                Toast.LENGTH_SHORT).show();
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(tarefaAdapter);
        recyclerView.addItemDecoration(
            new DividerItemDecoration(this, LinearLayout.VERTICAL)
        );

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext()
                                            ,AdicionarActivity.class);
                startActivity(intent);

            }
        });

    }

    public void preencherLista(){
        for(int i =0 ; i < 30 ; i++) {
            listaTarefas.add(new Tarefa("corrida"));
            listaTarefas.add(new Tarefa("Supermercado"));
            listaTarefas.add(new Tarefa("Fucapi"));

        }


    }
}
