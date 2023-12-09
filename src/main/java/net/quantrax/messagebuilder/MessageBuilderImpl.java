package net.quantrax.messagebuilder;

import net.quantrax.messagebuilder.util.Provider;
import net.quantrax.messagebuilder.util.Services;

import java.util.Optional;

public class MessageBuilderImpl implements MessageBuilder {
	private static final Optional<Provider> SERVICE = Services.service(Provider.class);

	static final class Instances {
		static final MessageBuilder INSTANCE = SERVICE
				.map(Provider::messageBuilder)
				.orElseGet(MessageBuilderImpl::new);
	}
}
