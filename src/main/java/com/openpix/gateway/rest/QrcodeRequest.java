package com.openpix.gateway.rest;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class QrcodeRequest {
	
	private String chavepix;
	private String nomeBeneficiario;
	private String 	cidadeBeneficiario;
	private BigDecimal valor;
	private String codigo; 

}
