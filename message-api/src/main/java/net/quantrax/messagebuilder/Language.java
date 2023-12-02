package net.quantrax.messagebuilder;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;

/**
 * A complete list of all currently supported languages <br><br>
 * <p>
 * Since this project is using lombok for generating getters, setters, and simple constructors, those annotations have been placed at the class definition. <br>
 * The {@code fluent = true} setting causes lombok to generate the getter without the {@code get} prefix at the beginning of the method name. So instead of {@code getName()}, lombok will generate a method called {@code name()}.
 */
@AllArgsConstructor
@Getter
@Accessors(fluent = true)
public enum Language {

	ENGLISH("english"),
	GERMAN("german");

	/**
	 * The name that is stored in the database to represent the language
	 */
	private final String name;

}
