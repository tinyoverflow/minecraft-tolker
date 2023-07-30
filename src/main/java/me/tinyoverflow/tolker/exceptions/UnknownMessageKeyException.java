package me.tinyoverflow.tolker.exceptions;

public class UnknownMessageKeyException extends IllegalArgumentException
{
    private final String messageKey;

    public UnknownMessageKeyException(String messageKey) {
        this.messageKey = messageKey;
    }

    @SuppressWarnings("unused")
    public String getMessageKey()
    {
        return messageKey;
    }
}
