package me.tinyoverflow.tolker.serializer;

import net.kyori.adventure.text.Component;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;

public class OfflinePlayerSerializer implements TypeSerializer<OfflinePlayer>
{
    @Override
    public @NotNull Component serialize(@NotNull OfflinePlayer obj)
    {
        if (obj.getName() == null) {
            return Component.text(obj.getUniqueId().toString());
        }

        return Component.text(obj.getName());
    }
}
