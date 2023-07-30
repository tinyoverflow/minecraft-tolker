package me.tinyoverflow.tolk.serializer;

import net.kyori.adventure.text.Component;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class ItemStackSerializer implements TypeSerializer<ItemStack>
{
    @Override
    public @NotNull Component serialize(@NotNull ItemStack obj)
    {
        return obj.displayName();
    }
}
