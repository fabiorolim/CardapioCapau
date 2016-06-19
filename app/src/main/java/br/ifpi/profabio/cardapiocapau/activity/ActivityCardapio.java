package br.ifpi.profabio.cardapiocapau.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import br.ifpi.profabio.cardapiocapau.R;

/**
 * Created by fabiorolim on 17/03/16.
 */
public class ActivityCardapio extends Activity {

    private TextView txtPrincial;
    private TextView txtSobremesa;
    private TextView txtInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cardapio);
        txtPrincial = (TextView) findViewById(R.id.txt_principal);
        txtSobremesa = (TextView) findViewById(R.id.txt_sobremesa);
        txtInfo = (TextView) findViewById(R.id.txt_info);

        Intent it = getIntent();

        txtPrincial.setText(it.getStringExtra("principal"));
        txtSobremesa.setText(it.getStringExtra("sobremesa"));
        txtInfo.setText(it.getStringExtra("info"));
    }
}
