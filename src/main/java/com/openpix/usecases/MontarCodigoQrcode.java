package com.openpix.usecases;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.openpix.domains.ChaveQrcode;
import com.openpix.util.Crc16;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class MontarCodigoQrcode {
	
	private final ValidarChaveQrcode validarChaveQrcode;

	public String execute(ChaveQrcode chaveQrcode) {
		
		validarChaveQrcode.executar(chaveQrcode);
		
		String chavePixPrefixLength = StringUtils.leftPad(String.valueOf(chaveQrcode.getChavepix().length()+22), 2, '0') ;
		String chavePixLength = StringUtils.leftPad(String.valueOf(chaveQrcode.getChavepix().length()), 2, '0') ;
		String valorLength = StringUtils.leftPad(String.valueOf(chaveQrcode.getValor().toString().length()), 2, '0');
		String beneficiarioLength = StringUtils.leftPad(String.valueOf(chaveQrcode.getNomeBeneficiario().length()), 2, '0');
		String cidadeLength = StringUtils.leftPad(String.valueOf(chaveQrcode.getCidadeBeneficiario().length()), 2, '0');
		String codigoLength = StringUtils.leftPad(String.valueOf(chaveQrcode.getCodigo().length()), 2, '0');
		
		String chave = "00020126"
				+ chavePixPrefixLength
				+ "0014BR.GOV.BCB.PIX01" 
				+ chavePixLength 
				+ chaveQrcode.getChavepix()
				+ "520400005303986"
				+ (chaveQrcode.getValor().longValue() > 0L ? "54"+valorLength : "")
				+ (chaveQrcode.getValor().longValue() > 0L ? chaveQrcode.getValor().toString() : "")
				+ "5802BR"
				+ "59" + beneficiarioLength
				+ chaveQrcode.getNomeBeneficiario()
				+ "60"
				+ cidadeLength
				+ chaveQrcode.getCidadeBeneficiario()
				+ "6218"
				+ "05" + codigoLength
				+ chaveQrcode.getCodigo()
				+ "6304";
		return  chave + Crc16.TO_PIX_HEX(chave);

	}

}
