/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package andrei_.musicapp;

/**
 *
 * @author arets
 */
public class MusicApp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        // Create a MusicManager instance to manage playlists and songs
        MusicManager manager = new MusicManager();

        // Create a GUI for the music manager and pass the MusicManager instance
        MusicManagerGUI gui = new MusicManagerGUI(manager);
        
        // Create SongImpl objects and add them using the addSong(Song) method
        manager.addSong(new SongImpl("Song Name", "Artist Name", "Genre"));
        manager.displayPlaylist();

        // Add sample songs to the liked playlist
        manager.addSong(new SongImpl("Song A", "Artist A", "Genre A"));
        manager.addSong(new SongImpl("Song B", "Artist B", "Genre B"));
        manager.addSong(new SongImpl("Song C", "Artist C", "Genre C"));
        // Display the liked playlist
        manager.displayPlaylist();

        // Count the number of songs in the liked playlist
        System.out.println("Number of songs in Liked Playlist: " + manager.countSongs());

        // Add songs to genre playlists
        manager.addSongToGenre(new SongImpl("Song D", "Artist D", "Pop"), "Pop");
        manager.addSongToGenre(new SongImpl("Song E", "Artist E", "Rock"), "Rock");

        // Display genre playlists
        manager.displayGenrePlaylist("Hip Hop");
        manager.displayGenrePlaylist("Rock");

        // Count the number of songs in each genre playlist
        System.out.println("Number of songs in Hip Hop Playlist: " + manager.countSongsInGenrePlaylist("HipHop"));
        System.out.println("Number of songs in Rock Playlist: " + manager.countSongsInGenrePlaylist("Rock"));
    }
}