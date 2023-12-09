package net.quantrax.messagebuilder;

import net.quantrax.messagebuilder.stage.LanguageStage;
import net.quantrax.messagebuilder.util.Provider;
import net.quantrax.messagebuilder.util.Services;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class MessageBuilderImpl implements MessageBuilder {
	private static final Optional<Provider> SERVICE = Services.service(Provider.class);

	@Override
	public @NotNull LanguageStage language(@NotNull Language language) {
		return new LanguageStage(language);
	}

	@Override
	public @NotNull LanguageStage localized(@NotNull Player player) {
		final Language suitableLanguage = Language.fromLocale(player.locale());

		return new LanguageStage(suitableLanguage);
	}

	static final class Instances {
		static final MessageBuilder INSTANCE = SERVICE
				.map(Provider::messageBuilder)
				.orElseGet(MessageBuilderImpl::new);
	}
}
