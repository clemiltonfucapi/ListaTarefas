package com.clemilton.listatarefas.adapter;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.clemilton.listatarefas.R;
import com.clemilton.listatarefas.model.Tarefa;

import java.util.ArrayList;

public class TarefaAdapter extends RecyclerView.Adapter<TarefaAdapter.TarefaVH> {
    private Context context;
    private ArrayList<Tarefa> listaTarefas;
    private OnItemClickListener listener;

    public TarefaAdapter(Context context, ArrayList<Tarefa> lista){
        this.context = context;
        this.listaTarefas = lista;
    }

    public void setListaTarefas(ArrayList<Tarefa> listaTarefas){
        this.listaTarefas = listaTarefas;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public TarefaVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //recuperar um inflater: é um objeto que converte XML em View
        LayoutInflater inflater = LayoutInflater.from(context);

        //converter um XML em um view
        View itemView = inflater.inflate(R.layout.item_tarefa,parent,false);

        //retornando um objeto ViewHolder
        return new TarefaVH(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TarefaVH holder, int position) {
        //recuperando a tarefa
        Tarefa tarefa = listaTarefas.get(position);
        //holder representa um elemento da Recycler

        holder.textTarefa.setText(tarefa.getTarefa());

    }

    @Override
    public int getItemCount() {
        return listaTarefas.size();
    }



    /* inner class: ViewHolder ->
    *   - Representa um elemento da RecyclerView */
    public class TarefaVH extends RecyclerView.ViewHolder {
        TextView textTarefa;

        public TarefaVH(@NonNull View itemView) {
            /* itemView: representa item_tarefa.xml */
            super(itemView);
            textTarefa = itemView.findViewById(R.id.nomeTarefa);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(listener!=null){
                        int position = getBindingAdapterPosition();
                        //executa o método implementado na Activity
                        listener.onItemClick(position);
                    }
                }
            });



        }
    }
}


