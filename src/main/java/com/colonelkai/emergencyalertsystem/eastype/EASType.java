package com.colonelkai.emergencyalertsystem.eastype;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class EASType {
    private final String name;
    private final String permission;
    private final String sound;
    private final String shortMessage;

    private final List<String> longMessages = new LinkedList<>();

    private final int volume;
    private final int pitch;
    private final int soundLength;

    public EASType(String name,
                   String permission,
                   String sound,
                   String shortMessage,
                   Collection<String> longMessages,
                   int volume,
                   int pitch,
                   int soundLength) {
        this.name = name;
        this.permission = permission;
        this.sound = sound;
        this.shortMessage = shortMessage;
        this.longMessages.addAll(longMessages);
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
