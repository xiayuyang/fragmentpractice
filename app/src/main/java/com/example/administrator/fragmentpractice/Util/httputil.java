package com.example.administrator.fragmentpractice.Util;

/**
 * Created by Administrator on 2018/5/25.
 */

import org.json.JSONException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;


/**
 * Created by Administrator on 2018/2/5.
 */
public class httputil {


    //public Httputil() throws IOException {


    //}


    public static void sendHttpRequest(final String address, final String param, final Callback callback) {
        // final android.os.Handler handler = new
        //android.os.Handler();
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection httpURLConnection = null;

                try {
                    URL url = null;
                    try {
                        url = new URL(address);
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                    try {
                        httpURLConnection = (HttpURLConnection) url.openConnection();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    //httpURLConnection.connect();
                    try {
                        httpURLConnection.setRequestMethod("POST");
                    } catch (ProtocolException e) {
                        e.printStackTrace();
                    }
                    httpURLConnection.setConnectTimeout(8000);
                    httpURLConnection.setReadTimeout(8000);

                    httpURLConnection.setDoOutput(true);
                    OutputStream os = null;
                    try {
                        os = httpURLConnection.getOutputStream();
                        os.write(param.getBytes());
                        os.flush();
                        os.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }finally {
                }
                try {
                    if (httpURLConnection.getResponseCode() == 200) {
                        final byte[] temp = read(httpURLConnection.getInputStream());
                        String string = new String(temp,"UTF-8");
                        callback.onResponse(string);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }).start();

    }

    /*public static String getDataString(String address) throws IOException {
        byte[] bytes = getDataBytes(address);
        if (bytes != null) return Arrays.toString(bytes);
        else return "";

    }
    */
    private static byte[] read(InputStream is) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] bytes = new byte[1024];
        int len;
        while ((len = is.read(bytes)) != -1)
            outputStream.write(bytes, 0, len);
        is.close();
        return outputStream.toByteArray();
    }

    public interface Callback {
        void onResponse(String response) throws JSONException;
    }

    public interface ImageCallback {
        void onResponse(byte[] bytes);
    }

    public static void sendImageHttpRequest(final String address, final ImageCallback callback) {
        //final android.os.Handler handler = new
        //android.os.Handler();
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection httpURLConnection = null;

                try {
                    URL url = new URL(address);
                    httpURLConnection = (HttpURLConnection) url.openConnection();
                    //httpURLConnection.connect();
                    httpURLConnection.setRequestMethod("GET");
                    httpURLConnection.setConnectTimeout(8000);
                    httpURLConnection.setReadTimeout(8000);
                    if (httpURLConnection.getResponseCode() != HttpURLConnection.HTTP_OK)
                        throw new IOException(httpURLConnection.getResponseMessage() + ": with " + address);
                    else {
                        InputStream in = httpURLConnection.getInputStream();

                        byte[] bytes = read(in);
                        //handler.post(new Runnable() {
                        //@Override
                        //public void run() {
                        callback.onResponse(bytes);
                        //}
                        //});

                    }


                } catch (MalformedURLException e) {
                    e.printStackTrace();

                } catch (ProtocolException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (httpURLConnection != null) httpURLConnection.disconnect();
                }

            }
        }).start();

    }


}

