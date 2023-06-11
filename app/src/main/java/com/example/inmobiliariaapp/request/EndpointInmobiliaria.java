package com.example.inmobiliariaapp.request;

import com.example.inmobiliariaapp.models.Contrato;
import com.example.inmobiliariaapp.models.Inmueble;
import com.example.inmobiliariaapp.models.Inquilino;
import com.example.inmobiliariaapp.models.Pago;
import com.example.inmobiliariaapp.models.Propietario;
import com.example.inmobiliariaapp.models.Usuario;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface EndpointInmobiliaria {

    @POST("propietario/login")
    Call<String> login(@Body Usuario usuario);

    @GET("propietario/perfil")
    Call<Propietario> getPerfil(@Header("Authorization") String token);

    @POST("propietario/edit")
    Call<Propietario> editarPerfil(@Header("Authorization") String token, @Body Propietario propietario);

    @GET("inmueble/")
    Call<List<Inmueble>> obtenerInmuebles(@Header("Authorization") String token);

    @GET("inmueble/alquilados")
    Call<List<Inmueble>> obtenerInmueblesAlquilados(@Header("Authorization") String token);

    @GET("Inquilino/Obtener/{id}")
    Call<Inquilino> obtenerInquilinoPorInmueble(@Header("Authorization") String token, @Path("id") int id);

    @GET("Contrato/Obtener/{id}")
    Call<Contrato> obtenerContratoPorInmueble(@Header("Authorization") String token, @Path("id") int id);

    @GET("Pago/Obtener/{id}")
    Call<List<Pago>> obtenerPagosPorContrato(@Header("Authorization") String token, @Path("id") int id);


}