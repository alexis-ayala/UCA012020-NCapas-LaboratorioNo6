package com.uca.capas.controller;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.uca.capas.domain.Contribuyente;
import com.uca.capas.domain.Importancia;
import com.uca.capas.service.ContribuyenteService;
import com.uca.capas.service.ImportanciaService;


@Controller
public class MainController {
Logger log = Logger.getLogger(MainController.class.getName());
	
	@Autowired 
	private ContribuyenteService contribuyenteService;
	
	@Autowired 
	private ImportanciaService importanciaService;
	
	
	@GetMapping("/")	//Página No 1
	public ModelAndView initMain() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("contribuyente", new Contribuyente());
		mav.addObject("importancias", importanciaService.findAll());
		mav.setViewName("index");
		return mav;
	}
	
	//********* Guardar contribuyente en la base de datos *********
	@PostMapping("/registrar")
	public ModelAndView registrar(@Valid @ModelAttribute Contribuyente contribuyente, BindingResult result) {
		ModelAndView mav = new ModelAndView();
		if(result.hasErrors()) { 
			try {
				mav.addObject("importancias",importanciaService.findAll());
				mav.setViewName("index");
			}catch(Exception e) {
				e.printStackTrace();
			}
			
			log.info("Error encontrado");
		}else {	
			try {
				log.info("Contribuyente agregado");
				ZoneId defaultZoneId = ZoneId.systemDefault();
				LocalDate today = LocalDate.now();
				Date date = Date.from(today.atStartOfDay(defaultZoneId).toInstant());
				contribuyente.setSfechaingreso(date);
				contribuyente.getSfechaingreso();
			
				contribuyenteService.save(contribuyente);
				mav.addObject("message", "Contribuyente guardado con éxito");
				mav.setViewName("registrado");
			}catch(Exception ex) {
				log.info("No se pudo agregar");
			}
			
		}
		return mav;
	
	}
	
	@GetMapping("/vercontribuyente")
	public ModelAndView vercontribuyente() {		
		ModelAndView mav = new ModelAndView("listado");
		mav.addObject("contribuyentes", contribuyenteService.findAll());
		return mav;
	}
}


