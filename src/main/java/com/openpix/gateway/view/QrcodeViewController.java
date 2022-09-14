package com.openpix.gateway.view;

import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.openpix.domains.ChaveQrcode;
import com.openpix.gateway.rest.QrcodeRequest;
import com.openpix.usecases.GerarQrcode;
import com.openpix.usecases.MontarCodigoQrcode;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller
@RequestMapping
@CrossOrigin
@RequiredArgsConstructor
@Log4j2
public class QrcodeViewController {
	
	private final GerarQrcode gerarQrcode;
	private final MontarCodigoQrcode montarCodigoQrcode;
	
	@GetMapping
	public String viewTodos(Model model){
		log.trace("Carregando tela inicial");
		QrcodeRequest qrcode = new QrcodeRequest();
		model.addAttribute("qrcodeRequest", qrcode);
		return "index";
	}
	
	@PostMapping("/")
	public String salvar(@ModelAttribute QrcodeRequest qrcode, Model model){				
		log.info("qrcode: " + qrcode);
		ChaveQrcode chaveQrcode = ChaveQrcode.toDomain(qrcode);
		String codigoQrcode = montarCodigoQrcode.execute(chaveQrcode);
		
		model.addAttribute("qrcodeRequest", qrcode);
		model.addAttribute("codigoQrcode", codigoQrcode);
		return "index";
	}

}
