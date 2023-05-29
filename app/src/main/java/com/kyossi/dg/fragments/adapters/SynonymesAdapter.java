package com.kyossi.dg.fragments.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kyossi.dg.R;
import com.kyossi.dg.dao.Definition;
import com.kyossi.dg.dao.entities.Synonyme;

import java.util.List;

public class SynonymesAdapter extends RecyclerView.Adapter<SynonymesAdapter.Holderview> {

    private Context context;
    private List<Synonyme> synonymes;

    public SynonymesAdapter(Context context, List<Synonyme> synonymes) {
        this.context = context;
        this.synonymes = synonymes;
    }

    @NonNull
    @Override
    public Holderview onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.synonyme_item_view, parent, false);
        return new Holderview(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull SynonymesAdapter.Holderview holder, int position) {
        Synonyme synonyme = this.synonymes.get(position);
        holder.word.setText(synonyme.getWord());
    }

    @Override
    public int getItemCount() {
        int result = 0;
        if (!synonymes.isEmpty()) {
            result = synonymes.size();
        }
        return result;
    }

    class Holderview extends RecyclerView.ViewHolder {

        TextView word;


        public Holderview(View itemView) {
            super(itemView);
            word = itemView.findViewById(R.id.word);
        }
    }
}
