package com.openpix.gateway.rest;

import java.io.IOException;
import java.util.Base64;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.zxing.WriterException;
import com.openpix.usecases.GerarQrcode;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("qrcode")
@RequiredArgsConstructor
public class QrcodeController {
	
	private final GerarQrcode gerarQrcode;
	
	@GetMapping(produces = MediaType.IMAGE_PNG_VALUE)
	@ResponseBody
	public byte[] getQrcode(@RequestParam(name = "chavePix", required = true) String chavePix) throws WriterException, IOException {
		String qrcodeBase64 = gerarQrcode.execute(chavePix, "UTF-8", 250, 250);
		return Base64.getDecoder().decode(qrcodeBase64);
	}

}
