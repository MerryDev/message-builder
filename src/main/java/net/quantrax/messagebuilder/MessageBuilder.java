package net.quantrax.messagebuilder;

import net.quantrax.messagebuilder.stage.LanguageStage;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public interface MessageBuilder {

	static @NotNull MessageBuilder messageBuilder() {
		return MessageBuilderImpl.Instances.INSTANCE;
	}

	@NotNull LanguageStage language(@NotNull Language language);

	@NotNull LanguageStage localized(@NotNull Player player);

}
