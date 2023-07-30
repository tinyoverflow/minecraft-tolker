package me.tinyoverflow.tolker.serializer;

import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class PlayerSerializer implements TypeSerializer<Player>
{
    @Override
    public @NotNull Component serialize(@NotNull Player obj)
    {
        return Component.text(obj.getName());
    }
}
