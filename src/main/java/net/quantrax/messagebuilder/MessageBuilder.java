package net.quantrax.messagebuilder;

import net.quantrax.messagebuilder.util.PlatformAPI;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

public interface MessageBuilder {

	static @NotNull MessageBuilder messageBuilder() {
		return MessageBuilderImpl.Instances.INSTANCE;
	}

}

interface Provider {

	@ApiStatus.Internal
	@PlatformAPI
	@NotNull MessageBuilder messageBuilder();

}
