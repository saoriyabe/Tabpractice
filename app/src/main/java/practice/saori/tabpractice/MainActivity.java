package practice.saori.tabpractice;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import practice.saori.tabpractice.data.ApiService;
import practice.saori.tabpractice.data.JsonToGsonConverter;
import practice.saori.tabpractice.data.Quote;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TabLayout tabLayout = findViewById(R.id.tablayout);
        ViewPager viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager()));
        tabLayout.setupWithViewPager(viewPager);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://raw.githubusercontent.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
       ApiService service = retrofit.create(ApiService.class);
       service.getQuotes().enqueue(new Callback<ArrayList<Quote>>() {
            @Override
            public void onResponse(Call<ArrayList<Quote>> call, Response<ArrayList<Quote>> response) {
                Log.d("Retrofit", "onResponse");
                for(int i = 0; i < response.body().size(); i++) {
                    Log.d("onResponse", "author:" + response.body().get(i).getAuthor()
                            + "\n quote:" + response.body().get(i).getQuote());
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Quote>> call, Throwable t) {
                Log.d("Retrofit", "onFailure" + t.getMessage());
            }
        });
    }
}
