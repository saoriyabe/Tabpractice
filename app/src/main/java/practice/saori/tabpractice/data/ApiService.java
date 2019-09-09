package practice.saori.tabpractice.data;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {
    @GET("/pdichone/UIUX-Android-Course/master/Quotes.json%20")
    Call<ArrayList<Quote>> getQuotes();
}
