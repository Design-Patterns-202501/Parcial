package com.patrones.schemas;

public class Song {

    private Long id;
    private String name;
    private byte[] songBytes = new byte[1000000];

	public Song(Long id, String name) {
        this.id = id;
        this.name = name;
    }

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public byte[] getSongBytes() {
		return songBytes;
	}
	public void setSongBytes(byte[] songBytes) {
		this.songBytes = songBytes;
	}

    @Override
    public String toString() {
        return "Song{ " + name + " id= " + id + "}";
    }
}
