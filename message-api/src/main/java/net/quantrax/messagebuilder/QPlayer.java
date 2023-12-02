package net.quantrax.messagebuilder;

import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;

/**
 * Represents a simple abstract version of a quantrax.net player based on the needs for this library
 */
public interface QPlayer {

	/**
	 * Returns the currently selected language
	 *
	 * @return The selected {@link Language}
	 */
	@NotNull Language language();

	/**
	 * Updates the selected language
	 *
	 * @param language The new {@link Language} to be set
	 */
	void language(@NotNull Language language);

	/**
	 * Sends a message to the player
	 *
	 * @param component The message to send
	 */
	void message(@NotNull Component component);

}
