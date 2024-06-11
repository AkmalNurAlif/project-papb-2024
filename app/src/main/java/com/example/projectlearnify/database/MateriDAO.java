package com.example.projectlearnify.database;

import android.content.Context;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

class MateriDAO extends RecyclerView.Adapter<MateriDAO.ViewHolder> {
    private final Context context;
    private final List<Kontak> kontaks;

    public MateriDAO(Context context, List<Kontak> kontaks) {
        this.context = context;
        this.kontaks = kontaks;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // Your ViewHolder implementation
        // For example:
        // TextView textView;

        public ViewHolder(@NonNull android.view.View itemView) {
            super(itemView);
            // Initialize your views
            // For example:
            // textView = itemView.findViewById(R.id.textView);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull android.view.ViewGroup parent, int viewType) {
        // Inflate your item layout and create a ViewHolder
        // For example:
        // View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        // return new ViewHolder(view);
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Bind data to your ViewHolder's views
        // For example:
        // holder.textView.setText(kontaks.get(position).getName());
    }

    @Override
    public int getItemCount() {
        // Return the size of your data list
        return kontaks.size();
    }

    public void addItem(Kontak kontak) {
        // Add a new item to the list
        kontaks.add(kontak);
        // Notify the adapter that an item has been added
        notifyDataSetChanged();
        // Optionally, you can provide feedback to the user
        Toast.makeText(context, "Item added", Toast.LENGTH_SHORT).show();
    }

    public void removeItem(int position) {
        // Remove an item from the list
        kontaks.remove(position);
        // Notify the adapter that an item has been removed
        notifyItemRemoved(position);
        // Optionally, you can provide feedback to the user
        Toast.makeText(context, "Item removed", Toast.LENGTH_SHORT).show();
    }
}
