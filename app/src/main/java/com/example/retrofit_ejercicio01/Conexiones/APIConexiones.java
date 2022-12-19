package com.example.retrofit_ejercicio01.Conexiones;

import com.example.retrofit_ejercicio01.Modelos.Response;
import com.example.retrofit_ejercicio01.Modelos.Usuario;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIConexiones {

    @GET("/api/users")
    Call<Response> getResponse();

}
