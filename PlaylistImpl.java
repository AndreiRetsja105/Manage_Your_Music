/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package manage_your_music_v1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;

/**
 *
 * @author arets
 */
public class PlaylistImpl implements Playlist {
    
    private String name;
    private List<Song> likedSongs;
    private Map<String, List<Song>> genrePlaylists;
    private Song lastAddedSong; // Keep track of the last added song

    public PlaylistImpl(String name) {
        this.name = name;
        this.likedSongs = new ArrayList<>();
        this.genrePlaylists = new HashMap<>();
        this.lastAddedSong = null;
    }


    public void createPlaylist(String name) {
        // Implement creation of a new playlist
    }

    public void deletePlaylist(String name) {
        // Implement deletion of a playlist
    }

    public void printPlaylist(String name) {
        // Implement printing of a playlist
    }

    public void repeatPlaylist(String name, boolean repeat) {
        // Implement setting repeat functionality for a playlist
    }

    // Method to add a song to the liked playlist
    public void addSongToLiked(Song song) {
        likedSongs.add(song);
    }

    // Method to add a song to a genre playlist
    public void addSongToGenre(String genre, Song song) {
        if (!genrePlaylists.containsKey(genre)) {
            genrePlaylists.put(genre, new ArrayList<>());
        }
        genrePlaylists.get(genre).add(song);
    }

    // Method to add a song from the liked playlist to a genre playlist
    public void addSongToGenrePlaylist(String genre, Song song, String playlistName) {
        if (likedSongs.contains(song) && genrePlaylists.containsKey(genre)) {
            genrePlaylists.get(genre).add(song);
            likedSongs.remove(song);
        }
    }

    public void searchSong(String title) {
        // Implement searching for a song in playlists
    }

    public void deleteSong(String title) {
        // Implement deleting a song from playlists
    }

    public void moveSong(String title, String playlistName) {
        // Implement moving a song between playlists
    }

    // Getter for liked songs
    public List<Song> getLikedSongs() {
        return likedSongs;
    }

    // Getter for genre playlists
    public Map<String, List<Song>> getGenrePlaylists() {
        return genrePlaylists;
    }
    
    // Method to sort the playlist based on specified criteria (title, artist, etc.)
    public void sortPlaylist(String sortBy) {
        switch (sortBy.toLowerCase()) {
            case "title":
                Collections.sort(likedSongs, Comparator.comparing(Song::getTitle));
                break;
            case "artist":
                Collections.sort(likedSongs, Comparator.comparing(Song::getArtist));
                break;
            // Add other sorting criteria as needed
            default:
                JOptionPane.showMessageDialog(null, "Invalid sorting criteria!");
        }
    }
    
}
