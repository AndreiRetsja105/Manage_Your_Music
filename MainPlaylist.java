/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package manage_your_music_v1;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author arets
 */
public class MainPlaylist {

    private List<Song> songs;

    public MainPlaylist() {
        this.songs = new ArrayList<>();
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void addSong(Song song) {
        songs.add(song);
    }

   
}
