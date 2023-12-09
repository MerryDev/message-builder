package net.quantrax.messagebuilder.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.Optional;
import java.util.ServiceLoader;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Services {

	/**
	 * Retrieves a service implementation of the specified type.
	 *
	 * @param <P>  the type of the service
	 * @param type the class object representing the type of the service
	 * @return an {@link Optional} containing the service implementation, or an empty {@link Optional} if no implementation is found
	 * @throws IllegalStateException if multiple implementations of the service are found
	 */
	public static <P> @NotNull Optional<P> service(final @NotNull Class<P> type) {
		final ServiceLoader<P> loader = Services0.loader(type);
		final Iterator<P> it = loader.iterator();

		while (it.hasNext()) {
			final P instance;

			try {
				instance = it.next();
			} catch (final Throwable throwable) {
				continue;
			}

			if (it.hasNext()) {
				throw new IllegalStateException("Expected to find one service %s, found multiple".formatted(type));
			}
			return Optional.of(instance);
		}

		return Optional.empty();
	}

}
