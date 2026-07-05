package com.abeltran10.ptinto.ui.main;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.abeltran10.ptinto.R;
import com.abeltran10.ptinto.data.Frase;

import java.util.List;

public class AdaptadorFrases extends RecyclerView.Adapter<AdaptadorFrases.FraseViewHolder> {

    private final List<Frase> listaFrases;
    private final OnFraseClickListener listener;

    public interface OnFraseClickListener {
        void onFraseClick(Frase frase);
        void onFraseLongClick(Frase frase);
    }

    public AdaptadorFrases(List<Frase> listaFrases, OnFraseClickListener listener) {
        this.listaFrases = listaFrases;
        this.listener = listener;
    }

    @Override
    public FraseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.elemento_selector, parent, false);
        return new FraseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FraseViewHolder holder, int position) {
        Frase frase = listaFrases.get(position);
        holder.bind(frase, listener);
    }

    @Override
    public int getItemCount() {
        return listaFrases.size();
    }

    // ViewHolder: Mejora el rendimiento evitando findViewById repetidos
    static class FraseViewHolder extends RecyclerView.ViewHolder {
        private final TextView textView;

        FraseViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.frase);
        }

        void bind(final Frase frase, final OnFraseClickListener listener) {
            textView.setText(frase.getFrase());

            itemView.setOnClickListener(v -> listener.onFraseClick(frase));
            itemView.setOnLongClickListener(v -> {
                listener.onFraseLongClick(frase);
                return true;
            });
        }
    }
}
