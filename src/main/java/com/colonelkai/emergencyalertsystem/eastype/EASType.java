package com.colonelkai.emergencyalertsystem.eastype;

import java.util.List;

public class EASType {
    String name;
    String permission;
    String sound;
    String shortMessage;

    List<String> longMessages;

    int volume;
    int pitch;
    int soundLength;

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
        this.name = name;
        this.permission = permission;
        this.sound = sound;
        this.shortMessage = shortMessage;
        this.longMessages = longMessages;
        this.volume = volume;
        this.pitch = pitch;
        this.soundLength = soundLength;
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
}
