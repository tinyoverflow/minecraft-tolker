package me.tinyoverflow.tolk.serializer;

import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;

public interface TypeSerializer<T>
{
    @NotNull Component serialize(@NotNull T obj);
}
