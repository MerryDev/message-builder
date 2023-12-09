package net.quantrax.messagebuilder.stage;

import lombok.AllArgsConstructor;
import net.kyori.adventure.text.Component;
import net.quantrax.messagebuilder.Language;
import net.quantrax.messagebuilder.Message;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

import java.util.List;

@AllArgsConstructor
public class LanguageStage extends Resolver {

	private final Language language;
	private final List<Message> localizedMessages;

	public MessageStage messaging(@NotNull String messageKey) {
		return new MessageStage(messageKey);
	}

	public @NotNull Component message(@NotNull String messageKey) {
		return resolve(messageKey, language, this.localizedMessages);
	}

	@ApiStatus.Internal
	public Language language() {
		return this.language;
	}

}
