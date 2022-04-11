package com.lig.feignlogging.util;

import static feign.Util.UTF_8;
import static feign.Util.decodeOrDefault;
import static feign.Util.valuesOrEmpty;

import feign.Request;
import feign.Response;
import feign.Util;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomLogger extends feign.Logger {

	public CustomLogger() {
	}

	@Override
	protected void logRequest(String configKey, Level logLevel, Request request) {
		this.logRequestCustom(configKey, logLevel, request);
	}

	/**
	 * refactored to single line {@link feign.Logger#logRequest}
	 */
	private void logRequestCustom(String configKey, Level logLevel, Request request) {
		StringBuilder sb = new StringBuilder();
		String protocolVersion = resolveProtocolVersion(request.protocolVersion());

		sb.append('\n')
				.append("---> ")
				.append(request.httpMethod().name())
				.append(" ")
				.append(request.url())
				.append(" ")
				.append(protocolVersion);
		if (logLevel.ordinal() >= Level.HEADERS.ordinal()) {

			for (String field : request.headers().keySet()) {
				if (shouldLogRequestHeader(field)) {
					for (String value : valuesOrEmpty(request.headers(), field)) {
						sb.append('\n').append(field).append(": ").append(value);
					}
				}
			}

			int bodyLength = 0;
			if (request.body() != null) {
				bodyLength = request.length();
				if (logLevel.ordinal() >= Level.FULL.ordinal()) {
					String bodyText =
							request.charset() != null
									? new String(request.body(), request.charset())
									: null;
					sb.append('\n'); // CRLF
					sb.append('\n').append(bodyText != null ? bodyText : "Binary data");
				}
			}
			sb.append('\n').append("---> END HTTP (").append(bodyLength).append("-byte body)");
			log(configKey, "%s", sb.toString());
		}
	}

	@Override
	protected Response logAndRebufferResponse(String configKey,
			Level logLevel,
			Response response,
			long elapsedTime)
			throws IOException {
		return customLogAndRebufferResponse(configKey, logLevel, response, elapsedTime);
	}

	/**
	 * refactored to single line {@link feign.Logger#logAndRebufferResponse}
	 */
	private Response customLogAndRebufferResponse(String configKey,
			Level logLevel,
			Response response,
			long elapsedTime)
			throws IOException {
		StringBuilder sb = new StringBuilder();
		String protocolVersion = resolveProtocolVersion(response.protocolVersion());
		String reason =
				response.reason() != null && logLevel.compareTo(Level.NONE) > 0 ? " " + response.reason()
						: "";
		int status = response.status();
		sb
				.append('\n')
				.append("<--- ")
				.append(protocolVersion)
				.append(" ")
				.append(status)
				.append(reason)
				.append(" (")
				.append(elapsedTime)
				.append("ms)");
		if (logLevel.ordinal() >= Level.HEADERS.ordinal()) {

			for (String field : response.headers().keySet()) {
				if (shouldLogResponseHeader(field)) {
					for (String value : valuesOrEmpty(response.headers(), field)) {
						sb.append('\n').append(field).append(": ").append(value);
					}
				}
			}

			int bodyLength = 0;
			if (response.body() != null && !(status == 204 || status == 205)) {
				// HTTP 204 No Content "...response MUST NOT include a message-body"
				// HTTP 205 Reset Content "...response MUST NOT include an entity"
				if (logLevel.ordinal() >= Level.FULL.ordinal()) {
					log(configKey, ""); // CRLF
				}
				byte[] bodyData = Util.toByteArray(response.body().asInputStream());
				bodyLength = bodyData.length;
				if (bodyLength > 0) {
					sb.append('\n').append(decodeOrDefault(bodyData, UTF_8, "Binary data"));
				}
				sb.append('\n').append("<--- END HTTP (").append(bodyLength).append("-byte body)");
				log(configKey, "%s", sb.toString());
				return response.toBuilder().body(bodyData).build();
			} else {
				sb.append('\n').append("<--- END HTTP (").append(bodyLength).append("-byte body)");
				log(configKey, "%s", sb.toString());
			}
		}
		return response;
	}

	@Override
	protected void log(String configKey, String format, Object... args) {
		// Not using SLF4J's support for parameterized messages (even though it would be more efficient)
		// because it would
		// require the incoming message formats to be SLF4J-specific.
		log.trace(String.format(methodTag(configKey) + format, args));
	}

}