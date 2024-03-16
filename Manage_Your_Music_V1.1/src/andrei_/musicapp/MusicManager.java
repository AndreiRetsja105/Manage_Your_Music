/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package andrei_.musicapp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Manages music playlists and songs.
 * Implements functionality for adding, removing, and displaying songs and playlists.
 * Counts songs in different playlists and provides methods for searching and moving songs.
 * Also provides methods to display playlists and their counts.
 * @author arets
 */
public class MusicManager {
    private List<Song> allSongs;
    private List<Song> likedSongs;
    private Playlist likedPlaylist;
    private Map<String, List<Song>> genrePlaylists;
    private Map<String, Integer> genreCounts;
    private MusicManager musicManager;
    private Playlist popGenrePlaylist;
    private Playlist rockGenrePlaylist;
    private List<Song> songs; // Define songs as a member variable
    
    
    public MusicManager() {
        this.songs = new ArrayList<>(); // Initialize songs as an ArrayList
        allSongs = new ArrayList<>();
        likedSongs = new ArrayList<>();
        likedPlaylist = new PlaylistImpl(new ArrayList<>()); // Initialize likedPlaylist with an empty list
        genrePlaylists = new HashMap<>();
        genreCounts = new HashMap<>();
    }

    /**
     * Sets the list of all songs.
     * @param allSongs The list of all songs to be set.
     */
    public void setAllSongs(List<Song> allSongs) {
        this.allSongs = allSongs;
    }

    /**
     * Updates genre counts when songs are added or removed.
     */
    private void updateGenreCounts() {
        // Update count for Liked playlist
        genreCounts.put("Liked", likedPlaylist.size());

        // Update count for each genre playlist
        for (String genre : genrePlaylists.keySet()) {
            genreCounts.put(genre, genrePlaylists.get(genre).size());
        }
    }

    public void setLikedPlaylist(Playlist playlist) {
    this.likedPlaylist = playlist;
    }
    
    
    /**
     * Adds a song to the list of all songs.
     * @param song The song to be added.
     */
    public void addSong(Song song) {
        allSongs.add(song);
    }

    /**
     * Counts the total number of songs in two specified genre playlists.
     * @param genre1 The first genre.
     * @param genre2 The second genre.
     * @return The total number of songs in both playlists.
     */
    public int countSongsInTwoGenrePlaylists(String genre1, String genre2) {
        // Retrieve the playlists for the specified genres
        List<Song> playlist1 = genrePlaylists.getOrDefault(genre1, new ArrayList<>());
        List<Song> playlist2 = genrePlaylists.getOrDefault(genre2, new ArrayList<>());

        // Calculate the total number of songs in both playlists
        int totalSongs = playlist1.size() + playlist2.size();
        return totalSongs;
    }

    /**
     * Removes a song from the liked playlist and updates genre counts.
     * @param song The song to be removed.
     * @return True if the song is successfully removed, false otherwise.
     */

    public boolean removeSong(Song song) {
        // Other remove song logic...
        boolean removed = likedPlaylist.removeSong(song);
        if (removed) {
            updateGenreCounts(); // Update genre counts after removing the song
        }
        return removed;
    }

    /**
     * Displays the list of songs in the liked playlist.
     */

    public void displayPlaylist() {
        for (Song song : likedSongs) {
            System.out.println(song.getName() + " - " + song.getArtist());
        }
    }

    /**
     * Displays all songs in the liked playlist.
     * @return A string representation of all songs in the liked playlist.
     */
    public String displayAllSongs() {
        StringBuilder result = new StringBuilder();
        for (Song song : likedPlaylist.getSongs()) {
            result.append(song.getName()).append(" - ").append(song.getArtist()).append("\n");
        }
        return result.toString();
    }

    /**
     * Searches for a song by keyword in a specified genre playlist.
     * @param keyword The keyword to search for.
     * @param genre The genre playlist to search in.
     * @return A string representation of the found songs matching the keyword.
     */
    public String searchSong(String keyword, String genre) {
        List<Song> genreSongs = genrePlaylists.getOrDefault(genre, new ArrayList<>());
        StringBuilder result = new StringBuilder();
        for (Song song : genreSongs) {
            if (song.getName().equalsIgnoreCase(keyword) || song.getArtist().equalsIgnoreCase(keyword)) {
                result.append(song.getName()).append(" - ").append(song.getArtist()).append("\n");
            }
        }
        return result.toString();
    }

    /**
     * Moves a song from the liked playlist to a specified genre playlist.
     * @param songName The name of the song to be moved.
     * @param genre The target genre playlist.
     */
    public void moveSongToGenre(String songName, String genre) {
        // Find the song in the liked songs list
        Song song = findSongByName(songName);
        if (song != null) {
            // Remove the song from the liked songs list
            likedSongs.remove(song);

            // Add the song to the genre specified
            addSongToGenre(song, genre);

            // Display the updated playlists
            displayPlaylist();
            displayGenrePlaylist(genre);
        }
    }

    /**
     * Gets the map of genre playlists.
     * @return The map of genre playlists.
     */
    public Map<String, List<Song>> getGenrePlaylists() {
        return genrePlaylists;
    }

    /**
     * Deletes a song from a specified genre playlist.
     * @param keyword The keyword to search for the song.
     * @param genre The genre playlist from which the song will be deleted.
     */
    
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

    /**
     * Finds a song by name in all playlists.
     * @param name The name of the song to find.
     * @return The song object if found, null otherwise.
     */

    public Song findSongByName(String name) {
        // Iterate over all songs in all playlists and find the song by name
        for (List<Song> playlist : genrePlaylists.values()) {
            for (Song song : playlist) {
                if (song.getName().equalsIgnoreCase(name)) {
                    return song; // Return the song if found
                }
            }
        }
        return null; // Return null if the song is not found
    }

    /**
     * Counts the total number of songs.
     * @return The total number of songs.
     */

    public int countSongs() {
        return allSongs.size();
    }

    /**
     * Gets the list of songs in the liked playlist.
     * @return The list of songs in the liked playlist.
     */
 
    public List<Song> getSongs() {
        return likedSongs; // Return the list of liked songs
    }

    /**
     * Adds a song from the liked playlist to a specified genre playlist.
     * @param songName The name of the song to be added.
     * @param genre The target genre playlist.
     */
    public void addSongToGenreFromLiked(String songName, String genre) {
        Song song = likedPlaylist.findSongByName(songName);
        if (song != null) {
            likedPlaylist.removeSong(song);
            if (genre.equalsIgnoreCase("Pop")) {
                popGenrePlaylist.addSong(song);
            } else if (genre.equalsIgnoreCase("Rock")) {
                rockGenrePlaylist.addSong(song);
            }
        }
    }

    /**
     * Updates the counts for each playlist.
     */
    private void updateCount() {
        likedCount = likedPlaylist.size(); // Update likedCount with the size of liked playlist
        // Other count updates...
    }

    /**
     * Prints the counts for each playlist.
     */
    public void printPlaylistCounts() {
        // Print the counts for each playlist
        for (String genre : genreCounts.keySet()) {
            System.out.println("Number of songs in " + genre + " Playlist: " + genreCounts.get(genre));
        }
    }

    /**
     * Adds a song to a specified genre playlist.
     * @param song The song to be added.
     * @param genre The target genre playlist.
     */
    public void addSongToGenre(Song song, String genre) {
        // Add the song to the appropriate genre playlist
        if (genrePlaylists.containsKey(genre)) {
            List<Song> playlist = genrePlaylists.get(genre);
            playlist.add(song);
            genrePlaylists.put(genre, playlist);
            // Update the count after adding the song
            updateCount();
        } else {
            // Handle the case where the genre playlist doesn't exist
            System.out.println("Genre not found: " + genre);
        }
    }

    /**
     * Displays the playlist for a specified genre.
     * @param genre The genre playlist to display.
     * @return A string representation of the specified genre playlist.
     */
    public String displayGenrePlaylist(String genre) {
        // Retrieve the playlist for the specified genre
        List<Song> playlist = genrePlaylists.get(genre);

        // Check if the playlist is null
        if (playlist == null || playlist.isEmpty()) {
            return "No playlist found for genre: " + genre;
        }

        // Create a StringBuilder to build the playlist content
        StringBuilder playlistContent = new StringBuilder();

        // Append each song's information to the playlist content
        for (Song song : playlist) {
            playlistContent.append("Song: ").append(song.getName()).append("\n");
            playlistContent.append("Artist: ").append(song.getArtist()).append("\n");
            playlistContent.append("Genre: ").append(song.getGenre()).append("\n\n");
        }

        // Return the playlist content as a String
        return playlistContent.toString();
    }

    
    public Playlist getLikedPlaylist() {
    return likedPlaylist; // Return the liked playlist stored in the music manager
    }
    
    
    private int likedCount;

    /**
     * Calculates the size of the liked playlist.
     * @return The size of the liked playlist.
     */
   
    public int size() {
        // Implement logic to calculate the size of the playlist
        return likedSongs.size(); // Assuming likedSongs is the list of songs in the playlist
    }

    /**
     * Counts the number of songs in a specified genre playlist.
     * @param genre The genre playlist to count.
     * @return The number of songs in the specified genre playlist.
     */
    public int countSongsInGenrePlaylist(String genre) {
        List<Song> playlist = genrePlaylists.getOrDefault(genre, new ArrayList<>());
        return playlist.size();
    }
}

