package br.ifpi.profabio.cardapiocapau.aplication;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import br.ifpi.profabio.cardapiocapau.data.Cardapio;

/**
 * Created by fabiorolim on 22/11/16.
 */

public class ConfigFirebase {
    private static DatabaseReference firebase;
    private static DatabaseReference cardapio_references;
    public static DatabaseReference getFirebase(){
        if(firebase==null){
            firebase = FirebaseDatabase.getInstance().getReference();
        }
        return firebase;
    }
    public static DatabaseReference cardapioReferences(){
        cardapio_references = ConfigFirebase.getFirebase().child("cardapios");
        return cardapio_references;
    }
}
