package com.example.projectlearnify;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectlearnify.materiDatabase.Materi;

import java.util.List;

public class ListMateriAdapter extends RecyclerView.Adapter<ListMateriAdapter.ViewAdapter>{
    private final Context context;
    private List<Materi> listmateri;
    private Dialog dialog;

    public interface Dialog {
        void onClick(int position);
    }

    public void setDialog(Dialog dialog) {
        this.dialog = dialog;
    }
    public ListMateriAdapter(Context context, List<Materi> listmateri) {
        this.context = context;
        this.listmateri = listmateri;
    }

    @NonNull
    @Override
    public ViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        //types: 0=materi, 1=video, 2=header

//        if(viewType == 0){
//            return new RowMateriViewHolder(
//                    LayoutInflater.from(parent.getContext()).inflate(
//                            R.layout.row_materi, parent, false)
//            );
//        } else if(viewType == 1){
//            return new RowVideoViewHolder(
//                    LayoutInflater.from(parent.getContext()).inflate(
//                            R.layout.row_video, parent, false)
//            );
//        } else {
//            return new MapelHeaderViewHolder(
//                    LayoutInflater.from(parent.getContext()).inflate(
//                            R.layout.mapel_header, parent, false)
//            );
//        }

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_row_materi, parent, false);
        return new ViewAdapter(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewAdapter holder, int position) {
        holder.nomorMateri.setText(listmateri.get(position).noMateri);
        holder.judulMateri.setText(listmateri.get(position).titleMateri);
//        holder.judulVideo.setText(listmateri.get(position).titleVideo);
//        holder.deskripsiVid.setText(listmateri.get(position).descVideo);

//        if (getItemViewType(position) == 0){
//            RowMateriModel materi = (RowMateriModel) item.get(position).getObject();
//            ((RowMateriViewHolder) holder).setRowMateri(materi);
//        } else if (getItemViewType(position) == 1){
//            RowVideoModel video = (RowVideoModel) item.get(position).getObject();
//            ((RowVideoViewHolder) holder).setRowVideo(video);
//        }
//        else {
//            HeaderModel header = (HeaderModel) item.get(position).getObject();
//            ((MapelHeaderViewHolder) holder).setMapelHeader(header);
//        }
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
//            judulVideo = itemView.findViewById(R.id.tvJudulVid);
//            deskripsiVid = itemView.findViewById(R.id.tvDeskripsiVid);

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
//    @Override
//    public int getItemViewType(int position) {
//        return item.get(position).getType();
//    }
//
//    static class MapelHeaderViewHolder extends RecyclerView.ViewHolder{
//        private ImageView ivHead;
//        private TextView tvHead;
//        public MapelHeaderViewHolder(@NonNull View itemView) {
//            super(itemView);
//            ivHead=itemView.findViewById(R.id.ivHead);
//            tvHead=itemView.findViewById(R.id.tvHead);
//        }
////        void setMapelHeader(HeaderModel header){
////            ivHead.setImageResource(header.getGambarMapel());
////            tvHead.setText(header.getNamaMapel());
////        }
//    }
//    static class RowMateriViewHolder extends  RecyclerView.ViewHolder{
//        private TextView tv_materi, tv_judulMateri;
//        private Button bt_modul, btcek;
//        RowMateriViewHolder(@NonNull View itemView) {
//            super(itemView);
//            tv_materi = itemView.findViewById(R.id.tv_materi);
//            tv_judulMateri = itemView.findViewById(R.id.tv_judulMateri);
//            bt_modul = itemView.findViewById(R.id.bt_modul);
//            btcek = itemView.findViewById(R.id.btcek);
//
//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if(dialog != u)
//                }
//            });
//        }
////        void setRowMateri(RowMateriModel materi){
////            tv_materi.setText(materi.getNomorMateri());
////            tv_judulMateri.setText(materi.getJudulMateri());
////        }
//    }
//
//    static class RowVideoViewHolder extends RecyclerView.ViewHolder{
//        private TextView tvJudulVid, tvDeskripsiVid;
//        private Button btMulai;
//        public RowVideoViewHolder(@NonNull View itemView) {
//            super(itemView);
//            tvJudulVid=itemView.findViewById(R.id.tvJudulVid);
//            tvDeskripsiVid=itemView.findViewById(R.id.tvDeskripsiVid);
//            btMulai=itemView.findViewById(R.id.btMulai);
//        }
////        void setRowVideo(RowVideoModel video){
////            tvJudulVid.setText(video.getJudulVideo());
////            tvDeskripsiVid.setText(video.getDeskripsiVid());
////        }
//    }

}
