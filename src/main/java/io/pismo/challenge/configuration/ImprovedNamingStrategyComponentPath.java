package io.pismo.challenge.configuration;

import static java.lang.Character.isLowerCase;
import static java.lang.Character.isUpperCase;

import java.util.Locale;

import org.hibernate.boot.model.naming.EntityNaming;
import org.hibernate.boot.model.naming.ImplicitNamingStrategyComponentPathImpl;
import org.hibernate.boot.model.source.spi.AttributePath;

public class ImprovedNamingStrategyComponentPath extends ImplicitNamingStrategyComponentPathImpl {

	@Override
	protected String transformEntityName(EntityNaming entityNaming) {
		return addUnderscores(super.transformEntityName(entityNaming));
	}

	@Override
	protected String transformAttributePath(AttributePath attributePath) {
		return addUnderscores(super.transformAttributePath(attributePath));
	}

	private String addUnderscores(String attributePath) {
		var buf = new StringBuilder(attributePath.replace('.', '_'));
		for (var i = 1; i < buf.length() - 1; ++i) {
			if (isLowerCase(buf.charAt(i - 1)) && isUpperCase(buf.charAt(i)) && isLowerCase(buf.charAt(i + 1))) {
				buf.insert(i++, '_');
			}
		}
		return buf.toString().toLowerCase(Locale.ROOT);
	}

}
