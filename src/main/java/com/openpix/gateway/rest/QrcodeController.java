package com.openpix.gateway.rest;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Base64;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.zxing.WriterException;
import com.openpix.domains.ChaveQrcode;
import com.openpix.usecases.GerarQrcode;
import com.openpix.usecases.MontarCodigoQrcode;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("qrcode")
@RequiredArgsConstructor
public class QrcodeController {
	
	private final GerarQrcode gerarQrcode;
	
	private final MontarCodigoQrcode montarCodigoQrcode;
	
	@GetMapping(produces = MediaType.IMAGE_PNG_VALUE)
	@ResponseBody
	public byte[] getQrcode(@RequestParam(name = "chavePix", required = true) String chavePix,
			                @RequestParam(name = "nomeBeneficiario", required = true) String nomeBeneficiario,
			                @RequestParam(name = "valor", required = false, defaultValue = "0") BigDecimal valor,
			                @RequestParam(name = "codigo", required = true) String codigo) throws WriterException, IOException {
		
		ChaveQrcode chaveQrcode = ChaveQrcode
				.builder()
				.chavepix(chavePix)
				.nomeBeneficiario(nomeBeneficiario)
				.cidadeBeneficiario("Santo Andre")
				.valor(valor)
				.codigo(codigo).build();
		
		String qrcodeBase64 = gerarQrcode.execute(chaveQrcode, "UTF-8", 250, 250);
		return Base64.getDecoder().decode(qrcodeBase64);
	}
	
	@GetMapping(value= "/chave")
	@ResponseBody
	public String getChaveQrCode(@RequestParam(name = "chavePix", required = true) String chavePix,
				                 @RequestParam(name = "nomeBeneficiario", required = true) String nomeBeneficiario,
				                 @RequestParam(name = "valor", required = false, defaultValue = "0") BigDecimal valor,
				                 @RequestParam(name = "codigo", required = true) String codigo) throws WriterException, IOException {
		
		ChaveQrcode chaveQrcode = ChaveQrcode
				.builder()
				.chavepix(chavePix.toUpperCase())
				.nomeBeneficiario(nomeBeneficiario)
				.cidadeBeneficiario("Santo Andre")
				.valor(valor)
				.codigo(codigo).build();
				
		return montarCodigoQrcode.execute(chaveQrcode);
	}

}
