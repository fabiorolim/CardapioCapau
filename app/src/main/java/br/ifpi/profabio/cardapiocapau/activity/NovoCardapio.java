package br.ifpi.profabio.cardapiocapau.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import br.ifpi.profabio.cardapiocapau.R;
import br.ifpi.profabio.cardapiocapau.aplication.ConfigFirebase;
import br.ifpi.profabio.cardapiocapau.data.Cardapio;
import br.ifpi.profabio.cardapiocapau.web.WebFirebase;

/**
 * Created by fabiorolim on 22/11/16.
 */

public class NovoCardapio extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo_cardapio);
    }

    public void cadastrar(View view){
        //DatabaseReference firebaseDatabase = FirebaseDatabase.getInstance().getReference();
        //DatabaseReference cardapioReference = firebaseDatabase.child("cardapios").child("001");
        EditText principal = (EditText) findViewById(R.id.edt_prato_principal);
        EditText sobremesa = (EditText) findViewById(R.id.edt_sobremesa);
        EditText info = (EditText) findViewById(R.id.edt_info);
        EditText data = (EditText) findViewById(R.id.edt_data);
        EditText tipo = (EditText) findViewById(R.id.edt_tipo);
        Cardapio cardapio = new Cardapio(data.getText().toString(), principal.getText().toString(),
                sobremesa.getText().toString(), info.getText().toString(), tipo.getText().toString());
        try{
            ConfigFirebase.pushCardapioReferences().setValue(cardapio);
            String key = ConfigFirebase.pushCardapioReferences().getKey();
            Toast.makeText(this, "Novo cardápio postado "+key, Toast.LENGTH_LONG).show();
        }catch (Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

}
