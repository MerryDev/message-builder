package net.quantrax.messagebuilder;

import org.jetbrains.annotations.NotNull;

public interface MessageBuilder {

	static @NotNull MessageBuilder messageBuilder() {
		return MessageBuilderImpl.Instances.INSTANCE;
	}

}
