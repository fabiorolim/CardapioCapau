package br.ifpi.profabio.cardapiocapau.data;

import android.content.Context;

import java.util.Iterator;
import java.util.List;

import br.ifpi.profabio.cardapiocapau.web.WebFirebase;
//import br.ifpi.profabio.cardapiocapau.web.WebService;

/**
 * Created by fabiorolim on 17/03/16.
 */
public class Cardapio{
    //private int cod;
    private String data;
    private String principal;
    private String sobremesa;
    private String info;
    private String tipo;

    private static List<Cardapio> cardapios;

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Cardapio(String data){
        //this.cod = cod;
        this.data = data;
    }

    public Cardapio(){

    }

    public Cardapio(String data, String principal, String sobremesa, String info, String tipo) {
        //this.cod = cod;
        this.data = data;
        this.principal = principal;
        this.sobremesa = sobremesa;
        this.info = info;
        this.tipo = tipo;
    }



    //public int getCod(){
    //    return this.cod;
    //}

   // public void setCod(int cod){
   //     this.cod = cod;
   // }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getPrincipal() {
        return principal;
    }

    public void setPrincipal(String principal) {
        this.principal = principal;
    }

    public String getSobremesa() {
        return sobremesa;
    }

    public void setSobremesa(String sobremesa) {
        this.sobremesa = sobremesa;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    //Gerar objetos cardapios WebService
    /*public static List<Cardapio> gerarCardapios(Context context) throws Exception{
        //List<Cardapio> cardapios;
        //cardapios.add(new Cardapio(01, "01-01-2012", "Lasanha", "Pavê", "230kcal"));
        //cardapios.add(new Cardapio(02, "01-01-2012", "Picanha", "Laranja", "230kcal"));
        //cardapios.add(new Cardapio(03, "11-11-2012", "Filé", "Goiabada", "230kcal"));

        return WebService.findAllItems(context);
    }
    */

    //Obter Objetos cardapios Firebase
    public static List<Cardapio> gerarCardapios(Context context) throws Exception{
        //List<Cardapio> cardapios = null;
        //cardapios.add((Cardapio) WebFirebase.getInstance().gerarCardapios());
        return WebFirebase.findAllItems(context);
    }
}
