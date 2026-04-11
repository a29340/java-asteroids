package com.a29340.utils;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;

/**
 * Sound utility class for playing sound effects using Java's standard audio APIs.
 * No external dependencies required.
 */
public class Sound {
    private static final String SOUND_PATH = "sounds/";
    
    // Event-based constants to identify which sound effect to play
    public enum EventType {
        ASTEROID_EXPLOSION,  // Asteroid destroyed by beam or collision
        SHIP_HIT,            // Ship collides with asteroid
        BEAM_FIRE,           // Laser shot fired
        GAME_START,          // Game starts
        GAME_OVER            // Game ends
    }

    private static boolean isMuted = false;

    /**
     * Plays a sound effect for the specified event.
     * @param eventType The type of event to play a sound for
     */
    public static void playSound(EventType eventType) {
        if (isMuted) return;
        
        String fileName = getFileName(eventType);
        File soundFile = new File(Sound.class.getClassLoader().getResource(SOUND_PATH + fileName).getPath());
        
        if (!soundFile.exists()) {
            System.out.println("Sound file not found: " + soundFile.getAbsolutePath());
            return;
        }

        try (AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundFile)) {
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            System.err.println("Error playing sound: " + e.getMessage());
        }
    }

    /**
     * Gets the file name for a specific event type.
     * @param eventType The event type
     * @return File name with extension
     */
    private static String getFileName(EventType eventType) {
        switch (eventType) {
            case ASTEROID_EXPLOSION: return "asteroid-explosion.wav";
            case SHIP_HIT: return "ship-hit.wav";
            case BEAM_FIRE: return "beam-fire.wav";
            case GAME_START: return "game-start.wav";
            case GAME_OVER: return "game-over.wav";
            default: return "asteroid-explosion.wav";
        }
    }

    /**
     * Sets the mute state.
     * @param muted True to mute all sounds, false to enable
     */
    public static void setMuted(boolean muted) {
        isMuted = muted;
    }

    /**
     * Checks if sound output is currently muted.
     * @return True if muted, false otherwise
     */
    public static boolean isMuted() {
        return isMuted;
    }

    /**
     * Plays a custom sound file.
     * @param fileName The name of the WAV file in the resources/images directory
     */
    public static void playSound(String fileName) {
        if (isMuted) return;
        
        File soundFile = new File(SOUND_PATH + fileName);
        
        if (!soundFile.exists()) {
            System.out.println("Sound file not found: " + soundFile.getAbsolutePath());
            return;
        }

        try (AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundFile)) {
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            System.err.println("Error playing sound: " + e.getMessage());
        }
    }

    /**
     * Stops all currently playing sounds.
     */
    public static void stopAllSounds() {
        // Note: Java's Clip API doesn't provide a direct way to get all clips
        // This is a limitation of the standard audio system
    }
}
