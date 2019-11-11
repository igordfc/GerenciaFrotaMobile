package com.example.gerenciafrotamobile;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.gerenciafrotamobile.Model.Ocorrencia;
import com.example.gerenciafrotamobile.Model.Usuario;
import com.example.gerenciafrotamobile.Service.ApiSingleton;

import org.parceler.Parcels;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Home extends AppCompatActivity {

    public static final String EXTRA_USUARIO = "Home.usuario";

    TextView nome ;
    TextView modelo;
    TextView placa;
    TextView marca;
    TextView presentation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Usuario usuarioLogado = Parcels.unwrap(getIntent().getParcelableExtra(EXTRA_USUARIO));
        nome = findViewById(R.id.txtNome);
        nome.setText(usuarioLogado.getNome());





    }

    private void carregaInformacoesAlugel(Usuario usuario){
        ApiSingleton.get().getApiService().mostraVeiculoAlugado().enqueue(new Callback<Ocorrencia>() {
            @Override
            public void onResponse(Call<Ocorrencia> call, Response<Ocorrencia> response) {

            }

            @Override
            public void onFailure(Call<Ocorrencia> call, Throwable t) {

            }
        });

    }
}
