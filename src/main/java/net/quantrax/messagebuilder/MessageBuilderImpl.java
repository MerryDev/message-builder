package net.quantrax.messagebuilder;

import com.zaxxer.hikari.HikariDataSource;
import net.quantrax.messagebuilder.backend.database.Credentials;
import net.quantrax.messagebuilder.backend.database.StaticSaduLoader;
import net.quantrax.messagebuilder.stage.LanguageStage;
import net.quantrax.messagebuilder.util.Provider;
import net.quantrax.messagebuilder.util.Services;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.logging.Logger;

public class MessageBuilderImpl implements MessageBuilder {

	private static final Logger LOGGER = Logger.getLogger(MessageBuilderImpl.class.getName());
	private static final Optional<Provider> SERVICE = Services.service(Provider.class);
	private HikariDataSource hikariDataSource;
	private final Map<Language, List<Message>> localizedMessages = new HashMap<>();

	@Override
	public void setup() {
		final Properties properties = new Properties();
		try (InputStream input = Thread.currentThread().getContextClassLoader().getResourceAsStream("config.properties")) {
			properties.load(input);

			if (properties.isEmpty()) throw new IllegalStateException("Could not read config.properties. Using default credentials");
			final Credentials credentials = new Credentials(
					properties.getProperty("host", "localhost"),
					properties.getProperty("port", "3306"),
					properties.getProperty("database", "language"),
					properties.getProperty("user", "root"),
					properties.getProperty("password", "")
			);

			this.hikariDataSource = StaticSaduLoader.start(credentials);
			this.fetchMessages();

		} catch (IOException exception) {
			LOGGER.severe("Could not load config.properties file. MessageBuilder will not work.");
		}
	}

	@Override
	public void destroy() {
		this.hikariDataSource.close();
	}

	@Override
	public @NotNull LanguageStage language(@NotNull Language language) {
		return new LanguageStage(language, this.localizedMessages.getOrDefault(language, Collections.emptyList()));
	}

	@Override
	public @NotNull LanguageStage localized(@NotNull Player player) {
		final Language suitableLanguage = Language.fromLocale(player.locale());

		return new LanguageStage(suitableLanguage, this.localizedMessages.getOrDefault(suitableLanguage, Collections.emptyList()));
	}

	@Override
	public @NotNull Map<Language, List<Message>> localizedMessages() {
		return this.localizedMessages;
	}

	private void fetchMessages() {
		for (Language language : Language.values()) {
			Language.messages(language)
					.whenComplete((messages, $) -> this.localizedMessages.putIfAbsent(language, messages))
					.exceptionally(throwable -> {
						LOGGER.severe(throwable.getLocalizedMessage());
						return Collections.emptyList();
					});
		}
	}

	static final class Instances {
		static final MessageBuilder INSTANCE = SERVICE.map(Provider::messageBuilder).orElseGet(MessageBuilderImpl::new);
	}
}
