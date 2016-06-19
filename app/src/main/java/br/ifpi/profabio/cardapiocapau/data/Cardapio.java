package br.ifpi.profabio.cardapiocapau.data;

import android.content.Context;

import java.util.List;

import br.ifpi.profabio.cardapiocapau.web.WebService;

/**
 * Created by fabiorolim on 17/03/16.
 */
public class Cardapio {
    private int cod;
    private String data;
    private String principal;
    private String sobremesa;
    private String info;

    private List<Cardapio> cardapios;

    public Cardapio(int cod, String data){
        this.cod = cod;
        this.data = data;
    }

    public Cardapio(int cod, String data, String principal, String sobremesa, String info) {
        this.cod = cod;
        this.data = data;
        this.principal = principal;
        this.sobremesa = sobremesa;
        this.info = info;
    }

    public int getCod(){
        return this.cod;
    }

    public void setCod(int cod){
        this.cod = cod;
    }

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

    public static List<Cardapio> gerarCardapios(Context context) throws Exception{
        //List<Cardapio> cardapios;
        /*cardapios.add(new Cardapio(01, "01-01-2012", "Lasanha", "Pavê", "230kcal"));
        cardapios.add(new Cardapio(02, "01-01-2012", "Picanha", "Laranja", "230kcal"));
        cardapios.add(new Cardapio(03, "11-11-2012", "Filé", "Goiabada", "230kcal"));*/

        return WebService.findAllItems(context);
    }
}
