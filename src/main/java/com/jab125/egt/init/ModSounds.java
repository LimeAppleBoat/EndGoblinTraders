package com.jab125.egt.init;

import com.jab125.egt.EGobT;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.LinkedHashMap;
import java.util.Map;

public class ModSounds {
    private static final Map<SoundEvent, Identifier> SOUND_EVENTS = new LinkedHashMap<>();
    public static final SoundEvent ANNOYED_GRUNT_ECHO = create("annoyed_grunt_echo");
    public static final SoundEvent IDLE_GRUNT_ECHO = create("idle_grunt_echo");

    private static SoundEvent create(String name) {
        Identifier id = new Identifier(EGobT.MODID, name);
        SoundEvent soundEvent = new SoundEvent(id);
        SOUND_EVENTS.put(soundEvent, id);
        return soundEvent;
    }

    public static void registerSounds() {
        SOUND_EVENTS.keySet().forEach(soundEvent -> Registry.register(Registry.SOUND_EVENT, SOUND_EVENTS.get(soundEvent), soundEvent));
    }
}
