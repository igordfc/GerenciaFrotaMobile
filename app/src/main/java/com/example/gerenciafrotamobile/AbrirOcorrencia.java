package com.example.gerenciafrotamobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gerenciafrotamobile.Model.Ocorrencia;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class AbrirOcorrencia extends AppCompatActivity {

    public static final String EXTRA_USUARIO = "AbrirOcorrencia.usuario" ;

    TextView titulo;
    TextView descricao;
    Button ocorrencia;
    ImageView sair;
    Ocorrencia ocorrenciaAberta = new Ocorrencia();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abrir_ocorrencia);
        titulo =findViewById(R.id.txtTitulo);
        descricao= findViewById(R.id.txtDesc);
        sair =  findViewById(R.id.txtSair);
        ocorrencia = findViewById(R.id.btnAbreOcorrencia);
        sair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirecionarParaLogin();
            }
        });
//        LocalDateTime data = new LocalDate().;
//
//            ocorrenciaAberta.setTitulo();
//            ocorrenciaAberta.setDescrição();
//            ocorrenciaAberta.setUsuario();
//            ocorrenciaAberta.setInicio();



//        ocorrencia setOnClickListner(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                //TODO
//            }
//        });
    }

    private void redirecionarParaLogin() {
        Intent login = new Intent(this, MainActivity.class);
        startActivity(login);
    }
}
