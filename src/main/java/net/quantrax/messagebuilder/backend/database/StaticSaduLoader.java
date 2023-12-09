package net.quantrax.messagebuilder.backend.database;

import com.zaxxer.hikari.HikariDataSource;
import de.chojo.sadu.databases.MariaDb;
import de.chojo.sadu.datasource.DataSourceCreator;

public class StaticSaduLoader {

	public static HikariDataSource start(Credentials properties) {
		final HikariDataSource source = createSource(properties);
		StaticQueryAdapter.start(source);

		return source;
	}

	private static HikariDataSource createSource(Credentials properties) {
		return DataSourceCreator.create(MariaDb.get())
				.configure(config -> config.host(properties.host()).port(properties.port()).database(properties.database()).user(properties.username()).password(properties.password()))
				.create()
				.withMinimumIdle(2) // At least two pending connections have to be active in case one fails and shutdowns
				.withMaximumPoolSize(4) // 4 existing connections is the maximum allowed in case both idling connections are failing
				.build();
	}

}
