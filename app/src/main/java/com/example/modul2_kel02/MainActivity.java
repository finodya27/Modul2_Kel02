package com.example.modul2_kel02;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.modul2_kel02.DataItem;
import com.example.modul2_kel02.ListUserResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    RecyclerView rvListUser;

    private List<DataItem> listItem;
    private RecycleAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvListUser = findViewById(R.id.rv_list_user);

        ApiClient.getService().getList().enqueue(new Callback<ListUserResponse>() {
            @Override
            public void onResponse(Call<ListUserResponse> call, Response<ListUserResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    listItem = response.body().getData();

                    adapter = new RecycleAdapter(listItem, MainActivity.this);
                    rvListUser.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    rvListUser.setAdapter(adapter);
                } else {
                    // Handle the case when the response is not successful or the body is null
                    Toast.makeText(getApplicationContext(), "Failed to retrieve data", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ListUserResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Error: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
