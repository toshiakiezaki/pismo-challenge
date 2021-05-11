package io.pismo.challenge.configuration;

import static java.util.Objects.isNull;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.dialect.H2Dialect;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.type.EnumType;

public class DatabaseEnumType extends EnumType {

	public void nullSafeSet(PreparedStatement st, Object value, int index, SharedSessionContractImplementor session) throws HibernateException, SQLException {
		if (isNull(value)) {
			st.setNull(index, Types.OTHER);
		} else if (isH2Database(session.getFactory().getProperties())) {
			st.setObject(index, value.toString(), Types.VARCHAR);
		} else {
			st.setObject(index, value.toString(), Types.OTHER);
		}
	}

	private boolean isH2Database(Map<String, Object> properties) {
		if (properties.containsKey("hibernate.dialect")) {
			var dialectClassName = properties.get("hibernate.dialect").toString();
			if (H2Dialect.class.getName().equals(dialectClassName)) {
				return true;
			}
		}
		return false;
	}

}
