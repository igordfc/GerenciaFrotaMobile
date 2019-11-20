package com.example.gerenciafrotamobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gerenciafrotamobile.Model.Ocorrencia;
import com.example.gerenciafrotamobile.Model.Usuario;
import com.example.gerenciafrotamobile.Model.Veiculo;
import com.example.gerenciafrotamobile.Service.ApiService;
import com.example.gerenciafrotamobile.Service.ApiSingleton;
import com.google.android.material.snackbar.Snackbar;

import org.parceler.Parcels;

import java.util.Optional;

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
    Veiculo veiculoAlugado = new Veiculo();
    Ocorrencia ocorrencia = new Ocorrencia();
    TextView semAluguel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        final Usuario usuarioLogado = Parcels.unwrap(getIntent().getParcelableExtra(EXTRA_USUARIO));
        nome = findViewById(R.id.txtNome);
        nome.setText(usuarioLogado.getNome());
        presentation = findViewById(R.id.txtPresentation);
        modelo = findViewById(R.id.txtModelo);
        marca = findViewById(R.id.txtMarca);
        placa = findViewById(R.id.txtPlaca);
        sair =  findViewById(R.id.txtSair);
        abreOcorrencia= findViewById(R.id.btnAbreOcorrencia);
        listaVeiculo = findViewById(R.id.btnListaVeiculo);
        semAluguel = findViewById(R.id.txtSemAluguel);
        presentation.setText(String.format("ola  %s seja bem vindo ao e-garage!", usuarioLogado.getNome()));

        final ApiService api = ApiSingleton.get().getApiService();
       api.mostraVeiculoAlugado(usuarioLogado.getId()).enqueue(new Callback<Ocorrencia>() {
            @Override
            public void onResponse(Call<Ocorrencia> call, Response<Ocorrencia> response) {
                if (response.isSuccessful()) {
                   final Ocorrencia ocorrenciaUsuario =  response.body();
                    ocorrencia = ocorrenciaUsuario;
                    veiculoAlugado = ocorrencia.getVeiculo();
                    placa.setText(veiculoAlugado.getPlaca());
                    marca.setText(veiculoAlugado.getMarca());
                    modelo.setText(veiculoAlugado.getModelo());

                }else {
                    mostrarMensagem(ApiSingleton.get().getMensagemErro(response.errorBody()));
                    placa.setText("sem veículo");
                    marca.setText("sem veículo");
                    modelo.setText("sem veículo");
                    semAluguel.setText("Você ainda não possui um veículo Alugado");
                    abreOcorrencia.setEnabled(false);



                }

            }

            @Override
            public void onFailure(Call<Ocorrencia> call, Throwable t) {
                Toast.makeText(Home.this, "Erro ao carregar o Layout", Toast.LENGTH_SHORT).show();
            }
        });
        sair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirecionarParaLogin();
            }
        });
       listaVeiculo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirecionarParaListagem(usuarioLogado);
            }
        });

        abreOcorrencia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirecionarParaAbrirOcorrencia(usuarioLogado,veiculoAlugado);
            }
        });
    }


    private void redirecionarParaListagem(Usuario usuario) {
        Intent listaVeiculos = new Intent(this, ListaVeiculo.class);
        listaVeiculos.putExtra(ListaVeiculo.EXTRA_USUARIO , Parcels.wrap(usuario));
        startActivity(listaVeiculos);
    }
    private void redirecionarParaAbrirOcorrencia(Usuario usuario, Veiculo veiculo) {
        Intent ocorrencias = new Intent(this, AbrirOcorrencia.class);
        ocorrencias.putExtra(AbrirOcorrencia.EXTRA_USUARIO, Parcels.wrap(usuario));
        ocorrencias.putExtra(AbrirOcorrencia.EXTRA_VEICULO, Parcels.wrap(veiculo));
        startActivity(ocorrencias);
    }

    private void redirecionarParaLogin() {
        Intent login = new Intent(this, MainActivity.class);
        startActivity(login);
    }
    private void mostrarMensagem(String mensagem) {
        Toast.makeText(this, "algo deu errado tente novamente mais tarde", Toast.LENGTH_SHORT).show();
    }

}
