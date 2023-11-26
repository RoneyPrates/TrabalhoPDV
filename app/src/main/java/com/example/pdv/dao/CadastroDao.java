package com.example.pdv.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.pdv.helper.SQLiteDataHelper;
import com.example.pdv.model.Cadastro;

import java.util.ArrayList;

public class CadastroDao {

    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase basededados;
    private String []colunas = {"login", "senha"};
    private String tabela = "cadastro";
    private Context context;

    private static CadastroDao instancia;

    public static CadastroDao getInstancia(Context context) {
        if (instancia == null){
            return instancia = new CadastroDao(context);
        }else{
            return instancia;
        }
    }
    public static void setInstancia(CadastroDao instancia) {
        CadastroDao.instancia = instancia;
    }
    private CadastroDao(Context context) {
        openHelper = new SQLiteDataHelper(this.context, "BANCOLOGIN", null, 1);

        basededados = openHelper.getWritableDatabase();

    }
    public long insert(Cadastro obj){
        try {
            ContentValues valores = new ContentValues();
            valores.put(colunas[0], obj.getLogin());
            valores.put(colunas[1], obj.getSenha());

            return basededados.insert(tabela, null, valores);

        }catch (SQLException ex){
            Log.e("PDV", "ERRO: CadastroDao.insert()"+ex.getMessage());
        }
        return 0;
    }
    public long update(Cadastro obj){
        try {
            ContentValues valores = new ContentValues();
            valores.put(colunas[1], obj.getSenha());

            String[]identificador = {String.valueOf(obj.getLogin())};

            return basededados.update(tabela, valores, colunas[0]+"= ?", identificador);

        }catch (SQLException ex){
            Log.e("PDV", "ERRO: CadastroDao.update()"+ex.getMessage());
        }
return 0;
    }

    public long delete(Cadastro obj){
        try{
            String[]identificador={String.valueOf(obj.getLogin())};

            return basededados.delete(tabela,colunas[0]+"= ?", identificador);

        }catch (SQLException ex){
            Log.e("PDV", "ERRO: CadastroDao.delete()"+ex.getMessage());
        }
        return 0;
    }

    public ArrayList<Cadastro> getAll(){
        ArrayList<Cadastro> lista = new ArrayList<>();

        try{
            Cursor cursor = basededados.query(tabela,colunas,null,null,null,null,colunas[0]+"desc");

            if (cursor.moveToFirst()){
                do{
                    Cadastro cadastro = new Cadastro();
                    cadastro.setLogin(cursor.getString(0));
                    cadastro.setSenha(cursor.getInt(1));

                    lista.add(cadastro);
                }while (cursor.moveToNext());
            }

        }catch (SQLException ex){
            Log.e("PDV", "ERRO: CadastroDao.getAll()"+ex.getMessage());
        }
        return lista;
    }
    public Cadastro getById(String id){
        try {
            String[]identificador = {String.valueOf(id)};
            Cursor cursor = basededados.query(tabela,colunas,colunas[0]+"= ?",identificador,null,null,null);

            if (cursor.moveToFirst()){
                Cadastro cadastro = new Cadastro();
                cadastro.setLogin(cursor.getString(0));
                cadastro.setSenha(cursor.getInt(1));

                return cadastro;
            }
        }catch (Exception ex){

        }
        return null;

    }

}
