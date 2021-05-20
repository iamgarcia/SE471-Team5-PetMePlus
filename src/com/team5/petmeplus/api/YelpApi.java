package com.team5.petmeplus.api;

import com.google.gson.Gson;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class YelpApi {

    private static final String API_HOST = "api.yelp.com";
    private static final String SEARCH_PATH = "/v3/businesses/search";
    private static final int SEARCH_LIMIT = 5;
    private static final String API_KEY = System.getenv("API_KEY");
    private final HttpClient client = HttpClient.newHttpClient();

    public String createAPIRequest() {
        return "https://" + API_HOST + SEARCH_PATH + "?";
    }

    public String addQueryStringParameter(String name, String value) {
        return "&" + name + "=" + value;
    }

    public List<Business> searchForBusinessesByZipcode(String service, String zipcode) {
        String uri = createAPIRequest();
        uri += addQueryStringParameter("term", service);
        uri += addQueryStringParameter("limit", String.valueOf(SEARCH_LIMIT));
        uri += addQueryStringParameter("location", zipcode);

        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(uri))
                    .header("authorization", "Bearer" + " " + API_KEY)
                    .GET()
                    .build();

            return deserializeBody(client.send(request, HttpResponse.BodyHandlers.ofString()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<Business> deserializeBody(HttpResponse<String> response) {
        Gson gson = new Gson();
        RootInfo rootInfo = gson.fromJson(response.body(), RootInfo.class);
        System.out.println(response.body());

        return rootInfo.getBusinesses();
    }
}
