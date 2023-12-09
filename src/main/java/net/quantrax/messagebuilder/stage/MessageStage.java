package net.quantrax.messagebuilder.stage;

import lombok.RequiredArgsConstructor;
import net.kyori.adventure.text.Component;
import net.quantrax.messagebuilder.Language;
import net.quantrax.messagebuilder.Message;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.List;

@RequiredArgsConstructor
public class MessageStage extends Resolver {

	private final String messageKey;
	private final Language language;
	private final List<Message> localizedMessages;

	public void send(@NotNull Player player) {
		final Component component = resolve(this.messageKey, this.language, this.localizedMessages);
		player.sendMessage(component);
	}

}
