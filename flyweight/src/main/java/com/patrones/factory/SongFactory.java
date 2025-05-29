package com.patrones.factory;

import java.util.HashMap;
import java.util.Map;

import com.patrones.schemas.Song;

public class SongFactory {

    public static boolean enableFlyweight = true;
    private static final Map<String, Song> PLAY_SONG = new HashMap<>();
    private static Long sequence = 0L;

    public static Song CreateItem(String name) {
        if (enableFlyweight && PLAY_SONG.containsKey(name)) {
            return PLAY_SONG.get(name);
        } else {
            Song createdSong = new Song(++sequence, name);
            PLAY_SONG.put(name, createdSong);
            return createdSong;
        }
    }
}
