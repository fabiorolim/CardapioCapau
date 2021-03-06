package br.ifpi.profabio.cardapiocapau.web;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import br.ifpi.profabio.cardapiocapau.aplication.ConfigFirebase;
import br.ifpi.profabio.cardapiocapau.data.Cardapio;


/**
 * Created by fabiorolim on 21/11/16.
 */
public class WebFirebase {

    public static final List<Cardapio> findAllItems(final Context context) throws Exception{
        final List<Cardapio> foundItems = new ArrayList<Cardapio>(5);
        //Cardapio cardapio = new Cardapio("2016-11-11","feijoada", "sorvete", "nada", "almoço");
        //foundItems.add(cardapio);
        ConfigFirebase.cardapioReferences().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot child : dataSnapshot.getChildren()){
                    try{
                        Cardapio cardapio = child.getValue(Cardapio.class);
                        foundItems.add(cardapio);
                    }catch (DatabaseException d){
                        Log.i("Database:", d.getMessage());
                    }
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(context, "Erro ao tentar consultar dados!", Toast.LENGTH_LONG ).show();
            }
        });

        return foundItems;
    }
}

