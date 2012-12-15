package com.example.niko.api;

import com.google.gson.Gson;
import org.apache.http.HttpResponse;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;

public class NikoNikoApi {

    private final String baseUrl;
    private final String username;
    private final String password;

    public NikoNikoApi(String baseUrl, String username, String password) {

        this.baseUrl = baseUrl;
        this.username = username;
        this.password = password;
    }


    public Response postRating(AddRatingRequest requestData) {

        URI uri = URI.create(baseUrl + "/nikoniko");
        HttpPost request = new HttpPost(uri);
        setHeading(request);

        String jsonRequestData = new Gson().toJson(requestData);
        try {
            request.setEntity(new StringEntity(jsonRequestData));
        } catch (UnsupportedEncodingException e) {
            return new Response(e);
        }


        DefaultHttpClient client = new DefaultHttpClient();
        try {
            HttpResponse execute = client.execute(request);
            return new Response(execute);
        } catch (IOException e) {
            return new Response(e);
        }


    }

    private void setHeading(HttpRequestBase request) {
        request.addHeader(
                BasicScheme.authenticate(
                        new UsernamePasswordCredentials(username, password),
                        "UTF-8",
                        false)
        );
        request.addHeader("Content-type", "application/json");
    }
}