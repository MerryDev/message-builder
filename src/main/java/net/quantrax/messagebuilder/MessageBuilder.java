package net.quantrax.messagebuilder;

import lombok.experimental.Accessors;
import net.quantrax.messagebuilder.stage.LanguageStage;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;

public interface MessageBuilder {

	static @NotNull MessageBuilder messageBuilder() {
		return MessageBuilderImpl.Instances.INSTANCE;
	}

	@ApiStatus.Internal
	void setup();

	@ApiStatus.Internal
	void destroy();

	@ApiStatus.Internal
	@NotNull Map<Language, List<Message>> localizedMessages();

	@NotNull LanguageStage language(@NotNull Language language);

	@NotNull LanguageStage localized(@NotNull Player player);

}
