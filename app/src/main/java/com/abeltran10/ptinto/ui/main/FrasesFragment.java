package com.abeltran10.ptinto.ui.main;


import android.content.Intent;
import android.os.Bundle;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.abeltran10.ptinto.R;
import com.abeltran10.ptinto.data.Frase;
import com.abeltran10.ptinto.data.FraseRepository;
import com.abeltran10.ptinto.ui.dialog.Dialogo;
import com.abeltran10.ptinto.player.Reproductor;

import java.util.List;

public class FrasesFragment extends Fragment {

    private AdaptadorFrases adaptador;
    private List<Frase> listaFrases;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Inicializamos los datos aquí una sola vez
        String consulta = (getArguments() != null) ? getArguments().getString("consulta") : null;
        listaFrases = (consulta != null && !consulta.isEmpty())
                ? FraseRepository.buscar(consulta)
                : FraseRepository.getListaFrases();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.content_main, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // 2. Configurar RecyclerView
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        // 3. Crear adaptador y asignar
        adaptador = new AdaptadorFrases(listaFrases, createClickListener());
        recyclerView.setAdapter(adaptador);
    }

    private AdaptadorFrases.OnFraseClickListener createClickListener() {
        return new AdaptadorFrases.OnFraseClickListener() {
            @Override
            public void onFraseClick(Frase frase) {
                reproducirAudio(frase);
            }

            @Override
            public void onFraseLongClick(Frase frase) {
                mostrarDialogo(frase);
            }
        };
    }

    private void reproducirAudio(Frase frase) {
        Intent intent = new Intent(requireContext(), Reproductor.class);
        intent.putExtra("frase", frase.getFrase());
        // Solo inicia el servicio. La lógica de release() está dentro del Reproductor.
        requireContext().startService(intent);
    }

    private void mostrarDialogo(Frase frase) {
        Dialogo dialogo = new Dialogo();
        Bundle bundle = new Bundle();
        bundle.putString("titulo", frase.getFrase());
        dialogo.setArguments(bundle);
        dialogo.show(getParentFragmentManager(), "opciones");
    }
}
