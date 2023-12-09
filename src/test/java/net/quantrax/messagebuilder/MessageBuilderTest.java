package net.quantrax.messagebuilder;

import be.seeseemelk.mockbukkit.MockBukkit;
import be.seeseemelk.mockbukkit.ServerMock;
import be.seeseemelk.mockbukkit.entity.PlayerMock;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.junit.jupiter.api.*;

import java.util.List;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

public class MessageBuilderTest {

	private PlayerMock player;

	@BeforeAll
	static void setup() {
		MessageBuilder.messageBuilder().setup();
	}

	@AfterAll
	static void destroy() {
		MessageBuilder.messageBuilder().destroy();
	}

	@BeforeEach
	void setUp() {
		ServerMock server = MockBukkit.mock();
		player = server.addPlayer();

		player.setLocale(Locale.ENGLISH);
	}

	@AfterEach
	void tearDown() {
		MockBukkit.unmock();
	}

	@Test
	void initDoesNotFail() {
		assertNotNull(MessageBuilder.messageBuilder(), "The MessageBuilder instance is null");
	}

	@Test
	void languageIsGerman() {
		Language expected = Language.GERMAN;
		Language actual = MessageBuilder.messageBuilder().language(Language.GERMAN).language();

		assertEquals(expected, actual, "The manually selected language does not match with the expected language. Expected: %s, actual: %s".formatted(expected, actual));
	}

	@Test
	void localizedIsEnglish() {
		Language expected = Language.ENGLISH;
		Language actual = MessageBuilder.messageBuilder().localized(player).language();

		assertEquals(expected, actual, "The auto-fetched language from the player does not match with the expected language. Expected: %s, actual: %s".formatted(expected, actual));
	}

	@Test
	void noGermanMessagesFetched() {
		final List<Message> germanMessages = MessageBuilder.messageBuilder().localizedMessages().get(Language.GERMAN);

		assertTrue(germanMessages.isEmpty(), "German messages were found.");
	}

	@Test
	void englishMessagesFound() {
		final List<Message> englishMessages = MessageBuilder.messageBuilder().localizedMessages().get(Language.ENGLISH);

		assertFalse(englishMessages.isEmpty(), "There was no english message found.");
	}

	@Test
	void confirmResolving() {
		final Component expected = MiniMessage.miniMessage().deserialize("<red>Test<red>");
		final Component actual = MessageBuilder.messageBuilder().localized(player).message("test.test");

		assertEquals(expected, actual, "The resolved component does not match with the expectation.");
	}

}
