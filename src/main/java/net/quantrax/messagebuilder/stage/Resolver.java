package net.quantrax.messagebuilder.stage;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;
import net.quantrax.messagebuilder.Language;
import net.quantrax.messagebuilder.Message;

import java.util.List;

public class Resolver {

	public Component resolve(String messageKey, Language language, List<Message> localizedMessages) {
		String message = "";
		for (Message localized : localizedMessages) {
			if (localized.name().equalsIgnoreCase(messageKey)) message = localized.value();
		}

		return MiniMessage.miniMessage().deserialize(message, TagResolver.standard());
	}

}
