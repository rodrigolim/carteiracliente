package com.win.henrique.carteiradeclientes;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "carteiraclientes.db";
    private static final int DATABASE_VERSION = 1;

    protected static final String TAB_CLIENTE = "Clientes";
    protected static final String ID = "_id";
    protected static final String NOME = "nome";
    protected static final String TELEFONE = "telefone";
    protected static final String EMAIL = "email";
    protected static final String DESCRICAO = "descricao";
    protected static final String DT_INICIO = "data_inicio";
    protected static final String DT_TERMINO = "data_termino";


    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table "+TAB_CLIENTE+"("
                + ID + " integer primary key autoincrement, "
                + NOME + " varchar(50), "
                + TELEFONE + " varchar(20), "
                + EMAIL + " varchar(50), "
                + DESCRICAO + " varchar(100), "
                + DT_INICIO + " date, "
                + DT_TERMINO + " date "
                + ")";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists "+TAB_CLIENTE);
        onCreate(db);
    }
}
