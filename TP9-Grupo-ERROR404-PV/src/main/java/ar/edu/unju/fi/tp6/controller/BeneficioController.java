package ar.edu.unju.fi.tp6.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.fi.tp6.model.Beneficio;
import ar.edu.unju.fi.tp6.model.Cliente;
import ar.edu.unju.fi.tp6.service.IBeneficioService;
import ar.edu.unju.fi.tp6.service.IClienteService;

@Controller
public class BeneficioController {
	
	@Autowired
	private Beneficio beneficio;
	
	@Autowired
	private Cliente cliente;
	
	@Autowired
	@Qualifier("clienteServiceMysql")
	private IClienteService clienteService;
	
	@Autowired
	@Qualifier("beneficioServiceMysql")
	private IBeneficioService beneficioService;
	
	@GetMapping("/beneficio/busqueda")
	public String seleccionarBeneficioPorId(@RequestParam(name = "id")Long id, Model model, @ModelAttribute(name = "beneficio")Beneficio beneficio,@ModelAttribute(name = "cliente")Cliente cliente) {
		List<Beneficio> beneficios = (List<Beneficio>) beneficioService.obtenerBeneficios();
				
		for (Beneficio beneficioDeLista : beneficios) {
			if(beneficioDeLista.getId() == beneficio.getId()) {
				beneficio = beneficioDeLista;
				}
		}
		
		beneficioService.agregarBeneficioEncontrado(beneficio);
		
		model.addAttribute("beneficiosSeleccionados", beneficioService.listBeneficiosSeleccionados());
		model.addAttribute("beneficios", beneficioService.obtenerBeneficios());
		model.addAttribute("cliente", cliente);
		//lista que almacena los beneficio a asignar en el cliente.
		model.addAttribute("beneficiosSeleccionados", beneficioService.listBeneficiosSeleccionados());
		return "nuevocliente";
	}
	
	@GetMapping("/beneficio/quitaropcion/{id}")
	public ModelAndView quitarOpcionListaBeneficiosSeleccionados(@PathVariable(name = "id")Long id) {
		ModelAndView model = new ModelAndView("nuevocliente");
		beneficioService.quitarBeneficioListaSeleccionados(id);
		model.addObject("cliente", cliente);
		//lista que almacena los beneficio a asignar en el cliente.
		model.addObject("beneficiosSeleccionados", beneficioService.listBeneficiosSeleccionados());
		//lista que recupera los beneficos en de la tabla beneficios.
		model.addObject("beneficios", beneficioService.obtenerBeneficios());
		//entidad beneficio para realizar la agregacion a beneficiosSeleccionados
		model.addObject("beneficio", beneficio);
		return model;
	}
	
	@GetMapping("/beneficios/lista")
	public String getBeneficiosPage(Model model) {
		model.addAttribute("beneficios", beneficioService.obtenerBeneficios());
		return "lista-beneficio";
	}
	
}
