package br.com.kohen.eventmanager.clarion.ws.utils;

import static junit.framework.Assert.assertEquals;

import org.junit.Test;

public class StringSafeNullTest {

	@Test
	public void shouldReturnEmptyWhenValueIsNull() {
		String value = null;
		
		String safeNull = StringSafeNull.safeNull(value);
		
		assertEquals("", safeNull);
	}

	@Test
	public void shouldReturnOriginalValueWhenValueIsNotNull() {
		String value = "test";
		
		String safeNull = StringSafeNull.safeNull(value);
		
		assertEquals("test", safeNull);
	}
}
