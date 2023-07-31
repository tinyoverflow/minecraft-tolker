package me.tinyoverflow.tolker.serializer;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import org.jetbrains.annotations.NotNull;

public class ComponentSerializer implements TypeSerializer<TextComponent>
{
    @Override
    public @NotNull Component serialize(@NotNull TextComponent obj)
    {
        return obj;
    }
}
