package me.tinyoverflow.tolker;

import me.tinyoverflow.tolker.repositories.MemoryBag;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MessageBuilderTest
{
    private Tolker tolker;

    @BeforeEach
    void setUp()
    {
        MemoryBag bag = new MemoryBag();
        bag.addMessage("first", "First <text> Message");

        tolker = new Tolker(bag);
        tolker.registerDefaultSerializers();
    }

    @Test
    void build()
    {
        Component component = tolker.build("first")
                .with("text", "Awesome")
                .build();

        assertEquals("First Awesome Message", PlainTextComponentSerializer.plainText().serialize(component));
    }
}