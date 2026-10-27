package com.elikcandy.rolandtone;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class ToneAdapter extends RecyclerView.Adapter<ToneAdapter.ViewHolder> {
    public interface OnToneClickListener {
        void onToneClick(Tone tone);
    }

    private final List<Tone> tones;
    private final OnToneClickListener listener;

    public ToneAdapter(List<Tone> tones, OnToneClickListener listener) {
        this.tones = tones;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = android.view.LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tone, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Tone tone = tones.get(position);
        holder.name.setText(tone.name + " (" + tone.category + ")");
        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onToneClick(tone);
            }
        });
    }

    @Override
    public int getItemCount() {
        return tones.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;

        ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tone_name);
        }
    }
}
