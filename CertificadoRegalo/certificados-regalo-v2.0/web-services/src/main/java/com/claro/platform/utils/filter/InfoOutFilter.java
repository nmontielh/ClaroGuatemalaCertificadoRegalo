package com.claro.platform.utils.filter;

import java.util.Arrays;
import java.util.List;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.filter.Filter;
import ch.qos.logback.core.spi.FilterReply;

/**
 * @see clase para filtrar los eventos de entrada/salida del logback
 * @author montieln
 *
 */
public class InfoOutFilter extends Filter<ILoggingEvent> {

	@Override
	public FilterReply decide(ILoggingEvent event) {

		if (!isStarted()) {
			return FilterReply.NEUTRAL;
		}

		List<Level> eventsToKeep = Arrays.asList(Level.TRACE, Level.DEBUG, Level.INFO);
		if (eventsToKeep.contains(event.getLevel())) {
			return FilterReply.ACCEPT;
		} else {
			return FilterReply.DENY;
		}
	}

}
