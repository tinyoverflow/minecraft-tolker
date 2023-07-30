package me.tinyoverflow.tolker.serializer;

import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;

public class LongSerializer implements TypeSerializer<Long>
{
    @Override
    public @NotNull Component serialize(@NotNull Long obj)
    {
        return Component.text(obj);
    }
}
