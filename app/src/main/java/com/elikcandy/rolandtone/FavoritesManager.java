package com.elikcandy.rolandtone;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.HashSet;
import java.util.Set;

public class FavoritesManager {
    private static final String PREF_NAME = "FavoritesPrefs";
    private static final String KEY_FAVORITES = "favorite_tones";
    private SharedPreferences prefs;

    public FavoritesManager(Context context) {
        prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public void addFavorite(String toneNumber) {
        Set<String> favorites = new HashSet<>(getFavorites());
        favorites.add(toneNumber);
        prefs.edit().putStringSet(KEY_FAVORITES, favorites).apply();
    }

    public void removeFavorite(String toneNumber) {
        Set<String> favorites = new HashSet<>(getFavorites());
        favorites.remove(toneNumber);
        prefs.edit().putStringSet(KEY_FAVORITES, favorites).apply();
    }

    public Set<String> getFavorites() {
        Set<String> favorites = prefs.getStringSet(KEY_FAVORITES, new HashSet<>());
        return favorites;
    }
}
