package me.tinyoverflow.tolk.serializer;

import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;

public class StringSerializer implements TypeSerializer<String>
{
    @Override
    public @NotNull Component serialize(@NotNull String obj)
    {
        return Component.text(obj);
    }
}
