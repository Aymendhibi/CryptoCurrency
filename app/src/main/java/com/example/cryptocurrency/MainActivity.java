package com.example.cryptocurrency;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavHostController;
import androidx.navigation.fragment.NavHostFragment;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.PopupMenu;

import com.example.cryptocurrency.databinding.ActivityMainBinding;
import com.example.cryptocurrency.fragment.ProfileFragment;
import com.example.cryptocurrency.fragment.WatchListFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentContainerView);
        NavHostController navController = (NavHostController) navHostFragment.getNavController();
        PopupMenu popupMenu = new PopupMenu(this, null);
        popupMenu.inflate(R.menu.bottom_nav_menu);
        binding.bottomBar.setupWithNavController(popupMenu.getMenu(), navController);





    }



}