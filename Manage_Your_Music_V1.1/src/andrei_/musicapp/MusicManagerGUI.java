/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package andrei_.musicapp;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.*;
import java.util.List;
import java.awt.event.*;
import java.util.Map;

/**
 *
 * @author arets
 */
public class MusicManagerGUI extends JFrame {

    // Fields
    private Button repeatButton;
    private Song lastAddedSong;
    private String lastAddedGenre; 
    private JComboBox<String> genreComboBox;
    private MusicManager musicManager;
    private JTextArea mainDisplayArea;
    private JTextArea rockDisplayArea; // Added rockDisplayArea field
    private JTextArea hipHopDisplayArea;
    private JTextField songNameField;
    private JTextField artistField;

    // Constructor
    public MusicManagerGUI(MusicManager musicManager) {
        this.musicManager = musicManager;
        String[] genres = {"Hip Hop", "Rock"};
        genreComboBox = new JComboBox<>(genres);
        initializeUI();
    }

    // Method to add the last added song
    private void addLastAddedSong(String name, String artist, String genre) {
        lastAddedSong = new SongImpl(name, artist, genre);
        lastAddedGenre = genre;
    }
    
    private void addSongInfo() {
        // Retrieve song information from text fields
        String name = songNameField.getText();
        String artist = artistField.getText();
        String genre = (String) genreComboBox.getSelectedItem(); // Retrieve selected genre from combo box

        // Create a new Song object
        Song newSong = new SongImpl(name, artist, genre);

        // Add the new song to the music manager
        musicManager.addSong(newSong);

        // Create a string representing the song information
        String songInfo = "Song: " + name + "\n" + "Artist: " + artist + "\n" + "Genre: " + genre + "\n";

        // Update the display area with the updated list of songs
        mainDisplayArea.setText(musicManager.displayAllSongs());

        // Append the song information to the display text area
        mainDisplayArea.append(songInfo);

        // Clear the input fields for the next input
        clearFields();
    }

    // UI Initialization Method
    private void initializeUI() {
        setTitle("Music Manager");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 500); // Adjusted size for better display

        JTabbedPane tabbedPane = new JTabbedPane();

        // Create panels for each tab
        JPanel allSongsPanel = createAllSongsPanel();
        JPanel hipHopPanel = createGenrePanel("Hip Hop");
        JPanel rockPanel = createGenrePanel("Rock");

        tabbedPane.addTab("All Songs", allSongsPanel);
        tabbedPane.addTab("Hip Hop", hipHopPanel);
        tabbedPane.addTab("Rock", rockPanel);

        add(tabbedPane);

        // Add exit button at the bottom
        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        add(exitButton, BorderLayout.SOUTH);

        setVisible(true);
    }

private JPanel createAllSongsPanel() {
    JPanel panel = new JPanel(new BorderLayout());

    mainDisplayArea = new JTextArea();
    JScrollPane scrollPane = new JScrollPane(mainDisplayArea);
    panel.add(scrollPane, BorderLayout.CENTER);

    JPanel inputPanel = new JPanel(new GridBagLayout()); // GridBag layout for input fields and buttons
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.anchor = GridBagConstraints.WEST;
    gbc.insets = new Insets(5, 5, 5, 5);

    // Song Name
    JLabel lblSongName = new JLabel("Song Name:");
    inputPanel.add(lblSongName, gbc);
    gbc.gridx++;
    songNameField = new JTextField(20);
    songNameField.setPreferredSize(new Dimension(200, 30));
    inputPanel.add(songNameField, gbc);

    gbc.gridx = 0;
    gbc.gridy++;
    // Artist
    JLabel lblArtist = new JLabel("Artist:");
    inputPanel.add(lblArtist, gbc);
    gbc.gridx++;
    artistField = new JTextField(20); // Changed from JTextArea to JTextField
    artistField.setPreferredSize(new Dimension(200, 30));
    inputPanel.add(artistField, gbc);

    gbc.gridx = 0;
    gbc.gridy++;
    // Genre Combo Box
    JLabel lblGenre = new JLabel("Genre:");
    inputPanel.add(lblGenre, gbc);
    gbc.gridx++;
    inputPanel.add(genreComboBox, gbc);

    gbc.gridx = 0;
    gbc.gridy++;
    // Add, Move, and Delete Buttons
    JButton addButton = new JButton("Add");
    JButton moveButton = new JButton("Move"); // Added Move button
    JButton printButton = new JButton("Print"); // Added Print button
    JButton countAllSongsButton = new JButton("Count All Songs"); // Added Count All Songs button
    JButton deleteButton = new JButton("Delete"); // delete button 
    JButton repeatButton = new JButton("Repeat"); // Added Repeat button
    
    inputPanel.add(deleteButton, gbc);
    gbc.gridx++;
    inputPanel.add(addButton, gbc);
    gbc.gridx++;
    inputPanel.add(moveButton, gbc);
    gbc.gridx++;
    inputPanel.add(printButton, gbc);
    gbc.gridx++;
    inputPanel.add(countAllSongsButton, gbc); // Add Count All Songs button
    gbc.gridx++;
    inputPanel.add(repeatButton, gbc); // Add Repeat button

    
     deleteButton.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
    String songName = songNameField.getText();
    String artist = artistField.getText();
    
    // Check if both song name and artist are provided
    if (!songName.isEmpty() && !artist.isEmpty()) {
        // Delete the song from the music manager
        boolean deleted = musicManager.deleteSong(songName, artist);
        
        // Update the display if the song is deleted successfully
        if (deleted) {
            mainDisplayArea.setText(musicManager.displayAllSongs());
            clearFields();
            JOptionPane.showMessageDialog(null, "Song deleted successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Song not found.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    } else {
        JOptionPane.showMessageDialog(null, "Please enter both song name and artist.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
    });
    
    // Add action listener for the "Add" button
    addButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            addSongInfo(); // Call addSongInfo() method to retrieve song information
        }
    });

    // Add action listener for the "Move" button
    moveButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            moveSongToGenre(); // Call moveSongToGenre() method to move song to selected genre
        }
        });

        // ActionListener for the repeatButton
        repeatButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
        // Prompt the user to select songs for the repeat playlist
        String input = JOptionPane.showInputDialog(null, "Enter songs (separated by commas) for the repeat playlist:");
        if (input != null && !input.isEmpty()) {
            // Split the input string by commas to get individual song names
            String[] songNames = input.split(",");
            
            // Create a new playlist for repeating songs
            Playlist repeatPlaylist = new PlaylistImpl();
            
            // Add each song to the repeat playlist
            for (String songName : songNames) {
                // Find the song by name
                Song song = musicManager.findSongByName(songName.trim());
                if (song != null) {
                    // Add the song to the repeat playlist
                    repeatPlaylist.addSong(song);
                } else {
                    // Display a message if the song is not found
                    JOptionPane.showMessageDialog(null, "Song \"" + songName.trim() + "\" not found.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            
            // Set the repeat playlist as the liked playlist
            musicManager.setLikedPlaylist(repeatPlaylist);
            
            // Display a message confirming the creation of the repeat playlist
            JOptionPane.showMessageDialog(null, "Repeat playlist created successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
        } else {
            // Display a message if no input is provided
            JOptionPane.showMessageDialog(null, "Please enter at least one song for the repeat playlist.", "Error", JOptionPane.ERROR_MESSAGE);
        }
            
        }
        });
    
    
         // ActionListener for the printButton
        printButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
        try {
            StringBuilder playlistContent = new StringBuilder();
            Map<String, List<Song>> genrePlaylists = musicManager.getGenrePlaylists();
            if (genrePlaylists != null) {
                for (String genre : genrePlaylists.keySet()) {
                    playlistContent.append("=== ").append(genre).append(" Playlist ===\n");
                    String genrePlaylist = musicManager.displayGenrePlaylist(genre);
                    if (!genrePlaylist.contains("No playlist found")) {
                        playlistContent.append(genrePlaylist).append("\n");
                    }
                }
                JOptionPane.showMessageDialog(null, playlistContent.toString(), "Playlist Content", JOptionPane.PLAIN_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "No genre playlists found.", "Playlist Content", JOptionPane.PLAIN_MESSAGE);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "An error occurred while retrieving genre playlists.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }   
        });

         countAllSongsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int totalSongsCount = musicManager.countSongs();
                JOptionPane.showMessageDialog(null, "Total Songs: " + totalSongsCount, "All Songs Count", JOptionPane.PLAIN_MESSAGE);
            }
        });
    
     // ActionListener for the repeatButton
    repeatButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            // Call the repeatMethod() to implement repeat functionality
            repeatMethod();
        }
    });
   
    panel.add(inputPanel, BorderLayout.SOUTH);

    return panel;
}


    // Method to handle repeat functionality
    private void repeatMethod() {
        // Get the current liked playlist from the music manager
        Playlist likedPlaylist = musicManager.getLikedPlaylist();

        // Create a new playlist based on the current liked playlist
        Playlist repeatPlaylist = new PlaylistImpl(likedPlaylist.getSongs());

        // Set the repeat flag of the new playlist to true
        repeatPlaylist.setRepeat(true);

        // Set the repeat playlist as the liked playlist
        musicManager.setLikedPlaylist(repeatPlaylist);

        // Provide user feedback indicating that the playlist has been set to repeat
        JOptionPane.showMessageDialog(this, "Playlist set to repeat.", "Repeat Playlist", JOptionPane.INFORMATION_MESSAGE);
    }



    // Method to create panel for a specific genre
    private JPanel createGenrePanel(String genre) {
        JPanel panel = new JPanel(new BorderLayout());

        JTextArea genreDisplayArea;
        if (genre.equals("Hip Hop")) {
            genreDisplayArea = hipHopDisplayArea = new JTextArea(); // Initialize and assign hipHopDisplayArea
        } else if (genre.equals("Rock")) {
            genreDisplayArea = rockDisplayArea = new JTextArea(); // Initialize and assign rockDisplayArea
        } else {
            genreDisplayArea = new JTextArea(); // Create a new JTextArea for other genres
        }

        JScrollPane scrollPane = new JScrollPane(genreDisplayArea);
        panel.add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout()); // Flow layout for search and delete buttons

        JTextField searchField = new JTextField(20);
        JButton searchButton = new JButton("Search");
        JButton deleteButton = new JButton("Delete");
        JButton printButton = new JButton("Print");
        JButton countAllSongsButton = new JButton("Count All Songs"); // Create the button;

        // Add action listeners for search and delete buttons
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String keyword = searchField.getText();
                genreDisplayArea.setText(musicManager.searchSong(keyword, genre));
            }
        });

       deleteButton.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
    String songName = songNameField.getText();
    String artist = artistField.getText();
    
    // Check if both song name and artist are provided
    if (!songName.isEmpty() && !artist.isEmpty()) {
        // Delete the song from the music manager
        boolean deleted = musicManager.deleteSong(songName, artist);
        
        // Update the display if the song is deleted successfully
        if (deleted) {
            mainDisplayArea.setText(musicManager.displayAllSongs());
            clearFields();
            JOptionPane.showMessageDialog(null, "Song deleted successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Song not found.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    } else {
        JOptionPane.showMessageDialog(null, "Please enter both song name and artist.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
    });

    countAllSongsButton.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        // Call countSongs() method from MusicManager and display the count
        int totalSongsCount = musicManager.countSongs();
        JOptionPane.showMessageDialog(null, "Total Songs: " + totalSongsCount, "All Songs Count", JOptionPane.PLAIN_MESSAGE);
        }
    });
        
         // Constructor for initializing GUI components and setting up event listeners
        printButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
        // Ensure musicManager is accessible within this scope
        StringBuilder playlistContent = new StringBuilder();
        // Assuming genrePlaylists is accessible through a getter method in musicManager
        Map<String, List<Song>> genrePlaylists = musicManager.getGenrePlaylists();
        if (genrePlaylists != null) {
            // Iterate through each genre and append its playlist content to the StringBuilder
            for (Map.Entry<String, List<Song>> entry : genrePlaylists.entrySet()) {
                String genre = entry.getKey();
                List<Song> songs = entry.getValue();
                playlistContent.append("=== ").append(genre).append(" Playlist ===\n");
                for (Song song : songs) {
                    playlistContent.append("Song: ").append(song.getName()).append("\n");
                    playlistContent.append("Artist: ").append(song.getArtist()).append("\n");
                    playlistContent.append("Genre: ").append(song.getGenre()).append("\n\n");
                }
            }
            // Display the playlist content in a message dialog
            displayContent(playlistContent.toString());
        } else {
            // Display a message if no genre playlists are found
            JOptionPane.showMessageDialog(null, "No genre playlists found.", "Playlist Content", JOptionPane.PLAIN_MESSAGE);
        }
         }
        });

        // Add labels and input field for search
        buttonPanel.add(new JLabel("Search:"));
        buttonPanel.add(searchField);
        buttonPanel.add(searchButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(printButton); // Add the print button to the button panel

        panel.add(buttonPanel, BorderLayout.SOUTH);

        return panel;
    }

    // Method to display content in a pop-up window
    private void displayContent(String content) {
    JTextArea textArea = new JTextArea(content);
    JScrollPane scrollPane = new JScrollPane(textArea);
    textArea.setLineWrap(true);
    textArea.setWrapStyleWord(true);
    scrollPane.setPreferredSize(new Dimension(400, 300));
    JOptionPane.showMessageDialog(this, scrollPane, "Playlist Content", JOptionPane.PLAIN_MESSAGE);
    }

    // Method to move the last added song to the appropriate genre playlist
    private void moveLastAddedSongToGenrePlaylist() {
        if (lastAddedSong != null && lastAddedGenre != null) {
            // Add the last added song to the appropriate genre playlist
            musicManager.addSong(lastAddedSong);
            
            // Create a string representing the song information
            String songInfo = "Song: " + lastAddedSong.getName() + "\n" + "Artist: " + lastAddedSong.getArtist() + "\n" + "Genre: " + lastAddedGenre + "\n";

            // Append the song information to the appropriate genre panel
            JTextArea genreDisplayArea;
            if (lastAddedGenre.equals("Hip Hop")) {
                genreDisplayArea = hipHopDisplayArea;
            } else if (lastAddedGenre.equals("Rock")) {
                genreDisplayArea = rockDisplayArea;
            } else {
                genreDisplayArea = null; // No other genres currently supported
            }

            if (genreDisplayArea != null) {
                genreDisplayArea.append(songInfo + "\n");
            }
            
            // Clear the last added song
            lastAddedSong = null;
            lastAddedGenre = null;
        }
    }
    
    private int countLikedSongs() {
    return musicManager.countSongs();
    }
    
    // Method to move song to selected genre
    private void moveSongToGenre() {
        // Retrieve song information from text fields
        String name = songNameField.getText();
        String artist = artistField.getText();
        String genre = (String) genreComboBox.getSelectedItem(); // Retrieve selected genre from combo box

        // Check if both the song name and artist are not empty
        if (!name.isEmpty() && !artist.isEmpty()) {
            // Create a new Song object
            Song newSong = new SongImpl(name, artist, genre);

            // Add the new song to the music manager
            musicManager.addSong(newSong);

            // Create a string representing the song information
            String songInfo = "Song: " + name + "\n" + "Artist: " + artist + "\n" + "Genre: " + genre + "\n";

            // Append the song information to the main display area
            mainDisplayArea.append(songInfo + "\n");

            // Append the song information to the appropriate genre panel
            JTextArea genreDisplayArea;
            if (genre.equals("Hip Hop")) {
                genreDisplayArea = hipHopDisplayArea;
            } else if (genre.equals("Rock")) {
                genreDisplayArea = rockDisplayArea;
            } else {
                genreDisplayArea = null; // No other genres currently supported
            }

            if (genreDisplayArea != null) {
                genreDisplayArea.append(songInfo + "\n");
            }

            // Clear the input fields for the next input
            clearFields();
        } else {
            // Display an error message if either the song name or artist is empty
            JOptionPane.showMessageDialog(null, "Please enter both song name and artist.");
        }
    }

    // Helper method to clear input fields
    private void clearFields() {
        songNameField.setText("");
        artistField.setText("");
    }

    // Main method for testing
    public static void main(String[] args) {
        MusicManager musicManager = new MusicManager();
        MusicManagerGUI gui = new MusicManagerGUI(musicManager);
    }
}