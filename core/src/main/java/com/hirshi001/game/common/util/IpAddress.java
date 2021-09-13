package com.hirshi001.game.common.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.net.HttpRequestBuilder;

import java.util.concurrent.atomic.AtomicBoolean;

public class IpAddress {

    public static String get(){
        if(ipAddress==null) loadAddress();
        return ipAddress;
    }

    public static void loadAddress(){
        if(gettingAddress.compareAndSet(false, true)) {
            urlIndex = 0;
            setIpAddress();
        }
    }

    private static volatile String ipAddress;

    private static final AtomicBoolean gettingAddress = new AtomicBoolean(false);

    private static final String[] urlsForIp = {"https://ipv4.icanhazip.com/", "https://checkip.amazonaws.com/"};
    private static int urlIndex = 0;
    private static int calls = 0;
    private static final int maxCalls = 2;

    private static void setIpAddress(){
        if(calls>=maxCalls){
            gettingAddress.set(false);
            return;
        }
        calls++;

        HttpRequestBuilder builder = new HttpRequestBuilder();
        Net.HttpRequest httpRequest = builder.newRequest()
                .method(Net.HttpMethods.GET)
                .url(urlsForIp[urlIndex])
                .timeout(5000)
                .build();

        Net.HttpResponseListener listener = new Net.HttpResponseListener() {
            @Override
            public void handleHttpResponse(Net.HttpResponse httpResponse) {
                String addy = httpResponse.getResultAsString();
                if(addy == null || addy.equals("")) return;


                addy = addy.substring(0, addy.length()-1); /* removes '\n' character from end of the string */

                System.out.println("Got ip address: " + addy + " from url: "+httpRequest.getUrl());
                ipAddress = addy;
                gettingAddress.set(false);
            }

            @Override
            public void failed(Throwable t) {
                urlIndex++;
                if(urlIndex == urlsForIp.length){
                    urlIndex = 0;
                }
                setIpAddress();
            }

            @Override
            public void cancelled() {
                gettingAddress.set(false);
            }
        };

        Gdx.net.sendHttpRequest(httpRequest, listener);

    }
}
