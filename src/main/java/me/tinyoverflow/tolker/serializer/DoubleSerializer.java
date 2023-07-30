package me.tinyoverflow.tolker.serializer;

import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;

public class DoubleSerializer implements TypeSerializer<Double>
{
    @Override
    public @NotNull Component serialize(@NotNull Double obj)
    {
        return Component.text(obj);
    }
}
