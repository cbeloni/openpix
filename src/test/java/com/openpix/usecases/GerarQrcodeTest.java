package com.openpix.usecases;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

@ExtendWith(MockitoExtension.class)
public class GerarQrcodeTest {
	
	@InjectMocks 
	private GerarQrcode gerarQrcode;
	
	@Test
	public void DeveGerarQrcode() throws WriterException, IOException {
        String data = "www.geeksforgeeks.org";
        String charset = "UTF-8";
        Map<EncodeHintType, ErrorCorrectionLevel> hashMap = new HashMap<EncodeHintType, ErrorCorrectionLevel>();
        hashMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
        int height = 250;
        int width = 250;
		String qrcode = gerarQrcode.execute(data, charset, height, width);
		
		assertTrue(qrcode.length() == 512);
	}
}
