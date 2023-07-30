package me.tinyoverflow.tolk;

import me.tinyoverflow.tolk.repositories.MessageBag;
import me.tinyoverflow.tolk.serializer.*;
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

        // Minecraft Types
        registerSerializer(ItemStack.class, new ItemStackSerializer());
        registerSerializer(Location.class, new LocationSerializer());
        registerSerializer(OfflinePlayer.class, new OfflinePlayerSerializer());
        registerSerializer(Player.class, new PlayerSerializer());
        registerSerializer(World.class, new WorldSerializer());
    }

    public MessageBuilder build(String messageKey) {
        return new MessageBuilder(serializerMap, messageBag.getMessage(messageKey));
    }
}
