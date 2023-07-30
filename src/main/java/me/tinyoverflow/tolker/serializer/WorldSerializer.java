package me.tinyoverflow.tolker.serializer;

import net.kyori.adventure.text.Component;
import org.bukkit.World;
import org.jetbrains.annotations.NotNull;

public class WorldSerializer implements TypeSerializer<World>
{
    @Override
    public @NotNull Component serialize(@NotNull World obj)
    {
        return Component.text(obj.getName());
    }
}
