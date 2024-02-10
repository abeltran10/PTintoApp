package com.abeltran10.ptinto;

import android.support.v4.app.Fragment;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.Menu;



import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;

public class MainActivity extends AppCompatActivity {
    Vector<MyAsyncTask> tareas = new Vector<>();

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.frame_layout);
        getSupportFragmentManager().beginTransaction().add((int) R.id.frame_container, (Fragment) new FrasesFragment()).commit();
        this.tareas.add(new MyAsyncTask());
        this.tareas.elementAt(0).execute(new Integer[0]);
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        if (!this.tareas.isEmpty()) {
            this.tareas.elementAt(0).cancel(true);
            this.tareas.remove(0);
        }
        super.onPause();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        if (this.tareas.isEmpty()) {
            this.tareas.add(new MyAsyncTask());
            this.tareas.elementAt(0).execute(new Integer[0]);
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.menu_buscar));
        searchView.setSearchableInfo(((SearchManager) getSystemService(Context.SEARCH_SERVICE)).getSearchableInfo(getComponentName()));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            public boolean onQueryTextSubmit(String query) {
                searchView.setIconified(true);
                searchView.clearFocus();
                searchView.onActionViewCollapsed();
                return false;
            }

            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return true;
    }

    /* access modifiers changed from: protected */
    public void onNewIntent(Intent intent) {
        if (intent.getAction() != null && intent.getAction().equals("android.intent.action.SEARCH")) {
            String consulta = intent.getStringExtra("query");
            FrasesFragment f = new FrasesFragment();
            Bundle args = new Bundle();
            args.putString("consulta", consulta);
            f.setArguments(args);
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, f).addToBackStack((String) null).commit();
        }
    }

    /* access modifiers changed from: protected */
    public void onStop() {
        if (!this.tareas.isEmpty()) {
            this.tareas.elementAt(0).cancel(true);
            this.tareas.remove(0);
        }
        stopService(new Intent(this, Reproductor.class));
        super.onStop();
    }

    /* access modifiers changed from: protected */
    public void onRestart() {
        super.onRestart();
        if (this.tareas.isEmpty()) {
            this.tareas.add(new MyAsyncTask());
            this.tareas.elementAt(0).execute(new Integer[0]);
        }
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        if (!this.tareas.isEmpty()) {
            this.tareas.elementAt(0).cancel(true);
            this.tareas.remove(0);
        }
        stopService(new Intent(this, Reproductor.class));
        super.onDestroy();
    }

    class MyAsyncTask extends AsyncTask<Integer, Integer, Integer> {
        MyAsyncTask() {
        }

        /* access modifiers changed from: protected */
        public Integer doInBackground(Integer... params) {
            new Timer().scheduleAtFixedRate(new TimerTask() {
                public void run() {
                    if (!MyAsyncTask.this.isCancelled()) {
                        int pos = new Random().nextInt(15);
                        ImagenFragment i = new ImagenFragment();
                        Bundle args = new Bundle();
                        args.putInt("posicion", pos);
                        i.setArguments(args);
                        MainActivity.this.getSupportFragmentManager().beginTransaction().replace(R.id.image_fragment, i).commit();
                    }
                }
            }, 0, 5000);
            return null;
        }
    }
}
