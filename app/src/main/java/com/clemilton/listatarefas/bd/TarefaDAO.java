package com.clemilton.listatarefas.bd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.clemilton.listatarefas.model.Tarefa;

import java.util.ArrayList;

public class TarefaDAO {
    private SQLiteDatabase escreve;
    private SQLiteDatabase le;

    public TarefaDAO(Context context){
        DbHelper db = new DbHelper(context);
        escreve = db.getWritableDatabase();
        le = db.getReadableDatabase();
    }

    public boolean salvar(Tarefa tarefa){
        ContentValues cv = new ContentValues();
        cv.put("nome",tarefa.getTarefa());
        try {
            escreve.insert(DbHelper.TABELA_TAREFAS, null, cv);
        }catch(Exception e ){
            Log.e("ERRO BD", "Erro ao salvar a tarefa "+ e.getMessage());
            return false;
        }
        return true;
    }

    public ArrayList<Tarefa> listarTarefas(){
        //Lista de tarefas
        ArrayList<Tarefa> tarefas = new ArrayList<>();
        //SELECT query
        String sql = "SELECT * FROM " + DbHelper.TABELA_TAREFAS + ";";
        //cursor
        Cursor c = le.rawQuery(sql,null);

        /* percorrer cursor*/
        while(c.moveToNext()){
            //recuperando valores
            Long id = c.getLong(c.getColumnIndex("id"));
            String nomeTarefa = c.getString(c.getColumnIndex("nome"));
            //adicionando na lista de tarefas
            tarefas.add(new Tarefa(id,nomeTarefa));
        }
        return tarefas;
    }
}
