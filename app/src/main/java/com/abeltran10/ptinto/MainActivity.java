package com.abeltran10.ptinto;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;

import android.view.Menu;
import android.view.MenuItem;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;

import com.abeltran10.ptinto.ui.main.FrasesFragment;
import com.abeltran10.ptinto.ui.main.ImagenFragment;
import com.abeltran10.ptinto.ui.main.MainViewModel;
import com.abeltran10.ptinto.player.Reproductor;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Esto es lo que pone el título de la App en la toolbar
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(R.string.app_name);
        }

        MainViewModel viewModel = new ViewModelProvider(this).get(MainViewModel.class);

        // 1. Carga inicial de la lista si es necesario
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frame_container, new FrasesFragment())
                    .commit();
        }

        // 2. Observar cambios de imagen
        viewModel.getImagePosition().observe(this, pos -> {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.image_fragment, ImagenFragment.newInstance(pos))
                    .commit();
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        MenuItem searchItem = menu.findItem(R.id.menu_buscar);
        SearchView searchView = (SearchView) searchItem.getActionView();

        if (searchView != null) {
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    searchView.clearFocus();
                    handleSearch(query);
                    return true;
                }
                @Override
                public boolean onQueryTextChange(String newText) { return false; }
            });
        }
        return true;
    }

    private void handleSearch(String consulta) {
        FrasesFragment f = new FrasesFragment();
        Bundle args = new Bundle();
        args.putString("consulta", consulta);
        f.setArguments(args);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame_container, f)
                .addToBackStack(null)
                .commit();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            handleSearch(intent.getStringExtra(SearchManager.QUERY));
        }
    }

    @Override
    protected void onDestroy() {
        // Unificar parada de servicios aquí es suficiente
        stopService(new Intent(this, Reproductor.class));
        super.onDestroy();
    }
}