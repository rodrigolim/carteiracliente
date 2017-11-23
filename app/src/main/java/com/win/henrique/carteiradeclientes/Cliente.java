package com.win.henrique.carteiradeclientes;

import java.io.Serializable;

public class Cliente implements Serializable {

    private int id;
    private String nome;
    private String telefone;
    private String email;
    private String descricao;
    private String dtini;
    private String dtfim;

    public Cliente(int id, String nome, String telefone, String email, String descricao, String dtini, String dtfim){
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.descricao = descricao;
        this.dtini = dtini;
        this.dtfim = dtfim;
    }

    public int getId(){ return this.id; }
    public String getNome(){ return this.nome; }
    public String getTelefone(){ return this.telefone; }
    public String getEmail(){ return this.email; }
    public String getDescricao(){ return this.descricao; }
    public String getDtini(){ return this.dtini; }
    public String getDtfim(){ return this.dtfim; }

    @Override
    public boolean equals(Object o){
        return this.id == ((Cliente)o).id;
    }

    @Override
    public int hashCode(){
        return this.id;
    }
}
