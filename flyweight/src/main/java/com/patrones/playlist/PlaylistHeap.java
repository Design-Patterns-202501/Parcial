package com.patrones.playlist;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

import com.patrones.dao.PlayListDAO;

public class PlaylistHeap {

    private class PlaylistWrapper {
        private Integer asked;
        private Playlist playlist;

        PlaylistWrapper(Integer ask, Playlist play) {
            this.asked = ask;
            this.playlist = play;
        }

        @Override
        public boolean equals(Object obj) {
            if ( !(obj instanceof PlaylistWrapper)) return false;
            if (getClass() != obj.getClass()) return false;

            PlaylistWrapper other = (PlaylistWrapper)obj;
            return (playlist.getName().equals(other.playlist.getName()));
        }

        @Override
        public String toString() {
            return "Wrapper={ ask=" + asked + ", playlistName=" + playlist.getName() + "}";
        }
    }

    private final int MAX_LEN = 2;
    private final PlayListDAO playListDAO = new PlayListDAO();

    private final TreeSet<PlaylistWrapper> rbtree = new TreeSet<PlaylistWrapper>((x, y) -> {
        if (x.asked == y.asked) return x.playlist.getName().compareTo(y.playlist.getName());
        return x.asked.compareTo(y.asked);
    });

    private final Map<String, PlaylistWrapper> playlistInRB = new TreeMap<>();
    
    public void addPlaylist(Playlist nw) {
        if (rbtree.size() == MAX_LEN) {
            playListDAO.insertPlaylist(nw);
        } else {
            PlaylistWrapper wrapper = new PlaylistWrapper(0, nw);
            rbtree.add(wrapper);
            playlistInRB.put(nw.getName(), wrapper);
        }

        printReport();
    }

    public boolean existPlaylist(Playlist playlist) {
        return rbtree.contains(new PlaylistWrapper(0, playlist));
    }

    public Playlist askForPlaylist(String name) throws Exception {
        boolean exist = playlistInRB.containsKey(name);
        if (!exist) {
            // Delete least asked and insert
            PlaylistWrapper first = rbtree.first();

            Playlist playlist = playListDAO.getPlaylist(name);

            if (playlist.getName().equals("-")) {
                playlist = null;
                System.gc();
                throw new Exception("Playlist do not exist in the system");
            }

            rbtree.remove(first);
            playlistInRB.remove(first.playlist.getName());


            PlaylistWrapper wrapper = new PlaylistWrapper(0, playlist);
            playlistInRB.put(playlist.getName(), wrapper);
            rbtree.add(wrapper);

            playListDAO.fillSongs(playlist);
            playListDAO.insertPlaylist(first.playlist);

            first = null;
            System.gc();
            return playlist;
        } else {
            PlaylistWrapper wrapper = playlistInRB.get(name);
            rbtree.remove(wrapper);
            wrapper.asked++;
            playlistInRB.put(name, wrapper);
            rbtree.add(wrapper);

            System.gc();
            return wrapper.playlist;
        }
    }

    public void printReport() {
        Iterator<PlaylistWrapper> it =rbtree.iterator();
        System.out.println("Printing RB tree...");
        while (it.hasNext()) {
            System.out.println(it.next());
        }

        Runtime runtime = Runtime.getRuntime();

        System.out.println("MaxMemory > " + (runtime.maxMemory() / 1000000));
        long memoryUsed = runtime.totalMemory() - runtime.freeMemory();
        System.out.println("Memoria Usada => " + (memoryUsed / 1000000));
    }
}
