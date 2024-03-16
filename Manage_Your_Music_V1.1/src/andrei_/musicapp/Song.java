/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package andrei_.musicapp;

/**
 *
 * @author arets
 */
public interface Song {
    String getName();
    void setName(String name);
    String getArtist();
    void setArtist(String artist);
    String getGenre();
    void setGenre(String genre);
    boolean isLiked(); // Add the isLiked() method
    void setLiked(boolean liked); // Add the setLiked() method
}