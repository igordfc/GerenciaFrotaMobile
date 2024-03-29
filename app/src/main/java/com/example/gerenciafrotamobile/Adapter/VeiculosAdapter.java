package com.example.gerenciafrotamobile.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.gerenciafrotamobile.Model.Veiculo;
import com.example.gerenciafrotamobile.R;

import java.util.List;

public abstract class VeiculosAdapter extends RecyclerView.Adapter<VeiculosAdapter.ViewHolder> {
    private List<Veiculo> mDataset;

    public abstract void onVeiculoClick(Veiculo veiculo);

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView ivVeiculo;
        public TextView tvPlaca;
        public TextView tvMarca;
        public TextView tvTipo;
        public TextView tvAno;
        public TextView tvModelo;

        public ViewHolder(View v) {
            super(v);
            tvPlaca = v.findViewById(R.id.tvPlaca);
            tvMarca = v.findViewById(R.id.tvMarca);
            tvModelo= v.findViewById(R.id.tvModelo);
            tvTipo = v.findViewById(R.id.tvTipo);
            tvAno = v.findViewById(R.id.tvAno);
        }
    }

    public VeiculosAdapter(List<Veiculo> myDataset) {
        mDataset = myDataset;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_veiculo, parent, false);

        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Veiculo veiculo = mDataset.get(position);

        holder.tvPlaca.setText(veiculo.getPlaca());
        holder.tvMarca.setText(veiculo.getMarca());
        holder.tvModelo.setText(veiculo.getModelo());
        holder.tvTipo.setText(veiculo.getTipo().name());
        holder.tvAno.setText(veiculo.getAno().toString());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onVeiculoClick(veiculo);
            }
        });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
