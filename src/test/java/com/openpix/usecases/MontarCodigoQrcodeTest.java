package com.openpix.usecases;

import static com.openpix.util.Constants.MENSAGEM_VALOR_MAXINMO;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import com.openpix.domains.ChaveQrcode;

@ExtendWith(MockitoExtension.class)
public class MontarCodigoQrcodeTest {
	
	private final String CODIGO_QRCODE_ESPERADO = "00020126390014BR.GOV.BCB.PIX0117CBELONI@GMAIL.COM52040000530398654049.995802BR5911Caue Beloni6011Santo Andre62180514pagamentoconta63040787";
	private final String CODIGO_QRCODE_ESPERADO_SEM_VALOR = "00020126390014BR.GOV.BCB.PIX0117CBELONI@GMAIL.COM5204000053039865802BR5911Caue Beloni6011Santo Andre62180514pagamentoconta6304F916";
	@InjectMocks
	private MontarCodigoQrcode montarCodigoQrcode;
	@Spy
	private ValidarChaveQrcode validarChaveQrcode;
	private ChaveQrcode chaveQrCodeSucess;
	private ChaveQrcode chaveQrCodeValueMax;
	private ChaveQrcode chaveQrCodeSemValor;
	
	@BeforeEach
	public void init() {
		chaveQrCodeSucess = ChaveQrcode
				.builder()
				.chavepix("CBELONI@GMAIL.COM")
				.nomeBeneficiario("Caue Beloni")
				.cidadeBeneficiario("Santo Andre")
				.valor(new BigDecimal("9.99"))
				.codigo("pagamentoconta").build();
		
		chaveQrCodeValueMax = ChaveQrcode
				.builder()
				.chavepix("CBELONI@GMAIL.COM")
				.nomeBeneficiario("Caue Beloni")
				.cidadeBeneficiario("Santo Andre")
				.valor(new BigDecimal("9999.99"))
				.codigo("pagamentoconta").build();
		
		chaveQrCodeSemValor = ChaveQrcode
				.builder()
				.chavepix("CBELONI@GMAIL.COM")
				.nomeBeneficiario("Caue Beloni")
				.cidadeBeneficiario("Santo Andre")
				.valor(new BigDecimal("0"))
				.codigo("pagamentoconta").build();
	}
	
	@Test
	public void DeveMontarCodigoQrCode() {				
		String codigoQrcode = montarCodigoQrcode.execute(this.chaveQrCodeSucess);		
		assertEquals(CODIGO_QRCODE_ESPERADO, codigoQrcode);		
	}
	
	@Test
	public void DeveMontarCodigoQrCodeSemValor() {
		String codigoQrcode = montarCodigoQrcode.execute(this.chaveQrCodeSemValor);		
		assertEquals(CODIGO_QRCODE_ESPERADO_SEM_VALOR, codigoQrcode);
	}
	
	@Test
	public void DeveGerarExceptionValorMaximo() {
				
		Exception exception = assertThrows(RuntimeException.class, () -> {
			montarCodigoQrcode.execute(this.chaveQrCodeValueMax);
	    });

	    String actualMessage = exception.getMessage();

	    assertTrue(actualMessage.contains(MENSAGEM_VALOR_MAXINMO));
		
	}

}
