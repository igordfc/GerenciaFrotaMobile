package com.example.gerenciafrotamobile.Service;

import com.example.gerenciafrotamobile.Model.Ocorrencia;
import com.example.gerenciafrotamobile.Model.Usuario;
import com.example.gerenciafrotamobile.Model.Veiculo;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiService {

    @POST("login")
    Call<Usuario> logar(@Body Usuario credenciais);

    @POST("ocorrencias")
    Call<Void>abrirOcorrencia(@Body Ocorrencia ocorrencia);

    @GET("veiculos")
    Call<List<Veiculo>> listarVeiculos();

    @GET("ocorrencias/my/{id}")
    Call<Ocorrencia>mostraVeiculoAlugado(@Path("id") int groupId);



}
