package me.tinyoverflow.tolker.serializer;

import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;

public class FloatSerializer implements TypeSerializer<Float>
{
    @Override
    public @NotNull Component serialize(@NotNull Float obj)
    {
        return Component.text(obj);
    }
}
