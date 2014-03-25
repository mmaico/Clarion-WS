package br.com.kohen.eventmanager.clarion.ws.utils;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CodeHandlerUtilsTest {

	@InjectMocks
	private CodeHandlerUtils utils;
	
	
	/**
	 *  : $Pedido já incluso : Empresa / Origem / Protheus / Externo - 01 / 2 / 065008 / 232 
	 */
	@Test
	public void shouldExtractValueWhenExternalSystemaResponse() {
		String returnExternalSystem = ": $Pedido já incluso : Empresa / Origem / Protheus / Externo - 01 / 2 / 065008 / 232";
		String codeExpected = "065008";
		String codeTreat = utils.extractValueFromResponde(returnExternalSystem);
		
		assertThat(codeTreat, is(codeExpected));
	}

}
