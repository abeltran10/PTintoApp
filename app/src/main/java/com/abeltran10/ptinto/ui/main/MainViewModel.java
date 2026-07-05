package com.abeltran10.ptinto.ui.main;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
public class MainViewModel extends ViewModel {
        private final MutableLiveData<Integer> imagePosition = new MutableLiveData<>();
        private final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

        public MainViewModel() {
            // Ejecuta la lógica cada 5 segundos
            scheduler.scheduleAtFixedRate(() -> {
                int pos = new Random().nextInt(15);
                imagePosition.postValue(pos); // postValue es seguro para hilos
            }, 0, 5, TimeUnit.SECONDS);
        }

        public LiveData<Integer> getImagePosition() { return imagePosition; }

        @Override
        protected void onCleared() {
            super.onCleared();
            scheduler.shutdown(); // Limpieza automática al destruir el ViewModel
        }
}
