package com.example.projectlearnify.searchDatabase;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.projectlearnify.Fragment.MateriFragment;
import com.example.projectlearnify.R;

public class MainActivitySearch extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_search);
        replaceFragment(new MateriFragment());

        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new MateriFragment()).commit();
    }

    private void replaceFragment(MateriFragment materiFragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, materiFragment);
        fragmentTransaction.commit();
    }
}

