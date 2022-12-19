package com.example.retrofit_ejercicio01;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.retrofit_ejercicio01.Adapters.UsersAdapter;
import com.example.retrofit_ejercicio01.Conexiones.APIConexiones;
import com.example.retrofit_ejercicio01.Conexiones.RetrofitObject;
import com.example.retrofit_ejercicio01.Modelos.Response;
import com.example.retrofit_ejercicio01.Modelos.Usuario;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    private RecyclerView.LayoutManager lm;
    private List<Usuario> usuarioList;
    private UsersAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView contenedor = findViewById(R.id.contenedor);

        usuarioList = new ArrayList<>();
        adapter = new UsersAdapter(usuarioList, this,R.layout.usuario_view_holder);
        lm = new LinearLayoutManager(this);

        contenedor.setAdapter(adapter);
        contenedor.setLayoutManager(lm);

        doGetUsuarios();

    }

    private void doGetUsuarios() {
        Retrofit retrofit = RetrofitObject.getConnection();
        APIConexiones api = retrofit.create(APIConexiones.class);

        Call<Response> getResponse = api.getResponse();
        getResponse.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                if(response.code() == HttpURLConnection.HTTP_OK){
                    Response resp = response.body();
                    usuarioList.addAll(resp.getData());
                    adapter.notifyItemRangeInserted(0, usuarioList.size());
                }
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                Toast.makeText(MainActivity.this, "ERROR DE CONEXIÓN", Toast.LENGTH_SHORT).show();
                Log.e("FAILURE", t.getLocalizedMessage());
            }
        });
    }
}