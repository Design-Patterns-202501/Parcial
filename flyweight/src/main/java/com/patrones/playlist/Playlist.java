package com.patrones.playlist;

import java.util.ArrayList;
import java.util.List;

import com.patrones.factory.SongFactory;
import com.patrones.schemas.Song;

public class Playlist {

    private String name;
    private String description;
    private byte[] bytes = new byte[10000];
    
	private List<Song> songs = new ArrayList<>();

    public Playlist() {
        this.name = "-";
    }
    

    public Playlist(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Song> getSongs() {
		return songs;
	}
	public void setSongs(List<Song> songs) {
		this.songs = songs;
	}

    public void addSong(String songname) {
        this.songs.add(SongFactory.CreateItem(songname));
    }
    
    @Override
    public String toString() {
        String out = "\nPlaylist: > " + name;
        for (Song song: songs) {
            out += "\n\t" + song.toString();
        }
        return out;
    }
}
