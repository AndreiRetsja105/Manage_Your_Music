/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package andrei_.musicapp;

/**
 *
 * @author arets
 */
public class SongImpl implements Song {

    private String name;
    private String artist;
    private String genre;
    private boolean liked; // Add a field to track whether the song is liked

    public SongImpl(String name, String artist, String genre) {
        this.name = name;
        this.artist = artist;
        this.genre = genre;
        this.liked = false; // By default, a song is not liked
    }

    // Getter and setter methods for name, artist, and genre
    
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
    
     // Implement the isLiked() method
    public boolean isLiked() {
        return liked;
    }

    // Implement the setLiked() method
    public void setLiked(boolean liked) {
        this.liked = liked;
    }
}

