package org.projetx.core;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;

@ConfigurationProperties("service")
@PropertySource("ressources.properties")
public class ServiceProperties {

	/**
	 * A message for the service.
	 */
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
