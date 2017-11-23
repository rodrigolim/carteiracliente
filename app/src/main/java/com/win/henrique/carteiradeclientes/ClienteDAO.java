package com.win.henrique.carteiradeclientes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    private DbGateway gw;
    private DbHelper dbhelper;

    public ClienteDAO(Context ctx){
        gw = DbGateway.getInstance(ctx);
    }

    public boolean salvar(String nome, String telefone, String email, String descricao, String dtini, String dtfim){
        ContentValues valores = new ContentValues();
        valores.put(dbhelper.NOME, nome);
        valores.put(dbhelper.TELEFONE, telefone);
        valores.put(dbhelper.EMAIL, email);
        valores.put(dbhelper.DESCRICAO, descricao);
        valores.put(dbhelper.DT_INICIO, dtini);
        valores.put(dbhelper.DT_TERMINO, dtfim);
        return gw.getDatabase().insert(dbhelper.TAB_CLIENTE, null, valores) > 0;
    }

    public List<Cliente> retornarTodos(){
        List<Cliente> clientes = new ArrayList<>();
        Cursor cursor = gw.getDatabase().rawQuery("SELECT * FROM Clientes", null);
        while(cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndex(dbhelper.ID));
            String nome = cursor.getString(cursor.getColumnIndex(dbhelper.NOME));
            String telefone = cursor.getString(cursor.getColumnIndex(dbhelper.TELEFONE));
            String email = cursor.getString(cursor.getColumnIndex(dbhelper.EMAIL));
            String descricao = cursor.getString(cursor.getColumnIndex(dbhelper.DESCRICAO));
            String dtini = cursor.getString(cursor.getColumnIndex(dbhelper.DT_INICIO));
            String dtfim = cursor.getString(cursor.getColumnIndex(dbhelper.DT_TERMINO));

            clientes.add(new Cliente(id, nome, telefone, email, descricao, dtini, dtfim));
        }
        cursor.close();
        return clientes;
    }

    public Cliente retornarUltimo(){
        Cursor cursor = gw.getDatabase().rawQuery("SELECT * FROM "+dbhelper.TAB_CLIENTE+" ORDER BY "+dbhelper.ID+" DESC", null);
        if(cursor.moveToFirst()){
            int id = cursor.getInt(cursor.getColumnIndex(dbhelper.ID));
            String nome = cursor.getString(cursor.getColumnIndex(dbhelper.NOME));
            String telefone = cursor.getString(cursor.getColumnIndex(dbhelper.TELEFONE));
            String email = cursor.getString(cursor.getColumnIndex(dbhelper.EMAIL));
            String descricao = cursor.getString(cursor.getColumnIndex(dbhelper.DESCRICAO));
            String dtini = cursor.getString(cursor.getColumnIndex(dbhelper.DT_INICIO));
            String dtfim = cursor.getString(cursor.getColumnIndex(dbhelper.DT_TERMINO));
            cursor.close();
            return new Cliente(id, nome, telefone, email, descricao, dtini, dtfim);
        }

        return null;
    }

    public boolean excluir(int id){
        return gw.getDatabase().delete(dbhelper.TAB_CLIENTE, dbhelper.ID+"=?", new String[]{ id + "" }) > 0;
    }
}
