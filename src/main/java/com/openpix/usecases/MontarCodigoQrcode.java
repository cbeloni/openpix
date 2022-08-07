package com.openpix.usecases;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.openpix.domains.ChaveQrcode;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class MontarCodigoQrcode {
	
	private final ValidarChaveQrcode validarChaveQrcode;

	public String execute(ChaveQrcode chaveQrcode) {
		
		validarChaveQrcode.executar(chaveQrcode);
		
		String chavePixLength = StringUtils.leftPad(String.valueOf(chaveQrcode.getChavepix().length()), 2) ;
		String valorLength = StringUtils.leftPad(chaveQrcode.getValor().toString(), 2);
		
		return "00020126360014BR.GOV.BCB.PIX01" 
				+ chavePixLength 
				+ chaveQrcode.getChavepix()
				+ "52040000530398654"
				+ valorLength
				+ chaveQrcode.getValor().toString();			
	}

}
