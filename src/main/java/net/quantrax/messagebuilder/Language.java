package net.quantrax.messagebuilder;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;
import org.jetbrains.annotations.NotNull;

import java.util.Locale;

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

}
