package net.quantrax.messagebuilder;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class MessageBuilderTest {

	private static MessageBuilder messageBuilder;

	@BeforeAll
	static void init() {
		messageBuilder = MessageBuilder.messageBuilder();
	}

	@Test
	void initDoesNotFail() {
		assertNotNull(messageBuilder, "The MessageBuilder instance is null");
	}

}
