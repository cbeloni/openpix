package com.openpix.domains;

import java.math.BigDecimal;

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

}
