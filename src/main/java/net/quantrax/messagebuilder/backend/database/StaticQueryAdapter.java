package net.quantrax.messagebuilder.backend.database;

import de.chojo.sadu.base.QueryFactory;
import de.chojo.sadu.wrapper.stage.QueryStage;
import net.quantrax.messagebuilder.backend.database.exception.AlreadyInitializedException;
import net.quantrax.messagebuilder.backend.database.exception.NotInitializedException;

import javax.sql.DataSource;
import java.util.logging.Logger;

public class StaticQueryAdapter {

	private static final Logger LOGGER = Logger.getLogger(StaticQueryAdapter.class.getName());
	private static QueryFactory factory = null;

	public static QueryStage<Void> builder() {
		assertInit();
		return factory.builder();
	}

	public static <T> QueryStage<T> builder(Class<T> clazz) {
		assertInit();
		return factory.builder(clazz);
	}

	public static void start(DataSource dataSource) {
		if (factory != null) throw new AlreadyInitializedException();
		factory = new QueryFactory(dataSource);

		LOGGER.info("Static sadu query adapter started.");
	}

	private static void assertInit() {
		if (factory == null) throw new NotInitializedException();
	}

}
