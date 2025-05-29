package com.patrones.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.function.Consumer;

import com.patrones.factory.SongFactory;
import com.patrones.playlist.Playlist;
import com.patrones.schemas.Song;
import com.patrones.utils.PropsUtil;

public class PlayListDAO {

    private String url;
    private String user;
    private String password;

    public PlayListDAO() {
        SongFactory.enableFlyweight = true;
        Properties props = new PropsUtil().loadProperties();
        url = props.getProperty("db.url");
        user = props.getProperty("db.user");
        password = props.getProperty("db.password");
    }

    private void connect(Consumer<Connection> callback) {
        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            // System.out.println("Opening connection...");
            callback.accept(conn);
            // System.out.println("Closing connection...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Playlist getPlaylist(String name) {
        Playlist playlist = new Playlist();
        this.connect((conn) -> {
            try {
                PreparedStatement statement = conn.prepareStatement("SELECT * FROM playlists WHERE name=?");
                statement.setString(1, name);
                ResultSet result = statement.executeQuery();

                while (result.next()) {
                    playlist.setName(result.getString("name"));
                    playlist.setDescription(result.getString("description"));
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        return playlist;
    }

    public boolean insertPlaylist(Playlist nw) {
        Playlist exist = this.getPlaylist(nw.getName());

        if (!exist.getName().equals("-"))
            return true;

        this.connect((conn) -> {
            try {
                conn.setAutoCommit(false);
                PreparedStatement statement = conn.prepareStatement("INSERT INTO playlists VALUES(?, ?)");
                statement.setString(1, nw.getName());
                statement.setString(2, nw.getDescription());

                int rows = statement.executeUpdate();

                if (rows == 0) {
                    conn.rollback();
                    throw new Exception("Algo paso con el insert");
                }

                for (Song song : nw.getSongs()) {
                    statement = conn.prepareStatement("INSERT INTO playlistxsong VALUES(?, ?)");
                    statement.setString(1, nw.getName());
                    statement.setString(2, song.getName());

                    rows = statement.executeUpdate();
                    if (rows == 0) {
                        conn.rollback();
                        throw new Exception("Algo paso con el insert");
                    }
                }

                conn.commit();

            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        return true;
    }

    public void fillSongs(Playlist nw) {
        List<Song> songs = new ArrayList<>();
        this.connect((conn) -> {
            try {
                PreparedStatement statement = conn
                        .prepareStatement("SELECT songname FROM playlistxsong WHERE playlistname=?");
                statement.setString(1, nw.getName());

                ResultSet result = statement.executeQuery();

                while (result.next()) {
                    String songname = result.getString("songname");
                    songs.add(SongFactory.CreateItem(songname));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        nw.setSongs(songs);
    }
}
