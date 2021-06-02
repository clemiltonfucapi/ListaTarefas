package com.clemilton.listatarefas;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.clemilton.listatarefas.adapter.OnItemClickListener;
import com.clemilton.listatarefas.adapter.TarefaAdapter;
import com.clemilton.listatarefas.bd.DbHelper;
import com.clemilton.listatarefas.bd.TarefaDAO;
import com.clemilton.listatarefas.model.Tarefa;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Tarefa> listaTarefas = new ArrayList<>();
    private RecyclerView recyclerView;
    private FloatingActionButton fab;
    private TarefaAdapter tarefaAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
        fab= findViewById(R.id.floatingActionButton);

        preencherLista();


        tarefaAdapter = new TarefaAdapter(
                                            getApplicationContext(),
                                            listaTarefas
                                            );
        tarefaAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Tarefa tarefa = listaTarefas.get(position);

                //Enviar tarefa para tela Adicionar tarefa
                Intent intent = new Intent(getApplicationContext(),AdicionarActivity.class);
                intent.putExtra("tarefa",tarefa);
                startActivity(intent);
            }

            @Override
            public void onLongClick(int position) {
                final Tarefa tarefa = listaTarefas.get(position);
                //Criar um AlertDialog
                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                // Configurar mensagens
                dialog.setTitle("Confirmar exclusão");
                dialog.setMessage("Deseja excluir a tarefa: "+tarefa.getTarefa() + "?");

                //listener p/ opção Sim
                dialog.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        TarefaDAO tarefaDAO = new TarefaDAO(getApplicationContext());
                        if(tarefaDAO.deletar(tarefa)){
                            preencherLista();
                            tarefaAdapter.setListaTarefas(listaTarefas);
                            tarefaAdapter.notifyDataSetChanged();
                            Toast.makeText(getApplicationContext(),"Sucesso ao exlcuir tarefa",Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(getApplicationContext(),"Erro ao excluir tarefa!",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                dialog.setNegativeButton("Não",null);
                dialog.create().show();
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
                Intent intent = new Intent(getApplicationContext(),AdicionarActivity.class);
                startActivity(intent);

            }
        });

    }

    public void preencherLista(){
        TarefaDAO tarefaDAO = new TarefaDAO(getApplicationContext());

        listaTarefas = tarefaDAO.listarTarefas();


    }

    @Override
    protected void onStart() {
        super.onStart();

        TarefaDAO tarefaDAO = new TarefaDAO(getApplicationContext());
        listaTarefas = tarefaDAO.listarTarefas();
        tarefaAdapter.setListaTarefas(listaTarefas);
        tarefaAdapter.notifyDataSetChanged();
    }
}
