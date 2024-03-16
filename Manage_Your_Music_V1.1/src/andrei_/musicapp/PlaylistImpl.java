/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package andrei_.musicapp;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author arets
 */
public class PlaylistImpl implements Playlist {

    private List<Song> songs;
    private boolean repeat;
    private Playlist repeatPlaylist;

    // Constructor with a list of songs parameter
    public PlaylistImpl(List<Song> songs) {
        this(songs, false);
    }

    // Another constructor with songs and repeat flag parameters
    public PlaylistImpl(List<Song> songs, boolean repeat) {
        this.songs = songs;
        this.repeat = repeat; // Set the repeat flag
    }
    
    // Default constructor
    public PlaylistImpl() {
        this(new ArrayList<>(), false); // Initialize songs with an empty list
    }

    @Override
    public int size() {
        return songs.size();
    }

    @Override
    public void addSong(Song song) {
        songs.add(song);
    }

    @Override
    public boolean removeSong(Song song) {
        return songs.remove(song);
    }

    @Override
    public void displayPlaylist() {
        for (Song song : songs) {
            System.out.println(song.getName() + " - " + song.getArtist());
        }
    }

    @Override
    public int countSongs() {
        return songs.size();
    }

    @Override
    public List<Song> getSongs() {
        return songs;
    }

    public void setRepeat(boolean repeat) {
        this.repeat = repeat;
    }

    public boolean isRepeat() {
        return repeat;
    }

    @Override
    public Song findSongByName(String songName) {
        for (Song song : songs) {
            if (song.getName().equalsIgnoreCase(songName)) {
                return song;
            }
        }
        return null; // Song not found
    }

    public boolean deleteSong(String songName, String artist) {
        // Iterate through the list of songs
        for (Iterator<Song> iterator = songs.iterator(); iterator.hasNext();) {
            Song song = iterator.next();
            // Check if the song matches both the name and artist
            if (song.getName().equalsIgnoreCase(songName) && song.getArtist().equalsIgnoreCase(artist)) {
                // Remove the song from the list
                iterator.remove();
                return true; // Song successfully deleted
            }
        }
        return false; // Song not found
    }
}

