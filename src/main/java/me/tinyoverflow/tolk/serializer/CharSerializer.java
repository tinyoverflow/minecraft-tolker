package me.tinyoverflow.tolk.serializer;

import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;

public class CharSerializer implements TypeSerializer<Character>
{
    @Override
    public @NotNull Component serialize(@NotNull Character obj)
    {
        return Component.text(obj);
    }
}
