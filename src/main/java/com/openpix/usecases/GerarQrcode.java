package com.openpix.usecases;

import java.awt.image.BufferedImage;
import java.io.IOException;

import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Component;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.openpix.util.ImageUtils;

@Component
public class GerarQrcode {

	public String execute(String data, String charset, int height, int width)
			throws WriterException, IOException {
		BitMatrix matrix = new MultiFormatWriter().encode(new String(data.getBytes(charset), charset),
				BarcodeFormat.QR_CODE, width, height);

		BufferedImage qrcode = MatrixToImageWriter.toBufferedImage(matrix);
		
		byte[] qrcodeBytes = ImageUtils.toByteArray(qrcode, "png");
        String qrcodeBase64 = Base64.encodeBase64String(qrcodeBytes);

		return qrcodeBase64;
	}

}
