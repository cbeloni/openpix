package com.openpix.usecases;

import static com.openpix.util.Constants.MENSAGEM_VALOR_MAXINMO;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import com.openpix.domains.ChaveQrcode;

@Component
public class ValidarChaveQrcode {
	
	public void executar(ChaveQrcode chaveQrcode) {
		if (chaveQrcode.getValor().compareTo(new BigDecimal(999.99)) > 0) {
			throw new RuntimeException(MENSAGEM_VALOR_MAXINMO);
		}
	}

}
