package me.tinyoverflow.tolk.repositories;

import me.tinyoverflow.tolk.exceptions.UnknownMessageKeyException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MemoryBagTest
{

    @Test
    void addMessageAddsTheMessageToTheBag()
    {
        MemoryBag bag = new MemoryBag();
        assertEquals(0, bag.size());

        bag.addMessage("test", "Test Message");
        assertEquals(1, bag.size());
    }

    @Test
    void getMessageReturnsTheMessage()
    {
        MemoryBag bag = new MemoryBag();
        bag.addMessage("test", "Test Message");

        assertEquals("Test Message", bag.getMessage("test"));
    }

    @Test
    void getMessageThrowsOnInvalidKey()
    {
        MemoryBag bag = new MemoryBag();
        assertThrows(UnknownMessageKeyException.class, () -> bag.getMessage("test"));
    }
}