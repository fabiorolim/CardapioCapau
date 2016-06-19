package br.ifpi.profabio.cardapiocapau.activity;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONException;

import br.ifpi.profabio.cardapiocapau.R;
import br.ifpi.profabio.cardapiocapau.adapter.CardapioAdapter;
import br.ifpi.profabio.cardapiocapau.data.Cardapio;

/**
 * Created by fabiorolim on 17/03/16.
 */
public class ListCardapios extends ListActivity {


    private CardapioAdapter adapter;
    private Cardapio cardapio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //adapter = new CardapioAdapter(this, Cardapio.gerarCardapios());
        //setListAdapter(adapter);
        new InvokeWebserviceTask().execute(this);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Cardapio cardapio = (Cardapio) adapter.getItem(position);
        Intent intent = new Intent(this, ActivityCardapio.class);
        intent.putExtra("principal", cardapio.getPrincipal());
        intent.putExtra("sobremesa", cardapio.getSobremesa());
        intent.putExtra("info", cardapio.getInfo());
        //Log.d("prin", cardapio.getPrincipal());
        startActivity(intent);
    }

    /*@Override
    protected void onTitleChanged(CharSequence title, int color) {
        super.onTitleChanged("Cardapio - CAPAU", color);
    }*/

    //menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_sobre){
            Intent intent = new Intent(getApplicationContext(), SobreActivity.class);
            startActivity(intent);
            return true;
        }
        if (item.getItemId() == R.id.menu_sair){
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private class InvokeWebserviceTask extends AsyncTask<Context, Cardapio, Exception>{

        private ProgressDialog dialog =
                new ProgressDialog(ListCardapios.this);

        @Override
        protected void onPreExecute() {
            // TODO i18n
            dialog.setMessage("Aguarde...");
            dialog.show();
        }

        @Override
        protected Exception doInBackground(Context... params){
            Context context = params[0];
            Exception e = null;
            try{
                adapter = new CardapioAdapter(context, Cardapio.gerarCardapios(context));
            }catch (JSONException j){
                e = j;
            }catch (NullPointerException n){
                e = n;
            }finally {
                return e;
            }
        }

        @Override
        protected void onPostExecute(Exception e) {
            super.onPostExecute(e);
            if (e instanceof JSONException) {
                Toast.makeText(getApplicationContext(), "Erro ao tentar converter JSON!", Toast.LENGTH_LONG).show();
                finish();
            }else if (e instanceof NullPointerException){
                    Toast.makeText(getApplicationContext(), "Não há cardapios cadastrados!", Toast.LENGTH_LONG).show();
                    finish();
            }else if (e == null) {
                setListAdapter(adapter);
                if (dialog.isShowing()) {
                    dialog.dismiss();
                }
            }
        }

    }

}
