package me.tinyoverflow.tolk;

import me.tinyoverflow.tolk.exceptions.MissingSerializerException;
import me.tinyoverflow.tolk.serializer.TypeSerializer;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MessageBuilder
{
    private final Map<Class<?>, TypeSerializer<?>> serializerMap;
    private final String message;

    private final Set<TagResolver.Single> replacements = new HashSet<>();

    public MessageBuilder(Map<Class<?>, TypeSerializer<?>> serializerMap, String message)
    {
        this.serializerMap = serializerMap;
        this.message = message;
    }

    public <T> MessageBuilder with(String key, T value)
    {
        if (!serializerMap.containsKey(value.getClass()))
        {
            throw new MissingSerializerException(value.getClass());
        }

        //noinspection unchecked
        TypeSerializer<T> serializer = (TypeSerializer<T>) serializerMap.get(value.getClass());

        //noinspection PatternValidation
        replacements.add(Placeholder.component(key, serializer.serialize(value)));

        return this;
    }

    public Component build()
    {
        TagResolver tagResolver = TagResolver.builder().resolvers(replacements).build();
        return MiniMessage.miniMessage().deserialize(message, tagResolver);
    }

    @SuppressWarnings("unused")
    public void send(Audience audience)
    {
        audience.sendMessage(this::build);
    }
}
