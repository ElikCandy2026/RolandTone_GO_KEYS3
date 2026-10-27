package com.elikcandy.rolandtone;

import android.content.Context;
import android.content.res.AssetManager;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CsvParser {
    public static List<Tone> parseTones(Context context) {
        List<Tone> tones = new ArrayList<>();
        try {
            InputStream is = context.getAssets().open("Roland_GoKeys3_Tones.csv");
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 6) {
                    String category = parts[0];
                    String number = parts[1];
                    String name = parts[2];
                    int msb = Integer.parseInt(parts[3]);
                    int lsb = Integer.parseInt(parts[4]);
                    int pc = Integer.parseInt(parts[5]);
                    tones.add(new Tone(category, number, name, msb, lsb, pc));
                }
            }
            reader.close();
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tones;
    }
}
