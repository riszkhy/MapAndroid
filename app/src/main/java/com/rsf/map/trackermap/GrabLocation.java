package com.rsf.map.trackermap;

import android.os.AsyncTask;
import android.util.Log;

/**
 * Created by riszkhy on 18/09/16.
 */
public class GrabLocation extends AsyncTask<Object,String, String> {

    String googlePlacesData;
    String url;
    String data;
    @Override
    protected String doInBackground(Object... objects) {
        try {
            Log.d("GetNearbyPlacesData", "doInBackground entered");
            url = (String) objects[0];
            DownloadURL downloadUrl = new DownloadURL();
            googlePlacesData = downloadUrl.readUrl(url);
            Log.d("GooglePlacesReadTask", "doInBackground Exit");
        } catch (Exception e) {
            Log.d("GooglePlacesReadTask", e.toString());
        }
        return googlePlacesData;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
//        getPropData();

    }


    public String getPropData() {
        return data;
    }


}
