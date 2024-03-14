/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package manage_your_music_v1;

import java.util.List;
import java.util.Map;

/**
 *
 * @author arets
 */
public interface Playlist {
    
    void sortPlaylist(String sortBy);
    void createPlaylist(String name);
    void deletePlaylist(String name);
    void printPlaylist(String name);
    void repeatPlaylist(String name, boolean repeat);
    void addSongToLiked(Song song);
    void addSongToGenre(String genre, Song song);
    void addSongToGenrePlaylist(String genre, Song song, String playlistName);
    void searchSong(String title);
    void deleteSong(String title);
    void moveSong(String title, String playlistName);
    List<Song> getLikedSongs(); // Define method to get liked songs
    Map<String, List<Song>> getGenrePlaylists(); // Define method to get genre playlists
    
}
