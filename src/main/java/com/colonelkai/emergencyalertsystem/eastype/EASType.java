package com.colonelkai.emergencyalertsystem.eastype;

import java.util.List;

public class EASType {
    private String name;
    private String permission;
    private String sound;
    private String shortMessage;

    private List<String> longMessages;

    private int volume;
    private int pitch;
    private int soundLength;

    public EASType(
            String name,
            String permission,
            String sound,
            String shortMessage,
            List<String> longMessages,
            int volume,
            int pitch,
            int soundLength
    ) {
        this.setName(name);
        this.setPermission(permission);
        this.setSound(sound);
        this.setShortMessage(shortMessage);
        this.setLongMessages(longMessages);
        this.setVolume(volume);
        this.setPitch(pitch);
        this.setSoundLength(soundLength);
    }

    public String getName() {
        return name;
    }

    public String getPermission() {
        return permission;
    }

    public String getSound() {
        return sound;
    }

    public String getShortMessage() {
        return shortMessage;
    }

    public List<String> getLongMessages() {
        return longMessages;
    }

    public int getVolume() {
        return volume;
    }

    public int getPitch() {
        return pitch;
    }

    public int getSoundLength() {
        return soundLength;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public void setSound(String sound) {
        this.sound = sound;
    }

    public void setShortMessage(String shortMessage) {
        this.shortMessage = shortMessage;
    }

    public void setLongMessages(List<String> longMessages) {
        this.longMessages = longMessages;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public void setPitch(int pitch) {
        this.pitch = pitch;
    }

    public void setSoundLength(int soundLength) {
        this.soundLength = soundLength;
    }
}
