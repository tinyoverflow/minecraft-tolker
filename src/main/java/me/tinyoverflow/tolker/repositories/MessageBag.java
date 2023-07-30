package me.tinyoverflow.tolker.repositories;

import me.tinyoverflow.tolker.exceptions.UnknownMessageKeyException;
import org.jetbrains.annotations.NotNull;

public interface MessageBag
{
    /**
     * Retrieves the plaintext message from the bag.
     *
     * @param messageKey The key to get the plaintext message for. It works like an ID for the message.
     * @return Plaintext message for the given key.
     * @throws UnknownMessageKeyException Indicates that the given key does not exist inside the message bag.
     */
    @NotNull String getMessage(String messageKey) throws UnknownMessageKeyException;

    /**
     * Returns the amount of messages inside the bag.
     *
     * @return Number of messages available.
     */
    int size();
}
