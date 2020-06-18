package com.example.masterfruits;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Result extends RecyclerView.Adapter<Result.MyViewHolder> {
    Context context;
    List<Pair<Drawable[], String>> data;
    public Result(Context gameActivity, List<Pair<Drawable[], String>> history) {
        context = gameActivity;
        data = history;
    }

    @NonNull
    @Override
    public Result.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.result_display, parent, false);
        MyViewHolder VH = new MyViewHolder(v);
        return VH;
    }

    @Override
    public void onBindViewHolder(@NonNull Result.MyViewHolder holder, int position) {
        holder.t.setText(data.get(position).second );
        for (int i = 0; i < 4; i++){
            holder.slots[i].setImageBitmap(((BitmapDrawable)(data.get(position).first[i])).getBitmap());
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView[] slots ;
        TextView t ;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            //Récup des 4 ref des img a modifier
            slots = new ImageView[4];
            for (int i = 1; i < 5; i++){
                String name = "slot"+i;
                int id = context.getResources().getIdentifier(name, "id", context.getPackageName());
                //ImageView slot = findViewById(id);
                slots[i-1] = itemView.findViewById(id);
            }
            //Récup du TextView a modifier
            t = itemView.findViewById(R.id.sendResult);
        }
    }
}
