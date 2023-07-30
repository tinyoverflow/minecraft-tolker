package me.tinyoverflow.tolk.serializer;

import net.kyori.adventure.text.Component;
import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;

public class LocationSerializer implements TypeSerializer<Location>
{
    @Override
    public @NotNull Component serialize(@NotNull Location obj)
    {
        return Component.text()
                .append(Component.text(obj.getX()))
                .appendSpace()
                .append(Component.text(obj.getX()))
                .appendSpace()
                .append(Component.text(obj.getX()))
                .asComponent();
    }
}
