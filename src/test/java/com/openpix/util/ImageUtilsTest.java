package com.openpix.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import javax.imageio.ImageIO;

import org.apache.commons.codec.binary.Base64;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import com.google.zxing.WriterException;

@ExtendWith(MockitoExtension.class)
public class ImageUtilsTest {
	
	@InjectMocks 
	private ImageUtils imageUtils;
	
	@Test
	public void deveLerImagem() throws WriterException, IOException, URISyntaxException {
		File qrcodeSamplePath = new File("./src/test/resources/qrcode-sample.png");;
		BufferedImage bi = ImageIO.read(qrcodeSamplePath);
        byte[] bytes = imageUtils.toByteArray(bi, "png");
        String bytesBase64 = Base64.encodeBase64String(bytes);
        
		assertEquals(512, bytesBase64.toString().length());
	}
	
	@Test
	public void deveGravarImagem() throws IOException {
		File qrcodeSamplePath = new File("./src/test/resources/qrcode-sample.png");
		BufferedImage bi = ImageIO.read(qrcodeSamplePath);
        byte[] bytes = imageUtils.toByteArray(bi, "png");
        String bytesBase64 = Base64.encodeBase64String(bytes);
        
		byte[] bytesFromDecode = Base64.decodeBase64(bytesBase64);
        BufferedImage newBi = imageUtils.toBufferedImage(bytesFromDecode);
        ImageIO.write(newBi, "png", new File("./src/test/resources/qrcode-decode.png"));
        
        File qrcodeGravadoPath = new File("./src/test/resources/qrcode-decode.png");
        
        assertTrue(qrcodeGravadoPath.canRead());
	}

}
