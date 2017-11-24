package com.win.henrique.carteiradeclientes;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class ACTCadCliente extends AppCompatActivity {

    private EditText edtNome;
    private EditText edtProjeto;
    private EditText edtTelefone;
    private EditText edtEmail;
    private EditText edtDataI;
    private EditText edtDataF;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actcad_cliente);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        edtNome = (EditText)findViewById(R.id.edtNome);
        edtProjeto = (EditText)findViewById(R.id.edtProjeto);
        edtTelefone = (EditText)findViewById(R.id.edtTelefone);
        edtEmail = (EditText)findViewById(R.id.edtEmail);
        edtDataI = (EditText)findViewById(R.id.edtDataI);
        edtDataF = (EditText)findViewById(R.id.edtDataF);
    }

    private ClienteAdapter adapter;
    private void ValidaCampos() {

        String nome = edtNome.getText().toString();
        String projeto = edtProjeto.getText().toString();
        String telefone = edtTelefone.getText().toString();
        String email = edtEmail.getText().toString();
        String datai = edtDataI.getText().toString();
        String dataf = edtDataF.getText().toString();

        boolean res = false;

        if (res = isCampoVazio(nome)) {
            edtNome.requestFocus();
        } else
        if (res = isCampoVazio(projeto)) {
            edtProjeto.requestFocus();
        } else
        if (res = !isEmailVazio(email)) {
            edtEmail.requestFocus();
        } else
        if (res = isCampoVazio(telefone)) {
            edtTelefone.requestFocus();
        } else
        if (res = isCampoVazio(datai)) {
            edtDataI.requestFocus();
        } else
        if (res = isCampoVazio(dataf)) {
            edtDataF.requestFocus();
        }

        if(res) {
            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setTitle(R.string.title_aviso);
            dlg.setMessage(R.string.message_campos_brancos_invalidos);
            dlg.setNeutralButton(R.string.lbl_ok, null);
            dlg.show();
        }
        else{
            //salvando os dados


            ClienteDAO dao = new ClienteDAO(getBaseContext());
            boolean sucesso = dao.salvar(nome, telefone, email, projeto, datai, dataf);
            if(sucesso) {
                Cliente cliente = dao.retornarUltimo();
                adapter.adicionarCliente(cliente);
               // Toast.makeText(this, "Salvou", Toast.LENGTH_SHORT).show();
               // finish();
            }else{
                Toast.makeText(this, "Erro ao salvar, consulte os logs", Toast.LENGTH_SHORT).show();
            }
        }
    }


    private boolean isCampoVazio(String valor){

        boolean resultado = (TextUtils.isEmpty(valor) || valor.trim().isEmpty());
        return resultado;

    }

    private boolean isEmailVazio(String email){

        boolean resultado = (!isCampoVazio(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
        return resultado;

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_act_cad_cliente, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch (id){

            case R.id.action_ok:
                ValidaCampos();

                break;

            case R.id.action_cancelar:
                Toast.makeText(this, "Operação Cancelada!", Toast.LENGTH_SHORT).show();
                finish();
                break;


        }
        return super.onOptionsItemSelected(item);
    }

}

