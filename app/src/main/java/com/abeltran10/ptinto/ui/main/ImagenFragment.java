package com.abeltran10.ptinto.ui.main;

import android.content.Context;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.abeltran10.ptinto.R;
import com.abeltran10.ptinto.data.Imagen;

public class ImagenFragment extends Fragment {
    private static final String ARG_POSICION = "posicion";

    // Patrón de diseño: Static Factory Method
    public static ImagenFragment newInstance(int posicion) {
        ImagenFragment fragment = new ImagenFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_POSICION, posicion);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.imagen_layout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (getArguments() != null) {
            int pos = getArguments().getInt(ARG_POSICION);
            ImageView imageView = view.findViewById(R.id.imageView);

            // Asumiendo que Imagen es una clase helper o un modelo
            String nombreImagen = new Imagen().getImg(pos);

            // Contexto seguro
            Context context = requireContext();
            int resId = context.getResources().getIdentifier(
                    nombreImagen, "drawable", context.getPackageName());

            if (resId != 0) {
                imageView.setImageResource(resId);
            }
        }
    }
}