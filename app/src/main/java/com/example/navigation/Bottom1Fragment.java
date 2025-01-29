package com.example.navigation;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.example.navigation.databinding.FragmentBottom1Binding;

public class Bottom1Fragment extends Fragment {

    private FragmentBottom1Binding binding;
    private ElementosViewModel elementosViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentBottom1Binding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        NavController navController = NavHostFragment.findNavController(this);
        NavigationUI.setupWithNavController(binding.bottomNavView, navController);
        elementosViewModel = new ViewModelProvider(requireActivity()).get(ElementosViewModel.class);

        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController controller,
                                             @NonNull NavDestination destination, @Nullable Bundle arguments) {

                if (destination.getId() == R.id.nuevoElementoFragment
                        || destination.getId() == R.id.mostrarElementoFragment) {
                    binding.bottomNavView.setVisibility(View.GONE);
                } else {
                    binding.bottomNavView.setVisibility(View.VISIBLE);
                }

                if (destination.getId() == R.id.recyclerBuscarFragment) {
                    binding.searchView.setVisibility(View.VISIBLE);
                    binding.searchView.setIconified(false);
                    binding.searchView.requestFocusFromTouch();
                } else {
                    binding.searchView.setVisibility(View.GONE);
                }
            }
        });

        binding.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                elementosViewModel.establecerTerminoBusqueda(newText);
                return false;
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}