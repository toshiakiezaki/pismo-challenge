package io.pismo.challenge.configuration;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;
import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;
import static com.fasterxml.jackson.databind.SerializationFeature.WRITE_DATES_AS_TIMESTAMPS;

import javax.enterprise.event.Observes;
import javax.inject.Singleton;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import io.quarkus.jackson.ObjectMapperCustomizer;
import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;

@Singleton
public class JacksonObjectMapperCustomizer implements ObjectMapperCustomizer {

	public static ObjectMapper objectMapper;

	void onStart(@Observes StartupEvent event, ObjectMapper objectMapper) {
		JacksonObjectMapperCustomizer.objectMapper = objectMapper;
	}

	void onStop(@Observes ShutdownEvent event) {
		JacksonObjectMapperCustomizer.objectMapper = null;
	}

	@Override
	public void customize(ObjectMapper objectMapper) {
		objectMapper.configure(FAIL_ON_UNKNOWN_PROPERTIES, false);
		objectMapper.disable(WRITE_DATES_AS_TIMESTAMPS);
		objectMapper.setSerializationInclusion(NON_NULL);

		objectMapper.registerModule(new JavaTimeModule());
		objectMapper.registerModule(new Jdk8Module());
	}

}
