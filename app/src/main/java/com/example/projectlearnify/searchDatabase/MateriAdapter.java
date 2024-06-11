//package com.example.projectlearnify.searchDatabase;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.example.projectlearnify.R;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//public class MateriAdapter extends RecyclerView.Adapter<MateriAdapter.VH> {
//
//    private final Context context;
//    private final String[] judulMateri;
//    private final String[] isiMateri;
//    private final List<String> materiiFiltered;
//    private OnItemClickListener listener;
//
//    public MateriAdapter(Context context, String[] judulMateri, String[] isiMateri) {
//        this.context = context;
//        this.judulMateri = judulMateri;
//        this.isiMateri = isiMateri;
//        this.materiiFiltered = new ArrayList<>(Arrays.asList(judulMateri));
//    }
//
//    public void setOnItemClickListener(OnItemClickListener listener) {
//        this.listener = listener;
//    }
//
//    public interface OnItemClickListener {
//        void onItemClick(int position);
//    }
//
//    public void filter(String keyword) {
//        materiiFiltered.clear();
//
//        if (keyword.isEmpty()) {
//            materiiFiltered.addAll(Arrays.asList(judulMateri));
//        } else {
//            keyword = keyword.toLowerCase();
//            for (int i = 0; i < judulMateri.length; i++) {
//                if (judulMateri[i].toLowerCase().contains(keyword) || isiMateri[i].toLowerCase().contains(keyword)) {
//                    materiiFiltered.add(judulMateri[i]);
//                }
//            }
//        }
//
//        notifyDataSetChanged();
//    }
//
//    @NonNull
//    @Override
//    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View vh = LayoutInflater.from(context).inflate(R.layout.row_materi, parent, false);
//        return new VH(vh);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull VH holder, int position) {
//        holder.tvJudul.setText(materiiFiltered.get(position));
//        holder.tvIsi.setText(isiMateri[position]);
//        holder.bind(position, listener);
//    }
//
//    @Override
//    public int getItemCount() {
//        return materiiFiltered.size();
//    }
//
//    public static class VH extends RecyclerView.ViewHolder {
//        private final TextView tvJudul;
//        private final TextView tvIsi;
//        private final Button btCari;
//
//        public VH(@NonNull View itemView) {
//            super(itemView);
//            tvJudul = itemView.findViewById(R.id.tvJudul);
//            tvIsi = itemView.findViewById(R.id.tvIsi);
//            btCari = itemView.findViewById(R.id.btCari);
//        }
//
//        public void bind(int position, OnItemClickListener listener) {
//            btCari.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if (listener != null) {
//                        listener.onItemClick(position);
//                    }
//                }
//            });
//        }
//    }
//
//}

package com.example.projectlearnify.searchDatabase;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectlearnify.R;

import java.util.ArrayList;
import java.util.List;

public class MateriAdapter extends RecyclerView.Adapter<MateriAdapter.VH> {

    private final Context context;
    private final List<Materi> materiList;
    private List<Materi> materiiFiltered;
    private OnItemClickListener listener;

    public MateriAdapter(Context context, List<Materi> materiList) {
        this.context = context;
        this.materiList = materiList;
        this.materiiFiltered = new ArrayList<>(materiList);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void filter(String keyword) {
        materiiFiltered.clear();

        if (keyword.isEmpty()) {
            materiiFiltered.addAll(materiList);
        } else {
            keyword = keyword.toLowerCase();
            for (Materi materi : materiList) {
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
        Materi materi = materiiFiltered.get(position);
        holder.tvJudul.setText(materi.getJudul());
        holder.tvIsi.setText(materi.getIsi());
        holder.bind(position, listener);
    }

    @Override
    public int getItemCount() {
        return materiiFiltered.size();
    }

    public static class VH extends RecyclerView.ViewHolder {
        private final TextView tvJudul;
        private final TextView tvIsi;
        private final Button btCari;

        public VH(@NonNull View itemView) {
            super(itemView);
            tvJudul = itemView.findViewById(R.id.tvJudul);
            tvIsi = itemView.findViewById(R.id.tvIsi);
            btCari = itemView.findViewById(R.id.btCari);
        }

        public void bind(int position, OnItemClickListener listener) {
            btCari.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        listener.onItemClick(position);
                    }
                }
            });
        }
    }
}
