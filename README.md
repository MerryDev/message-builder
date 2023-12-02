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
We support two ways of sending a message to the player:

### 1. By explicitly defining the message as a component and sending it separately
In the snipped below, we firstly define our player and retrieve the selected language. 
After that, we can create our message by selecting the desired language by using the defined player language and setting the "messageKey" which represents the unique database entry for each message.
Now, having the message in our desired language, we can simply send it to the player. 
```java
final QPlayer player = [...]; // The way of how you get the player
Language language = player.language(); // Gets the selected language

Component message = MessageBuilder.builder().language(language).message(messageKey); // messageKey is the name in the database under which the message is stored
player.message(message); // Sends the message to the player
```
<br>

**Note: We provide a shortcut for sending a localized message based on the players' game language.**
<br>
But keep in mind that this will ignore the player language setting
<br>

In the snipped below, we do the same as normal, but instead of selecting an explicit language, we call `localized()` instead of `language(language)`.


```java
final QPlayer player = [...]; // The way of how you get the player

Component localizedMessage = MessageBuilder.builder().localized().message(messageKey); // messageKey is the name in the database under which the message is stored
player.message(localizedMessage); // Sends the message to the player
```

<br>

### 2. By directly sending the message 
In the snipped below, we do the exact same thing as in the two snippets above but instead of defining the message as a Component, we directly send it to the player by using `send(player)`. 
<br>

Sending a localized message works the same way as described earlier and here.
```java
final QPlayer player = [...]; // The way of how you get the player
Language language=player.language(); // Gets the selected language

MessageBuilder.builder().language(language).message(messageKey).send(player); // Sends the message directly to the player without explictily defining the message a component
```