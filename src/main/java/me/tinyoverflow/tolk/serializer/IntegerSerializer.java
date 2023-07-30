package me.tinyoverflow.tolk.serializer;

import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;

public class IntegerSerializer implements TypeSerializer<Integer>
{
    @Override
    public @NotNull Component serialize(@NotNull Integer obj)
    {
        return Component.text(obj);
    }
}
