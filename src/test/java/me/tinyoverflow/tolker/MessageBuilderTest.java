package me.tinyoverflow.tolker;

import me.tinyoverflow.tolker.repositories.MemoryBag;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

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
        bag.addMessage("date", "Christmas: $<date:'dd.MM.yyyy'>");

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
    void buildWithNumberFormatter() {
        Component component = tolker.build("number")
                .withNumber("price", 15)
                .build();

        assertEquals("Price: $15.00", PlainTextComponentSerializer.plainText().serialize(component));
    }

    @Test
    void buildWithDateFormatter() {
        Component component = tolker.build("date")
                .withDate("date", LocalDate.of(2023, 12, 24))
                .build();

        assertEquals("Christmas: 24.12.2023", PlainTextComponentSerializer.plainText().serialize(component));
    }
}