package com.example.gerenciafrotamobile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gerenciafrotamobile.Model.Ocorrencia;
import com.example.gerenciafrotamobile.Model.Usuario;
import com.example.gerenciafrotamobile.Model.Veiculo;
import com.example.gerenciafrotamobile.Service.ApiService;
import com.example.gerenciafrotamobile.Service.ApiSingleton;
import com.google.android.material.snackbar.Snackbar;

import org.parceler.Parcels;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AbrirOcorrencia extends AppCompatActivity {

    public static final String EXTRA_USUARIO = "AbrirOcorrencia.usuario" ;
    public static final String EXTRA_VEICULO ="AbrirOcorrencia.veiculo";

    TextView titulo;
    TextView descricao;
    Button ocorrencia;
    ImageView sair;
    Ocorrencia ocorrenciaAberta = new Ocorrencia();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Usuario usuarioOcorrencia = Parcels.unwrap(getIntent().getParcelableExtra(EXTRA_USUARIO));
        final Veiculo usuarioVeiculo = Parcels.unwrap(getIntent().getParcelableExtra(EXTRA_VEICULO));
        setContentView(R.layout.activity_abrir_ocorrencia);
        titulo =findViewById(R.id.edtTitulo);
        descricao= findViewById(R.id.edtDesc);
        sair =  findViewById(R.id.txtSair);
        ocorrencia = findViewById(R.id.btnAbrirOcorrencia);
        ocorrenciaAberta.setUsuario(usuarioOcorrencia);
        ocorrenciaAberta.setVeiculo(usuarioVeiculo);



        sair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirecionarParaLogin();
            }
        });


        ocorrencia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(titulo.getText().toString().toLowerCase().contains("aluguel")) {
                    Toast.makeText(AbrirOcorrencia.this, "Titulo Invalido", Toast.LENGTH_SHORT).show();
                }else {
                    ocorrenciaAberta.setTitulo(titulo.getText().toString());
                    ocorrenciaAberta.setDescricao(descricao.getText().toString());

                    abreOcorrencia(ocorrenciaAberta);
                }

            }
        });
    }

    private void abreOcorrencia(final Ocorrencia ocorrencia){
        final ApiService api = ApiSingleton.get().getApiService();
        api.abrirOcorrencia(ocorrencia).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {

                mostrarMensagem("sua ocorrencia foi enviada com sucesso");
                titulo.setText("");
                descricao.setText("");
                redirecionarParaHome(ocorrencia.getUsuario());


            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(AbrirOcorrencia.this, "Erro ao abrir a Ocorrencia", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void redirecionarParaLogin() {
        Intent login = new Intent(this, MainActivity.class);
        startActivity(login);
    }
    private void mostrarMensagem(String mensagem) {
        Snackbar.make(ocorrencia,mensagem, Snackbar.LENGTH_LONG).show();
    }
    private void redirecionarParaHome(Usuario usuario) {
        Intent home = new Intent(this, Home.class);
        home.putExtra(Home.EXTRA_USUARIO, Parcels.wrap(usuario));
        startActivity(home);
    }
}
