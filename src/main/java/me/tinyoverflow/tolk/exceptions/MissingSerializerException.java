package me.tinyoverflow.tolk.exceptions;

public class MissingSerializerException extends RuntimeException
{
    private final Class<?> type;

    public MissingSerializerException(Class<?> type) {
        super("Missing serializer for type " + type.getName());
        this.type = type;
    }

    public Class<?> getType()
    {
        return type;
    }
}
