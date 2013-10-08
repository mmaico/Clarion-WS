package br.com.kohen.eventmanager.clarion.ws.utils;

import static junit.framework.Assert.assertTrue;

import org.junit.Test;


public class ClarionWsResponseTest {

	@Test
	public void shouldReturnInvalidWhenValueReturnedIsNotPossibleToConvertToNumber() {
		ClarionWsResponse response = ClarionWsResponse.build().withValueReturned("XXXX9938839938 - ERROR");
		
		assertTrue(!response.isValid());
	}
	
	@Test
	public void shouldReturnTrueWhenValueReturnedIsValid() {
		ClarionWsResponse response = ClarionWsResponse.build().withValueReturned("993889");
		
		assertTrue(response.isValid());
	}

}
