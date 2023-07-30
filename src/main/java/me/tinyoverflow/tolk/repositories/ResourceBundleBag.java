package me.tinyoverflow.tolk.repositories;

import me.tinyoverflow.tolk.exceptions.UnknownMessageKeyException;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

@SuppressWarnings("unused")
public class ResourceBundleBag implements MessageBag
{
    private final Map<String, String> messages = new HashMap<>();

    /**
     * Load all messages from a resource bundle.
     *
     * @param resourceBundle The bundle to read messages from.
     */
    public ResourceBundleBag(ResourceBundle resourceBundle)
    {
        for (String key : resourceBundle.keySet())
        {
            messages.put(key, resourceBundle.getString(key));
        }
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
