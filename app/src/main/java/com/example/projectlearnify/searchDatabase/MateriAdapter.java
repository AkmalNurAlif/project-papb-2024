package com.example.projectlearnify.searchDatabase;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectlearnify.R;
import com.example.projectlearnify.searchDatabase.Materi;

import java.util.ArrayList;
import java.util.List;

public class MateriAdapter extends RecyclerView.Adapter<MateriAdapter.VH> {

    private final Context context;
    private final List<Materi> materii;
    private final List<Materi> materiiFiltered;

    public MateriAdapter(Context context, List<Materi> materii) {
        this.context = context;
        this.materii = materii;
        this.materiiFiltered = new ArrayList<>(materii);
    }

    public void filter(String keyword) {
        materiiFiltered.clear();

        if (keyword.isEmpty()) {
            materiiFiltered.addAll(materii);
        } else {
            keyword = keyword.toLowerCase();

            for (Materi materi : materii) {
                if (materi.getJudul().toLowerCase().contains(keyword) || materi.getIsi().toLowerCase().contains(keyword)) {
                    materiiFiltered.add(materi);
                }
            }
        }

        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vh = LayoutInflater.from(context).inflate(R.layout.row_materi, parent, false);
        return new VH(vh);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        Materi m = materiiFiltered.get(position);
        holder.tvJudul.setText(m.getJudul());
        holder.tvIsi.setText(m.getIsi());
    }

    @Override
    public int getItemCount() {
        return materiiFiltered.size();
    }

    public static class VH extends RecyclerView.ViewHolder {

        private final TextView tvJudul;
        private final TextView tvIsi;

        public VH(@NonNull View itemView) {
            super(itemView);
            tvJudul = itemView.findViewById(R.id.tvJudul);
            tvIsi = itemView.findViewById(R.id.tvIsi);
        }
    }
}
