package com.openpix.usecases;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.openpix.domains.ChaveQrcode;

@ExtendWith(MockitoExtension.class)
public class GerarQrcodeTest {
	
	@InjectMocks private GerarQrcode gerarQrcode;	
	@Spy private ValidarChaveQrcode validarChaveQrcode = new ValidarChaveQrcode();	
	@Spy private MontarCodigoQrcode montarCodigoQrcode = new MontarCodigoQrcode(validarChaveQrcode);
	
	@Test
	public void DeveGerarQrcode() throws WriterException, IOException {
		ChaveQrcode chaveQrCodeSucess = ChaveQrcode
				.builder()
				.chavepix("CBELONI@GMAIL.COM")
				.nomeBeneficiario("Caue Beloni")
				.cidadeBeneficiario("Santo Andre")
				.valor(new BigDecimal("9.99"))
				.codigo("pagamentoconta").build();
		
        String data = "www.geeksforgeeks.org";
        String charset = "UTF-8";
        Map<EncodeHintType, ErrorCorrectionLevel> hashMap = new HashMap<EncodeHintType, ErrorCorrectionLevel>();
        hashMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
        int height = 250;
        int width = 250;
		String qrcode = gerarQrcode.execute(chaveQrCodeSucess, charset, height, width);
		
		assertTrue(qrcode.length() > 0);
	}
}
