package me.tinyoverflow.tolker.serializer;

import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;

public class BooleanSerializer implements TypeSerializer<Boolean>
{
    private final String trueText;
    private final String falseText;

    public BooleanSerializer()
    {
        this("True", "False");
    }

    public BooleanSerializer(String trueText, String falseText) {
        this.trueText = trueText;
        this.falseText = falseText;
    }

    @Override
    public @NotNull Component serialize(@NotNull Boolean obj)
    {
        return Component.text(obj ? trueText : falseText);
    }
}
