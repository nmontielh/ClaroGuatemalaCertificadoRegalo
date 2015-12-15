package com.claro.platform.utils.filter;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.filter.Filter;
import ch.qos.logback.core.spi.FilterReply;

/**
 * @see Filtros para los errores
 * @author montieln
 *
 */
public class ErrorOutFilter extends Filter<ILoggingEvent> {

	@Override
	public FilterReply decide(ILoggingEvent event) {

		if (!isStarted()) {
			return FilterReply.NEUTRAL;
		}

		if (Level.ERROR.equals(event.getLevel())) {
			return FilterReply.ACCEPT;
		} else {
			return FilterReply.DENY;
		}
	}

}
