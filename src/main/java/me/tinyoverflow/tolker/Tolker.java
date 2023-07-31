package me.tinyoverflow.tolker;

import me.tinyoverflow.tolker.repositories.MessageBag;
import me.tinyoverflow.tolker.serializer.*;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

public class Tolker
{
    private final Map<Class<?>, TypeSerializer<?>> serializerMap = new HashMap<>();
    private final MessageBag messageBag;

    public Tolker(MessageBag messageBag)
    {
        this.messageBag = messageBag;
    }

    public <T> void registerSerializer(Class<T> type, TypeSerializer<? super T> serializer) {
        serializerMap.put(type, serializer);
    }

    public void registerDefaultSerializers() {
        // Built-In Types
        registerSerializer(Boolean.class, new BooleanSerializer());
        registerSerializer(Character.class, new CharSerializer());
        registerSerializer(Double.class, new DoubleSerializer());
        registerSerializer(Float.class, new FloatSerializer());
        registerSerializer(Integer.class, new IntegerSerializer());
        registerSerializer(Long.class, new LongSerializer());
        registerSerializer(String.class, new StringSerializer());
        registerSerializer(TextComponent.class, new ComponentSerializer());

        // Minecraft Types
        registerSerializer(ItemStack.class, new ItemStackSerializer());
        registerSerializer(Location.class, new LocationSerializer());
        registerSerializer(OfflinePlayer.class, new OfflinePlayerSerializer());
        registerSerializer(Player.class, new PlayerSerializer());
        registerSerializer(World.class, new WorldSerializer());
    }

    @Deprecated(forRemoval = true)
    /**
     * @deprecated Use {@code from} instead.
     */
    public MessageBuilder build(String messageKey) {
        return from(messageKey);
    }

    public MessageBuilder from(String messageKey) {
        return new MessageBuilder(serializerMap, messageBag.getMessage(messageKey));
    }
}
