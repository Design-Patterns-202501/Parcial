package com.patrones;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.patrones.schemas.*;
import com.patrones.factory.SongFactory;
import com.patrones.playlist.Playlist;
import com.patrones.playlist.PlaylistHeap;

public class App {

    private static final String[] songNames = new String[1000];
    private static final String[] playListNames = new String[199990];
    private static final List<Playlist> lists = new ArrayList<>();

    public static void main(String[] args) {

        System.out.println(""
                + "Proceso de creación de listas de reproducción iniciado,\n"
                + "este proceso puede ser muy retrasado debido a la gran cantidad de objetos\n"
                + "que se creará, por favor espere un momento hasta que \n"
                + "sea notificado de que el proceso ha terminado..");

        SongFactory.enableFlyweight = true;
        InicializarArreglos();
        CrearListaDinamica();
        System.out.println("Total Listas > " + lists.size());

        PlaylistHeap heap = new PlaylistHeap();

        for (int i = 0; i < 3; i++) {
            heap.addPlaylist(lists.get(i));
        }
        
        try {
            System.out.println("Asking playlist: "+ lists.get(1).getName());
            Playlist p = heap.askForPlaylist(lists.get(1).getName());
            heap.printReport();


            System.out.println("Asking playlist: "+ lists.get(2).getName());
            p = heap.askForPlaylist(lists.get(2).getName());
            heap.printReport();

            System.out.println("Asking playlist: "+ lists.get(0).getName());
            p = heap.askForPlaylist(lists.get(0).getName());
            for (Song song: p.getSongs()) System.out.println(song);
            heap.printReport();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void CrearListaDinamica() {
        Random random = new Random();
        for (int c = 0; c < playListNames.length; c++) {
            Playlist playList = new Playlist(
                    playListNames[c],
                    "Playlist with name: " + playListNames[c]);
            for (int i = 0; i < 10; i++) {
                int song = random.nextInt(songNames.length);
                playList.addSong(songNames[song]);
            }
            lists.add(playList);
        }
    }

    private static void InicializarArreglos() {
        for (int c = 0; c < songNames.length; c++) {
            songNames[c] = "Song " + (c + 1);
        }

        for (int c = 0; c < playListNames.length; c++) {
            playListNames[c] = "PlayList " + (c + 1);
        }
    }
}
