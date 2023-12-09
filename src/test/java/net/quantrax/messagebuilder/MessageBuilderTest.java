package net.quantrax.messagebuilder;

import be.seeseemelk.mockbukkit.MockBukkit;
import be.seeseemelk.mockbukkit.ServerMock;
import be.seeseemelk.mockbukkit.entity.PlayerMock;
import org.junit.jupiter.api.*;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class MessageBuilderTest {

	private static MessageBuilder messageBuilder;
	private ServerMock server;
	private PlayerMock player;

	@BeforeAll
	static void setup() {
		messageBuilder = MessageBuilder.messageBuilder();
		messageBuilder.setup();
	}

	@AfterAll
	static void destroy() {
		messageBuilder.destroy();
	}

	@BeforeEach
	void setUp() {
		server = MockBukkit.mock();
		player = server.addPlayer();

		player.setLocale(Locale.ENGLISH);
	}

	@AfterEach
	void tearDown() {
		MockBukkit.unmock();
	}

	@Test
	void initDoesNotFail() {
		assertNotNull(messageBuilder, "The MessageBuilder instance is null");
	}

	@Test
	void languageIsGerman() {
		Language expected = Language.GERMAN;
		Language actual = messageBuilder.language(Language.GERMAN).language();

		assertEquals(expected, actual, "The manually selected language does not match with the expected language. Expected: %s, actual: %s".formatted(expected, actual));
	}

	@Test
	void localizedIsEnglish() {
		Language expected = Language.ENGLISH;
		Language actual = messageBuilder.localized(player).language();

		assertEquals(expected, actual, "The auto-fetched language from the player does not match with the expected language. Expected: %s, actual: %s".formatted(expected, actual));
	}

}
