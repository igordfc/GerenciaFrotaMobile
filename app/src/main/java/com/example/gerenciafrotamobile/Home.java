package com.example.gerenciafrotamobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gerenciafrotamobile.Model.Ocorrencia;
import com.example.gerenciafrotamobile.Model.Usuario;
import com.example.gerenciafrotamobile.Service.ApiSingleton;
import com.google.android.material.snackbar.Snackbar;

import org.parceler.Parcels;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Home extends AppCompatActivity {

    public static final String EXTRA_USUARIO = "Home.usuario";

    TextView nome ;
    TextView modelo;
    TextView presentation;
    TextView placa;
    TextView marca;
    Button listaVeiculo;
    Button abreOcorrencia;
    ImageView sair;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        final Usuario usuarioLogado = Parcels.unwrap(getIntent().getParcelableExtra(EXTRA_USUARIO));
        carregaInformacoesAlugel(usuarioLogado);
        nome = findViewById(R.id.txtNome);
        nome.setText(usuarioLogado.getNome());
        presentation = findViewById(R.id.txtPresentation);
        modelo = findViewById(R.id.txtModelo);
        marca = findViewById(R.id.txtMarca);
        placa = findViewById(R.id.txtPlaca);

        sair =  findViewById(R.id.txtSair);

        presentation.setText(String.format("ola  %s seja bem vindo ao e-garage!", usuarioLogado.getNome()));

        listaVeiculo = findViewById(R.id.btnListaVeiculo);

        listaVeiculo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirecionarParaListagem(usuarioLogado);
            }
        });

        abreOcorrencia= findViewById(R.id.btnAbreOcorrencia);

        abreOcorrencia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirecionarParaAbrirOcorrencia(usuarioLogado);
            }
        });

        sair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirecionarParaLogin();
            }
        });

    }
    private void carregaInformacoesAlugel(Usuario usuario){
        ApiSingleton.get().getApiService().mostraVeiculoAlugado(usuario.getId()).enqueue(new Callback<Ocorrencia>() {
            @Override
            public void onResponse(Call<Ocorrencia> call, Response<Ocorrencia> response) {
                if (response.isSuccessful()) {
                    final Ocorrencia ocorrencia = response.body();

                }else {
                    mostrarMensagem(ApiSingleton.get().getMensagemErro(response.errorBody()));
                }

            }


            @Override
            public void onFailure(Call<Ocorrencia> call, Throwable t) {

            }
        });

    }






    private void redirecionarParaListagem(Usuario usuario) {
        Intent listaVeiculos = new Intent(this, ListaVeiculo.class);
        listaVeiculos.putExtra(ListaVeiculo.EXTRA_USUARIO , Parcels.wrap(usuario));
        startActivity(listaVeiculos);
    }
    private void redirecionarParaAbrirOcorrencia(Usuario usuario) {
        Intent ocorrencias = new Intent(this, AbrirOcorrencia.class);
        ocorrencias.putExtra(AbrirOcorrencia.EXTRA_USUARIO, Parcels.wrap(usuario));
        startActivity(ocorrencias);
    }

    private void redirecionarParaLogin() {
        Intent login = new Intent(this, MainActivity.class);
        startActivity(login);
    }
    private void mostrarMensagem(String mensagem) {
        Snackbar.make(nome,mensagem, Snackbar.LENGTH_LONG).show();
    }

}
