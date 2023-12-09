package net.quantrax.messagebuilder.stage;

import net.quantrax.messagebuilder.Language;
import org.jetbrains.annotations.ApiStatus;

public record LanguageStage(Language language) {

	@ApiStatus.Internal
	public Language language() {
		return this.language;
	}

}
