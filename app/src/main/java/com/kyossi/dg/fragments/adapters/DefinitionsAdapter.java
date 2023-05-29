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

import java.util.List;

public class DefinitionsAdapter extends RecyclerView.Adapter<DefinitionsAdapter.Holderview> {

    private Context context;
    private List<Definition> definitions;

    public DefinitionsAdapter(Context context, List<Definition> definitions) {
        this.context = context;
        this.definitions = definitions;
    }

    @NonNull
    @Override
    public Holderview onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.definition_item_view, parent, false);
        return new Holderview(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull Holderview holder, int position) {
        Definition definition = this.definitions.get(position);
        holder.order.setText(definition.getIdentification()+"");
        holder.definition.setText(definition.getDefinition());
        holder.example.setText(definition.getExample());
    }

    @Override
    public int getItemCount() {
        int result = 0;
        if (!definitions.isEmpty()) {
            result = definitions.size();
        }
        return result;
    }

    class Holderview extends RecyclerView.ViewHolder {

        TextView order;
        TextView definition;
        TextView example;


        public Holderview(View itemView) {
            super(itemView);
            order = itemView.findViewById(R.id.word);
            definition = itemView.findViewById(R.id.definition);
            example = itemView.findViewById(R.id.example);
        }
    }
}
