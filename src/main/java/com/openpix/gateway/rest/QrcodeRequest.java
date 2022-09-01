package com.openpix.gateway.rest;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class QrcodeRequest {
	
	private String chavepix;
	private String nomeBeneficiario;
	private String 	cidadeBeneficiario;
	private BigDecimal valor;
	private String codigo; 

}
