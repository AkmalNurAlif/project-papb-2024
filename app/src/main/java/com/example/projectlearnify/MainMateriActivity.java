package com.example.projectlearnify;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectlearnify.database.AppDatabase;
import com.example.projectlearnify.materiDatabase.Materi;
import com.example.projectlearnify.materiDatabase.MateriDatabase;

import java.util.ArrayList;
import java.util.List;


public class MainMateriActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private Button btAdd;
    private MateriDatabase materiDatabase;
    private ListMateriAdapter listMateriAdapter;
    private static List<Materi> listmateri = new ArrayList<>();
    private AlertDialog.Builder dialog;
    private AlertDialog.Builder delConfirmdialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_materi);

        recyclerView = findViewById(R.id.rvMateri);
        btAdd = findViewById(R.id.btAdd);

        materiDatabase = MateriDatabase.getInstance(getApplicationContext());
        listmateri.clear();
        listmateri.addAll(materiDatabase.materiDao().getAll());
        //listAdapter.notifyDataSetChanged();

        listMateriAdapter = new ListMateriAdapter(getApplicationContext(), listmateri);
        listMateriAdapter.setDialog(new ListMateriAdapter.Dialog(){
            @Override
            public void onClick(int position) {
                showOptionDialog(position);
            }
        });

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(
                getApplicationContext(), RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(listMateriAdapter);

        btAdd.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainMateriActivity.this, AddMateriActivity.class));
                //MainActivity.listmateri.notifyAll();
            }
        });

        //        RecyclerView recyclerView = findViewById(R.id.rvMateri);
        //        List<ItemModel> barisan = new ArrayList<>();
        //
        //        ListAdapter listAdapter = new ListAdapter(this, barisan);
        //        recyclerView.setAdapter(listAdapter);
        //
        //        RecyclerView.LayoutManager lm = new LinearLayoutManager(MainActivity.this);
        //        recyclerView.setLayoutManager(lm);
        //
        //        //types: 0=materi, 1=video, 2=header
        //        HeaderModel header1 = new HeaderModel((R.drawable.bahasaindo), "Bahasa Indonesia");
        //        barisan.add(new ItemModel(2, header1));
        //
        //        RowMateriModel materi1 = new RowMateriModel("Materi 1", "Teks prosedur");
        //        barisan.add(new ItemModel(0, materi1));
        //
        //        RowVideoModel video1 = new RowVideoModel("Video teks prosedur", "Video pembelajaran materi teks prosedur");
        //        barisan.add(new ItemModel(1, video1));
        //
        //        RowMateriModel materi2 = new RowMateriModel("Materi 2", "Teks Eksposisi");
        //        barisan.add(new ItemModel(0, materi2));
        //
        //        RowVideoModel video2 = new RowVideoModel("Video teks eksposisi", "Video pembelajaran materi teks eksposisi");
        //        barisan.add(new ItemModel(1, video2));
    }


    @Override
    protected void onStart() {
        super.onStart();
        listmateri.clear();
        listmateri.addAll(materiDatabase.materiDao().getAll());
        //RefreshList();

        //listAdapter.notifyDataSetChanged();
    }

    //    private void confirmOptionDialog(final int position) {
    //        final CharSequence[] dialogItem = {"Ya", "Tidak"};
    //        if (dialog == null) {
    //            dialog = new AlertDialog.Builder(MainActivity.this);
    //        }
    //
    //        delConfirmdialog.setItems(dialogItem, new DialogInterface.OnClickListener() {
    //            @Override
    //            public void onClick(DialogInterface delConfirmdialog, int which) {
    //                switch(which){
    //                    case 0:
    //                        Materi materi = listmateri.get(position);
    //                        database.materiDao().delete(materi);
    //                        onStart();
    //                        Toast.makeText(getApplicationContext(), "Data berhasil dihapus", Toast.LENGTH_SHORT).show();
    //                        break;
    //                    case 1:
    //                        Toast.makeText(getApplicationContext(), "Batal menghapus data", Toast.LENGTH_SHORT).show();
    //                        break;
    //                }
    //
    //            }
    //        });
    //        delConfirmdialog.show();
    //    }
    private void showOptionDialog(final int position){
        final CharSequence[] dialogItem = {"Hapus", "Edit"};
        if (dialog == null){
            dialog = new AlertDialog.Builder(MainMateriActivity.this);
        }

        dialog.setItems(dialogItem, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                switch(i){
                    case 0:
                        Materi materi = listmateri.get(position);
                        materiDatabase.materiDao().delete(materi);
                        //finish();
                        //onStart();
                        break;
                    case 1:
                        //Toast.makeText(getApplicationContext(), "Batal menghapus data", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
        dialog.show();
    }

    //    private void deleteMateri(int position){
    //        Materi materi = listmateri.get(position);
    //        database.materiDao().delete(materi);
    //        onStart();
    //    }

    //    private void refreshList(){
    //        listmateri.clear();
    //        listmateri.addAll(database.materiDao().getAll());
    //        listAdapter.notifyDataSetChanged();
    //    }
}

