package com.openpix.domains;

import java.math.BigDecimal;

import com.openpix.gateway.rest.QrcodeRequest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChaveQrcode {
	
	private String chavepix;
	private String nomeBeneficiario;
	private String 	cidadeBeneficiario;
	private BigDecimal valor;
	private String codigo; 
	
	public static ChaveQrcode toDomain(QrcodeRequest qrcode) {
		return ChaveQrcode.builder().chavepix(qrcode.getChavepix())
				.nomeBeneficiario(qrcode.getNomeBeneficiario())
				.cidadeBeneficiario(qrcode.getCidadeBeneficiario() != null ? qrcode.getCidadeBeneficiario() : "")
				.valor(qrcode.getValor())
				.codigo(qrcode.getCodigo()).build();
	}

}
