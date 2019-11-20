package com.example.gerenciafrotamobile;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gerenciafrotamobile.Adapter.VeiculosAdapter;
import com.example.gerenciafrotamobile.Model.Usuario;
import com.example.gerenciafrotamobile.Model.Veiculo;
import com.example.gerenciafrotamobile.Service.ApiSingleton;

import org.parceler.Parcels;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListaVeiculo extends AppCompatActivity {

    private static final String TAG = ListaVeiculo.class.getSimpleName();

    public static final String EXTRA_USUARIO = "VeiculosActivity.usuario";

    private RecyclerView rvVeiculos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_veiculo);

        // Exemplo de uso do Parcelable (comentado)
        Usuario usuarioLogado = Parcels.unwrap(getIntent().getParcelableExtra(EXTRA_USUARIO));

        rvVeiculos = findViewById(R.id.rvVeiculos);
        rvVeiculos.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvVeiculos.setLayoutManager(layoutManager);

        carregarVeiculos();
    }

    private void carregarVeiculos() {
        ApiSingleton.get().getApiService().listarVeiculos().enqueue(new Callback<List<Veiculo>>() {
            @Override
            public void onResponse(Call<List<Veiculo>> call, Response<List<Veiculo>> response) {
                if (response.isSuccessful()) {
                    final List<Veiculo> veiculos = response.body();
                    //ArrayAdapter<Veiculo> adapter = new ArrayAdapter<>(VeiculosActivity.this, android.R.layout.simple_list_item_1, veiculos);

                    VeiculosAdapter adapter = new VeiculosAdapter(veiculos) {
                        @Override
                        public void onVeiculoClick(Veiculo veiculo) {
                            Log.d(TAG, String.format("Clicou no veiculo %s", veiculo.getId()));
                        }
                    };
                    rvVeiculos.setAdapter(adapter);
                } else {
                    //TODO tratar erros

                }
            }

            @Override
            public void onFailure(Call<List<Veiculo>> call, Throwable t) {
                // TODO Tratar erro inesperado...
            }
        });
    }
}
