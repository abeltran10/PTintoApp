package com.abeltran10.ptinto;




import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class ImagenFragment extends Fragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.imagen_layout, container, false);
        int pos = getArguments().getInt("posicion");
        ((ImageView) vista.findViewById(R.id.imageView)).setImageResource(getResources().getIdentifier(new Imagen()
                .getImg(pos), "drawable", getActivity().getApplicationInfo().packageName));
        return vista;
    }
}
