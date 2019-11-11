package com.example.gerenciafrotamobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.gerenciafrotamobile.Model.Usuario;
import com.example.gerenciafrotamobile.Service.ApiService;
import com.example.gerenciafrotamobile.Service.ApiSingleton;
import com.google.android.material.snackbar.Snackbar;

import org.parceler.Parcels;
import org.w3c.dom.Text;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    TextView email;
    TextView senha;
    Button login;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email = findViewById(R.id.txtEmail);
        senha = findViewById(R.id.txtSenha);
        login = findViewById(R.id.btnLogin);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Usuario credenciais = new Usuario();
                if (isValidEmail(email.getText().toString())) {
                    credenciais.setEmail(email.getText().toString());
                }
                credenciais.setSenha(senha.getText().toString());

                final ApiService api = ApiSingleton.get().getApiService();
                api.logar(credenciais).enqueue(new Callback<Usuario>() {
                    @Override
                    public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                        if (response.isSuccessful()) {
                            final Usuario usuario = response.body();
                            String msg = String.format("Bem vindo %s!", usuario.getNome());
                            mostrarMensagem(msg);
                        } else {
                            mostrarMensagem(ApiSingleton.get().getMensagemErro(response.errorBody()));
                        }
                    }

                    @Override
                    public void onFailure(Call<Usuario> call, Throwable t) {
                        mostrarMensagem(t.getMessage());
                    }
                });
            }
        });
    }
        private void redirecionarParaHome(Usuario usuario) {
            Intent home = new Intent(this, Home.class);
            home.putExtra(Home.EXTRA_USUARIO, Parcels.wrap(usuario));
            startActivity(home);
        }



        private void mostrarMensagem(String mensagem) {
        Snackbar.make(login, mensagem, Snackbar.LENGTH_LONG).show();
        }
    public boolean isValidEmail(CharSequence target) {
        return !TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }
}
