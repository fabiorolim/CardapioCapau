package br.ifpi.profabio.cardapiocapau.web;

import android.content.Context;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import br.ifpi.profabio.cardapiocapau.data.Cardapio;


/**
 * Created by fabiorolim on 21/11/16.
 */
public class WebFirebase {

    private static final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    private static final DatabaseReference cardapioReference = databaseReference.child("cardapios");
    //private static List<Cardapio> foundItems;

    public static final List<Cardapio> findAllItems(Context context) throws Exception{
        final List<Cardapio> foundItems = new ArrayList<Cardapio>(5);
        //Cardapio cardapio = new Cardapio(001, "2016-11-11","feijoada", "sorvete", "nada", "almoço");
        //foundItems.add(cardapio);
        cardapioReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot child : dataSnapshot.getChildren()){
                    Cardapio cardapio = child.getValue(Cardapio.class);
                    foundItems.add(cardapio);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return foundItems;
    }
}

