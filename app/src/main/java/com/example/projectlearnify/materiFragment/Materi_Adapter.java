package com.example.projectlearnify.materiFragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectlearnify.R;
import com.example.projectlearnify.materiDatabase.Materi;

import java.util.List;

public class Materi_Adapter extends RecyclerView.Adapter<Materi_Adapter.ViewAdapter>{
    private final Context context;
    private List<Materi> listmateri;
    private Dialog dialog;

    public interface Dialog {
        void onClick(int position);
    }

    public void setDialog(Dialog dialog) {
        this.dialog = dialog;
    }
    public Materi_Adapter(Context context, List<Materi> listmateri) {
        this.context = context;
        this.listmateri = listmateri;
    }

    @NonNull
    @Override
    public ViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_materi, parent, false);
        return new ViewAdapter(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewAdapter holder, int position) {
        holder.nomorMateri.setText(listmateri.get(position).noMateri);
        holder.judulMateri.setText(listmateri.get(position).titleMateri);
        holder.judulVideo.setText(listmateri.get(position).titleVideo);
        holder.deskripsiVid.setText(listmateri.get(position).descVideo);
    }

    @Override
    public int getItemCount() {
        return listmateri.size();
    }
    class ViewAdapter extends RecyclerView.ViewHolder {
        TextView nomorMateri, judulMateri, judulVideo, deskripsiVid;
        public ViewAdapter(@NonNull View itemView) {
            super(itemView);
            nomorMateri = itemView.findViewById(R.id.tv_materi);
            judulMateri = itemView.findViewById(R.id.tv_judulMateri);
            judulVideo = itemView.findViewById(R.id.tvJudulVid);
            deskripsiVid = itemView.findViewById(R.id.tvDeskripsiVid);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (dialog!=null){
                        dialog.onClick(getLayoutPosition());
                    }
                }
            });
        }
    }
}

