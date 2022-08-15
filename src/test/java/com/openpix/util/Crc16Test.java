package com.openpix.util;

import static com.openpix.util.Crc16.TO_PIX_HEX;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class Crc16Test {
	
	private final String CODIGO_QRCODE = "00020126390014BR.GOV.BCB.PIX0117CBELONI@GMAIL.COM5204000053039865802BR5904caue6010santoandre62070503***6304";
	private final String DIGITO_ESPERADO = "41CE";
	
	@Test
	public void deveGerarCrc16() {
		String digito = TO_PIX_HEX(CODIGO_QRCODE);
		assertEquals(DIGITO_ESPERADO, digito);
	}

}
