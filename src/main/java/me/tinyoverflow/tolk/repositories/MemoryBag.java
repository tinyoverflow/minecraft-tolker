package me.tinyoverflow.tolk.repositories;

import me.tinyoverflow.tolk.exceptions.UnknownMessageKeyException;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class MemoryBag implements MessageBag
{
    private final Map<String, String> messages = new HashMap<>();

    /**
     * Adds a message to the in-memory message bag.
     *
     * @param key   The key for the message.
     * @param value The plaintext message itself to add.
     */
    public void addMessage(String key, String value)
    {
        messages.put(key, value);
    }

    @Override
    public @NotNull String getMessage(String messageKey) throws UnknownMessageKeyException
    {
        if (!messages.containsKey(messageKey))
        {
            throw new UnknownMessageKeyException(messageKey);
        }

        return messages.get(messageKey);
    }

    @Override
    public int size()
    {
        return messages.size();
    }
}
