package com.example.projectlearnify.materiFragment;

//adapter utk row video

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectlearnify.R;
import com.example.projectlearnify.materiDatabase.Materi;

import java.util.List;

public class Materi_Adapter extends RecyclerView.Adapter<Materi_Adapter.materiViewHolder>{
    private final Context context;
    private List<RowMateri_Model> listmateri;
    private Dialog dialog;

    public Materi get(int position) {

        return null;
    }

    public interface Dialog {
        void onClick(int position);
    }

    public void setDialog(Dialog dialog) {
        this.dialog = dialog;
    }
    public Materi_Adapter(Context context, List<RowMateri_Model> listmateri) {
        this.context = context;
        this.listmateri = listmateri;
    }

    @NonNull
    @Override
    public materiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_row_materi, parent, false);
        return new materiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull materiViewHolder holder, int position) {
        RowMateri_Model materi = listmateri.get(position);
        holder.nomorMateri.setText(materi.getTv_materi());
        holder.judulMateri.setText(materi.getTv_judulMateri());
        holder.videoView.setVideoURI(materi.getVideoView());
    }

    @Override
    public int getItemCount() {
        return listmateri.size();
    }

//    class videoViewHolder extends RecyclerView.ViewHolder {
//        TextView judulVideo, deskripsiVid;
//        public videoViewHolder(@NonNull View itemView) {
//            super(itemView);
//            judulVideo = itemView.findViewById(R.id.tvJudulVid);
//            deskripsiVid = itemView.findViewById(R.id.tvDeskripsiVid);
//
//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    if (dialog!=null){
//                        dialog.onClick(getLayoutPosition());
//                    }
//                }
//            });
//        }
//    }

    static class materiViewHolder extends RecyclerView.ViewHolder {
        TextView nomorMateri, judulMateri;
        VideoView videoView;

        public materiViewHolder(@NonNull View itemView) {
            super(itemView);
            nomorMateri = itemView.findViewById(R.id.tv_materi);
            judulMateri = itemView.findViewById(R.id.tv_judulMateri);
            videoView = itemView.findViewById(R.id.videoView);
        }
    }
}

