/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package andrei_.musicapp;

import java.util.List;

/**
 *
 * @author arets
 */
public interface Playlist {
    int size();
    void addSong(Song song);
    boolean removeSong(Song song);
    void displayPlaylist();
    int countSongs();
    Song findSongByName(String songName); // Add this method
    List<Song> getSongs(); // You may already have this method

    public void setRepeat(boolean b);
}
