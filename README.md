# message-builder

### A multi-language supporting messaging library

___

## Design & Disclaimer

This library is designed to allow developers to easily support multiple languages in their minecraft plugin.
For reasons, we **only support**
sending components defined by [Adventure](https://docs.advntr.dev/index.html).
A list of software that has native support of Adventure can be found [here](https://docs.advntr.dev/platform/native.html#native-support).
<br> <br>
If you want to include Adventure in older software, feel free to do so.
But keep in mind: In case something breaks, we do not accept any liability for it.
So you've been warned.

___

## How to use

#### F: Okay I have this library, but how the fuck to I properly use it?

To start with, simply run the following statement **once** in your plugin **before** sending messages with this library.

```java
MessageBuilder.messageBuilder().setup();
```

With this peace of code you will fetch all messages by their name from the database.
<br><br><br>

#### F: Okay and now, how can I send a message with this library?

We support two different ways of sending a message to a player:
<br><br>

##### 1. By explicitly defining the message as a component and sending it separately
In this snippet below we first define our component and then send it to the player. <br>
Get the desired language by calling the enum `Language` and select either `ENGLISH` or `GERMAN`.
Currently, we only support these two languages.


```java
final Component message = MessageBuilder.messageBuilder().language(language).message(messageKey);
sendMessage(message);
```
<br>

##### 2. By directly sending the message to the player
In this snippet below we directly send the message by calling `messaging()` instead of `message()` and appending `send(player)` to our statement.
```java
MessageBuilder.messageBuilder().language(language).messaging(messageKey).send(player);
```
<br>

#### Pro-Tip: Use our auto-selecting language system
Sending a message works the same way as described above. But instead of selecting the desired language with `language()`, we can instead call `localized(player)`.
This will retrieve the language the player is playing minecraft with and translates it into the supported languages. If neither an english nor the german language is selected, the message will be sent with the language-setting 
`ENGLISH`.
```java
// Message sending with explicitly defining the component
final Component message = MessageBuilder.messageBuilder().localized(player).message(messageKey);
sendMessage(message);

// Direct message sending
MessageBuilder.messageBuilder().localized(player).messaging(messageKey).send(player);
```
___

## Additional

You have to shade this library into your project.