package br.ifpi.profabio.cardapiocapau.web;

import android.content.Context;
import android.os.Build;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import br.ifpi.profabio.cardapiocapau.data.Cardapio;

/**
 * Created by fabiorolim on 17/03/16.
 */
public class WebService {
    public static JSONObject requestWebService(String serviceUrl) {
        disableConnectionReuseIfNecessary();

        HttpURLConnection urlConnection = null;
        try {
            // create connection
            URL urlToRequest = new URL(serviceUrl);
            urlConnection = (HttpURLConnection)
                    urlToRequest.openConnection();
            urlConnection.setConnectTimeout(15000);
            urlConnection.setReadTimeout(15000);

            // handle issues
            int statusCode = urlConnection.getResponseCode();
            if (statusCode == HttpURLConnection.HTTP_UNAUTHORIZED) {
                // handle unauthorized (if service requires user login)
            } else if (statusCode != HttpURLConnection.HTTP_OK) {
                // handle any other errors, like 404, 500,..
            }

            // create JSON object from content
            InputStream in = new BufferedInputStream(
                    urlConnection.getInputStream());
            return new JSONObject(getResponseText(in));

        } catch (MalformedURLException e) {
            // URL is invalid
        } catch (SocketTimeoutException e) {
            // data retrieval or connection timed out
        } catch (IOException e) {
            // could not read response body
            // (could not create input stream)
        } catch (JSONException e) {
            // response body is no valid JSON string
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }

        return null;
    }

    /**
     * required in order to prevent issues in earlier Android version.
     */
    private static void disableConnectionReuseIfNecessary() {
        // see HttpURLConnection API doc
        if (Integer.parseInt(Build.VERSION.SDK)
                < Build.VERSION_CODES.FROYO) {
            System.setProperty("http.keepAlive", "false");
        }
    }

    private static String getResponseText(InputStream inStream) {
        // very nice trick from
        // http://weblogs.java.net/blog/pat/archive/2004/10/stupid_scanner_1.html
        return new Scanner(inStream).useDelimiter("\\A").next();
    }

    public static List<Cardapio> findAllItems(Context context) throws Exception{
        JSONObject serviceResult = WebService.requestWebService(
                "http://profabio.com.br/cardapiocapau/cardapio/webservice/exibirCardapios.php");

        List<Cardapio> foundItems = new ArrayList<Cardapio>(5);
        JSONArray itens = null;

            itens = serviceResult.getJSONArray("cardapios");

                for (int i = 0; i < itens.length(); i++) {
                    JSONObject obj = itens.getJSONObject(i);
                    foundItems.add(
                            new Cardapio(obj.getInt("cod_card"),
                                    obj.getString("data_card"),
                                    obj.getString("prato_principal"),
                                    obj.getString("sobremesa"),
                                    obj.getString("info_nutri")));

                /*foundItems.add(
                            new Cardapio(obj.getInt("cod_card"),
                                obj.getString("data_card"),
                                obj.getString("principal"),
                                obj.getString("sobremesa"),
                                obj.getString("info_card")));*/

                }

        return foundItems;
    }
}
