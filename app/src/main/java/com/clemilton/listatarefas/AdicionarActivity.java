package com.clemilton.listatarefas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.clemilton.listatarefas.bd.TarefaDAO;
import com.clemilton.listatarefas.model.Tarefa;
import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AdicionarActivity extends AppCompatActivity {

    private TextInputEditText editTarefa;
    private TarefaDAO tarefaDAO;
    private Tarefa tarefaAtual;
    /* Atributos para data */
    private EditText editData;
    private Calendar calendar;

    DatePickerDialog.OnDateSetListener listenerDate;

    /*Atributo para hora */
    private EditText editHora;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar);
        editTarefa = findViewById(R.id.textTarefa);
        editData = findViewById(R.id.editData);
        editHora = findViewById(R.id.editHora);
        tarefaDAO = new TarefaDAO(getApplicationContext());
        //recuperar tarefa
        tarefaAtual = (Tarefa) getIntent().getSerializableExtra("tarefa");

        //configurar tarefa na caixa de texto
        if(tarefaAtual!=null){
            editTarefa.setText(tarefaAtual.getTarefa());
        }

        calendar = Calendar.getInstance();

        /*listener para */
        listenerDate = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR,year);
                calendar.set(Calendar.MONTH,month);
                calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);
                updateData();

            }
        };


        new TimePickerDialog.OnTimeSetListener(){

            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

            }
        };

        editData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //handle your situation here
                new DatePickerDialog(AdicionarActivity.this,
                                        listenerDate,
                                        //setando data inicial
                                        calendar.get(Calendar.YEAR),
                                        calendar.get(Calendar.MONTH),
                                        calendar.get(Calendar.DAY_OF_MONTH)
                ).show();
            }
        });

        editHora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new TimePickerDialog(AdicionarActivity.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                calendar.set(Calendar.HOUR_OF_DAY,hourOfDay );
                                calendar.set(Calendar.MINUTE,minute);
                                updateHora();
                            }
                        },
                        0,
                        0,
                        true
                ).show();
            }
        });




    }

    private void updateData(){
        String myFormat = "dd/MM/yyyy";
        /* Conversor de datas */
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat);
        editData.setText(sdf.format(calendar.getTime()));
    }

    private void updateHora(){
        String myFormat = "HH:mm";
        /* Conversor de datas */
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat);
        editHora.setText(sdf.format(calendar.getTime()));
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
                if(tarefaAtual!=null){
                    atualizarTarefa();
                }else {
                    salvarTarefa();
                }
        }
        return super.onOptionsItemSelected(item);
    }

    public void atualizarTarefa(){
        String nomeTarefa = editTarefa.getText().toString();
        if(nomeTarefa.isEmpty()){
            Toast.makeText(this,"Digite o nome da tarefa! ", Toast.LENGTH_SHORT).show();
            return;
        }
        //Criando uma tarefa atualizada
        Tarefa tarefa = new Tarefa();
        tarefa.setId(tarefaAtual.getId());
        tarefa.setTarefa(nomeTarefa);

        //atualizar no b.d
        if(tarefaDAO.atualizar(tarefa)){
            Toast.makeText(getApplicationContext(),"Sucesso ao atualizar a tarefa!",Toast.LENGTH_SHORT).show();
            finish();
        }else{
            Toast.makeText(getApplicationContext(),"Erro ao atualizar!",Toast.LENGTH_SHORT).show();
        }


    }


    public void salvarTarefa(){
        String nomeTarefa = editTarefa.getText().toString();
        String hora = editHora.getText().toString();
        String data = editData.getText().toString();
        if(nomeTarefa.isEmpty() || hora.isEmpty() || data.isEmpty()){
            Toast.makeText(this,"Preencha todos os campos!" , Toast.LENGTH_SHORT).show();
            return;
        }

        Tarefa t = new Tarefa();
        t.setTarefa(nomeTarefa);
        t.setHora(hora);
        t.setData(data);
        if(tarefaDAO.salvar(t)){
            Toast.makeText(this,"Sucesso ao salvar tarefa!", Toast.LENGTH_SHORT).show();
            finish();
        }else{
            Toast.makeText(this,"Erro ao salvar!", Toast.LENGTH_SHORT).show();
        }
    }


}
