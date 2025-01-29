package com.example.navigation;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class RecyclerValoracionFragment extends RecyclerElementosFragment {

    @Override
    LiveData<List<Elemento>> obtenerElementos() {
        return elementosViewModel.masValorados();
    }
}