package com.elikcandy.rolandtone;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.LinearLayoutManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class FavoritesActivity extends AppCompatActivity {
    private MidiController midiController;
    private FavoritesManager favoritesManager;
    private List<Tone> allTones;
    private List<Tone> favoriteTones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);

        midiController = new MidiController(this);
        favoritesManager = new FavoritesManager(this);

        allTones = CsvParser.parseTones(this);
        favoriteTones = new ArrayList<>();

        Set<String> favoriteNumbers = favoritesManager.getFavorites();
        for (Tone tone : allTones) {
            if (favoriteNumbers.contains(tone.number)) {
                favoriteTones.add(tone);
            }
        }

        RecyclerView recyclerView = findViewById(R.id.favorite_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new ToneAdapter(favoriteTones, tone -> {
            midiController.sendToneCommand(tone.msb, tone.lsb, tone.pc);
        }));
    }
}
