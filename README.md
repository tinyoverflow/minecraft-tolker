# 💬 Tolker

![GitHub Version](https://img.shields.io/github/v/tag/tinyoverflow/tolker?style=flat-square&label=Version)
![GitHub Issues](https://img.shields.io/github/issues/tinyoverflow/tolker?style=flat-square&label=Issues)
![GitHub Last Commit](https://img.shields.io/github/last-commit/tinyoverflow/tolker/main?style=flat-square&label=Updated)
![GitHub Workflow Status](https://img.shields.io/github/actions/workflow/status/tinyoverflow/tolker/maven.yml?style=flat-square&label=Build)

Tolker is an easy-to-use messaging library for Paper. It provides you an
interface which allows you to easily compose messages with placeholders in them.

- [Installation](#installation)
  - [Install using Maven](#install-using-maven)
  - [Install using Gradle](#install-using-gradle)
- [Usage](#usage)
  - [Message Format](#message-format)
  - [Instantiating Tolker](#instantiating-tolker)
  - [Custom Type Serializers](#custom-type-serializers)

## Installation

Simply add the package `me.tinyoverflow:tolker:<version>` to your project. While
this package is available on GitHub Packages, it is recommended to use JitPack.

### Install using Maven

```xml
<!-- Repository -->
<repository>
    <id>jitpack.io</id>
    <url>https://jitpack.io</url>
</repository>
```

```xml
<!-- Dependency -->
<dependency>
    <groupId>com.github.tinyoverflow</groupId>
    <artifactId>minecraft-tolker</artifactId>
    <version><version tag></version>
</dependency>
```

### Install using Gradle

```kotlin
allprojects {
    repositories {
        maven { url 'https://jitpack.io' }
    }
}

dependencies {
    implementation 'com.github.tinyoverflow:minecraft-tolker:<version tag>'
}
```

## Usage

This plugin consists out of 3 components:

1. `MessageBag`
2. `Tolker`
3. `MessageBuilder`

The `MessageBag` is responsible for providing any messages. It is an interface
that you can implement into your message loader, if you have one. The Tolker
library also provides you with two implementations:

1. `MemoryBag`  
   Provides an empty message bag that you can add messages to which are only
   stored in memory.

2. `ResourceBundleBag`  
   Serves messages from a ResourceBundle that you give to it.

### Message Format

Each message is represented by a so-called _Message Key_. The key is simply a
string which uniquely identifies the message template itself. Properties files
are great examples of that concept:

```properties
player-kicked=<player> was kicked by <admin>.
player-banned=<player> was banned by <admin> for <reason>.
```

In this example the keys are `player-kicked` and `player-banned`.

### Instantiating Tolker

Instantiating Tolker is pretty easy. You simply provide it with a `MessageBag`
and you're good to go. We'll use the `ResourceBundleBag` with the
following `messages.properties` in this example.

```properties
welcome-message=Welcome, <green><player></green>! You're playing on <red><world></red>.
```

```java
class MyPlugin extends JavaPlugin implements Listener
{
    private Tolker tolker;

    public void onEnable()
    {
        // Create MessageBag from ResourceBundle
        ResourceBundle messageBundle = ResourceBundle.getBundle("messages");
        MessageBag messageBag = new ResourceBundleBag(messageBundle);

        // Create a new Tolker instance
        tolker = new Tolker(messageBag);
        tolker.registerDefaultSerializers();

        getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event)
    {
        // Send the welcome-message to the joining player with the given
        // objects as values for the corresponding placeholders
        tolker.build("welcome-message")
                .with("player", event.getPlayer())
                .with("world", event.getPlayer().getWorld())
                .send(event.getPlayer());
    }
}
```

### Custom Type Serializers

Tolker supports custom type serializers. To add a custom one, create a class
which extends the `TypeSerializer<T>` interface. Here's an example for a
serializer for the UUID object:

```java
public class UUIDSerializer implements TypeSerializer<UUID>
{
    public @NotNull Component serialize(@NotNull UUID obj)
    {
        return Component.text(obj.toString());
    }
}
```

After creating it, register it to Tolker after it was instantiated:

```java
Tolker tolker = new Tolker(/* Message Bag */);
tolker.registerSerializer(UUID.class, new UUIDSerializer());
```

You can now pass UUID objects as a value to the `MessageBuilder#with` method.
