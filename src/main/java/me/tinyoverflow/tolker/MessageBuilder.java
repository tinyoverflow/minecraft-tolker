package me.tinyoverflow.tolker;

import me.tinyoverflow.tolker.exceptions.MissingSerializerException;
import me.tinyoverflow.tolker.serializer.TypeSerializer;
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

    @SuppressWarnings("unchecked")
    public <T> MessageBuilder with(String key, T value)
    {
        TypeSerializer<T> serializer = (TypeSerializer<T>) serializerMap.get(value.getClass());

        if (serializer == null)
        {
            Class<?>[] interfaces = value.getClass().getInterfaces();

            for (Class<?> interfaze : interfaces) {
                if (serializerMap.containsKey(interfaze)) {
                    serializer = (TypeSerializer<T>) serializerMap.get(interfaze);
                    break;
                }
            }

            if (serializer == null) {
                throw new MissingSerializerException(value.getClass());
            }
        }

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
