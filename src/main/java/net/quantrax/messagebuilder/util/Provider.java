package net.quantrax.messagebuilder.util;

import net.quantrax.messagebuilder.MessageBuilder;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

public interface Provider {

	@ApiStatus.Internal
	@PlatformAPI
	@NotNull MessageBuilder messageBuilder();

}
