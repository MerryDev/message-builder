package net.quantrax.messagebuilder;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Locale;
import java.util.concurrent.CompletableFuture;

import static net.quantrax.messagebuilder.backend.database.StaticQueryAdapter.builder;

@Accessors(fluent = true)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum Language {

	ENGLISH("en"),
	GERMAN("de");

	private final String key;

	public static Language fromLocale(@NotNull Locale locale) {
		return (locale == Locale.GERMAN || locale == Locale.GERMANY) ? GERMAN : ENGLISH;
	}

	public static CompletableFuture<List<Message>> messages(Language language) {
		final String tableName = "messages_%s".formatted(language.key());

		return builder(Message.class).query("SELECT * FROM " + tableName + ";")
				.emptyParams()
				.readRow(row -> {
					final String name = row.getString("name");
					final String value = row.getString("value");

					return new Message(name, value);
				})
				.all();
	}
}
