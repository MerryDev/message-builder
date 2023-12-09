package net.quantrax.messagebuilder.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.ServiceLoader;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
final class Services0 {

	static <S> ServiceLoader<S> loader(final Class<S> type) {
		return ServiceLoader.load(type, type.getClassLoader());
	}

}
