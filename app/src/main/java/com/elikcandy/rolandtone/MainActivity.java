package com.elikcandy.rolandtone;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.media.midi.*;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.LinearLayoutManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
    private MidiController midiController;
    private FavoritesManager favoritesManager;
    private List<Tone> allTones;
    private List<Tone> favoriteTones;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

        recyclerView = findViewById(R.id.tone_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new ToneAdapter(favoriteTones, tone -> {
            midiController.sendToneCommand(tone.msb, tone.lsb, tone.pc);
        }));

        findViewById(R.id.btn_connect).setOnClickListener(v -> {
            midiController.openFirstUsbMidiDevice();
            Toast.makeText(this, "Connecting to MIDI device...", Toast.LENGTH_SHORT).show();
        });
    }
}
