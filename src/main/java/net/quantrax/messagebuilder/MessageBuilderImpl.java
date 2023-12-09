package net.quantrax.messagebuilder;

import net.quantrax.messagebuilder.backend.database.Properties;
import net.quantrax.messagebuilder.backend.database.StaticSaduLoader;
import net.quantrax.messagebuilder.stage.LanguageStage;
import net.quantrax.messagebuilder.util.Provider;
import net.quantrax.messagebuilder.util.Services;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.tomlj.Toml;
import org.tomlj.TomlParseResult;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Logger;

public class MessageBuilderImpl implements MessageBuilder {
	private static final Logger LOGGER = Logger.getLogger(MessageBuilderImpl.class.getName());
	private static final Optional<Provider> SERVICE = Services.service(Provider.class);

	@Override
	public @NotNull LanguageStage language(@NotNull Language language) {
		return new LanguageStage(language);
	}

	@Override
	public void setup() {
		try {
			final URL url = getClass().getResource("config.toml");
			if (url == null) throw new RuntimeException("Could not load config.toml file. MessageBuilder will not work.");

			final Path source = Paths.get(url.toURI());
			final TomlParseResult result = Toml.parse(source);

			result.errors().forEach(error -> LOGGER.severe(error.getLocalizedMessage())); // Log possible errors during parsing

			final Map<String, Object> credentials = result.toMap();
			final Properties properties = new Properties(
					credentials.get("host").toString(),
					credentials.get("port").toString(),
					credentials.get("database").toString(),
					credentials.get("username").toString(),
					credentials.get("password").toString()
			);

			StaticSaduLoader.start(properties);

		} catch (URISyntaxException | IOException exception) {
			LOGGER.severe("Could not get proper information from config.toml. Check the config syntax and the path to the file.");
		}
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
