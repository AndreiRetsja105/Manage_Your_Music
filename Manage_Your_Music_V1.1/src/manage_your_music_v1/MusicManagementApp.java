/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package manage_your_music_v1;

/**
 *
 * @author arets
 */
public class MusicManagementApp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        MusicManagementAppGUI gui = new MusicManagementAppGUI();
            gui.setVisible(true);
        
            
        
         // Creating a playlist
        Playlist likedPlaylist = new PlaylistImpl("Liked Songs");

        // Adding songs to the liked playlist
        likedPlaylist.addSongToLiked(new SongImpl("Song 1", "Artist 1", "Album 1", "Rock"));
        likedPlaylist.addSongToLiked(new SongImpl("Song 2", "Artist 2", "Album 2", "Pop"));
        
        
        // Populating genre playlists based on liked songs
        for (Song song : likedPlaylist.getLikedSongs()) {
            likedPlaylist.addSongToGenre(song.getGenre(), song);
        }

        // Adding a song from the liked playlist to a genre playlist
        likedPlaylist.addSongToGenrePlaylist("Rock", new SongImpl("Song 1", "Artist 1", "Album 1", "Rock"), "Liked Songs");

        // Printing liked playlist
        likedPlaylist.printPlaylist("Liked Songs");

        // Printing genre playlists
        for (String genre : likedPlaylist.getGenrePlaylists().keySet()) {
            likedPlaylist.printPlaylist(genre + " Playlist");
        }
    }
}
