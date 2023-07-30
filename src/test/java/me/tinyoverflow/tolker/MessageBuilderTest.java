package me.tinyoverflow.tolker;

import me.tinyoverflow.tolker.repositories.MemoryBag;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MessageBuilderTest
{
    private Tolker tolker;

    @BeforeEach
    void setUp()
    {
        MemoryBag bag = new MemoryBag();
        bag.addMessage("demo", "First <text> Message");
        bag.addMessage("number", "Price: $<price:'en-US':'#.00'>");
        bag.addMessage("date", "Christmas: <date:'dd.MM.yyyy'>");
        bag.addMessage("choice", "I met <choice:'0#no developer|1#one developer|1<many developers'>!");

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

    @Test
    void buildWithNumberFormatter()
    {
        Component component = tolker.build("number")
                .withNumber("price", 15)
                .build();

        assertEquals("Price: $15.00", PlainTextComponentSerializer.plainText().serialize(component));
    }

    @Test
    void buildWithDateFormatter()
    {
        Component component = tolker.build("date")
                .withDate("date", LocalDate.of(2023, 12, 24))
                .build();

        assertEquals("Christmas: 24.12.2023", PlainTextComponentSerializer.plainText().serialize(component));
    }

    @Test
    void buildWithChoiceFormatter()
    {
        assertEquals("I met no developer!", PlainTextComponentSerializer.plainText().serialize(tolker.build("choice")
                .withChoice("choice", 0)
                .build()));

        assertEquals("I met one developer!", PlainTextComponentSerializer.plainText().serialize(tolker.build("choice")
                .withChoice("choice", 1)
                .build()));

        assertEquals("I met many developers!", PlainTextComponentSerializer.plainText().serialize(tolker.build("choice")
                .withChoice("choice", 2)
                .build()));
    }
}