package com.example.projectlearnify.database;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectlearnify.R;
import com.example.projectlearnify.database.Kontak;
import com.example.projectlearnify.database.SelectListener;

import java.util.List;

class KontakMateriAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private SelectListener listener;


    private final Context context;
    private final List<Kontak> kontaks;

    public KontakMateriAdapter(Context context, List<Kontak> kontaks){
        this.context = context;
        this.kontaks = kontaks;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        Button BtDaftarMapel;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            BtDaftarMapel   = itemView.findViewById(R.id.BtDaftarMapelRecycleView);

        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(this.context)
                .inflate(R.layout.activitykontak, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Kontak k = this.kontaks.get(position);
        ViewHolder viewHolder = (ViewHolder) holder;

        viewHolder.BtDaftarMapel.setText(k.getMapel());

        viewHolder.BtDaftarMapel.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Anda memilih: " + k.getMapel(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public int getItemCount() {
        return this.kontaks.size();



    }
}
