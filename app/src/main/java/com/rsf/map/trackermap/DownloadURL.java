package com.rsf.map.trackermap;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by riszkhy on 18/09/16.
 */
public class DownloadURL {
    public String readUrl(String strURL) throws IOException{
        String data = "";
        InputStream inputStream = null;
        HttpsURLConnection urlConnection = null;
        try{
            URL url = new URL(strURL);

            //Buat http koneksi
            urlConnection = (HttpsURLConnection) url.openConnection();

            //konek ke url
            urlConnection.connect();

            //baca data
            inputStream = urlConnection.getInputStream();

            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));

            StringBuffer sb = new StringBuffer();

            String line = "";
            while((line = br.readLine()) != null){
                sb.append(line);
            }
            data = sb.toString();
            Log.d("downloadURL", data.toString());
            br.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            inputStream.close();
            urlConnection.disconnect();
        }
        return data;
    }
}
