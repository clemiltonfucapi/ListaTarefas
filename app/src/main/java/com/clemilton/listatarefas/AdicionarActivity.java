package com.clemilton.listatarefas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import android.widget.Toolbar;

import com.clemilton.listatarefas.bd.TarefaDAO;
import com.clemilton.listatarefas.model.Tarefa;
import com.google.android.material.textfield.TextInputEditText;

public class AdicionarActivity extends AppCompatActivity {

    private TextInputEditText editTarefa;
    private TarefaDAO tarefaDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar);
        editTarefa = findViewById(R.id.textTarefa);
        tarefaDAO = new TarefaDAO(getApplicationContext());
    }

    /* Criação de menu */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_adicionar,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.itemSalvar:
                //Toast.makeText(this,"Salvar",Toast.LENGTH_SHORT).show();
                salvarTarefa();
        }
        return super.onOptionsItemSelected(item);
    }

    public void salvarTarefa(){
        String nomeTarefa = editTarefa.getText().toString();
        if(nomeTarefa.isEmpty()){
            Toast.makeText(this,"Digite o nome da tarefa! ", Toast.LENGTH_SHORT).show();
            return;
        }

        if(tarefaDAO.salvar(new Tarefa(nomeTarefa))){
            Toast.makeText(this,"Sucesso ao salvar tarefa!", Toast.LENGTH_SHORT).show();
            finish();
        }else{
            Toast.makeText(this,"Erro ao salvar!", Toast.LENGTH_SHORT).show();
        }

    }
}
