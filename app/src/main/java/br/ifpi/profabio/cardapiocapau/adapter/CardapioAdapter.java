package br.ifpi.profabio.cardapiocapau.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import br.ifpi.profabio.cardapiocapau.R;
import br.ifpi.profabio.cardapiocapau.data.Cardapio;

/**
 * Created by fabiorolim on 17/03/16.
 */
public class CardapioAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    private List<Cardapio> cardapios;

    public CardapioAdapter(Context context, List<Cardapio> cardapios){
        this.cardapios = cardapios;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return cardapios.size();
    }

    @Override
    public Object getItem(int position) {
        return cardapios.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View view = inflater.inflate(R.layout.adapter_cardapio, parent, false);
        Cardapio cardapio = cardapios.get(position);
        List<String> data_e_dia;
        String data_formatada = null;
        String dia_semana = null;


        try {
            data_e_dia = CardapioAdapter.formatarDatas(cardapio.getData());
            dia_semana = data_e_dia.get(0);
            data_formatada = data_e_dia.get(1);
        } catch (ParseException e) {
            e.printStackTrace();
        }


        TextView txtData = (TextView) view.findViewById(R.id.txt_data);
        TextView txtDia = (TextView) view.findViewById(R.id.txt_dia);

        txtData.setText(dia_semana);
        txtDia.setText(data_formatada);

        //Log.d("Listando", String.valueOf(cardapio.getCod()));
        //Log.d("Listando", cardapio.getData());

        return view;
    }

    public static List<String> formatarDatas(String data) throws ParseException {
        //String d = "2016-03-18";
        SimpleDateFormat sd1 = new SimpleDateFormat("yyyy-MM-dd");
        Date d1 = sd1.parse(data);
        System.out.println(d1);

        SimpleDateFormat sd2 = new SimpleDateFormat("dd-MM-yyyy");
        String dataf = sd2.format(d1);
        //System.out.println(dataf);

        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(d1);
        int dia = cal.get(Calendar.DAY_OF_WEEK);
        String dias = null;

        if (dia == Calendar.SUNDAY){
            dias = "Domingo";
        }
        if (dia == Calendar.MONDAY){
            dias = "Segunda-Feira";
        }
        if (dia == Calendar.TUESDAY){
            dias = "Terça-Feira";
        }
        if (dia == Calendar.WEDNESDAY){
            dias = "Quarta-Feira";
        }
        if (dia == Calendar.THURSDAY){
            dias = "Quinta-Feira";
        }
        if (dia == Calendar.FRIDAY){
            dias = "Sexta-Feira";
        }
        if (dia == Calendar.SATURDAY){
            dias = "Sábado";
        }

        ArrayList<String> datas = new ArrayList<>();
        datas.add(dias);
        datas.add(dataf);

        return datas;
    }
}
