package ar.edu.unju.fi.tp6.controller;

import java.util.List;
import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.fi.tp6.model.Beneficio;
import ar.edu.unju.fi.tp6.model.Cliente;
import ar.edu.unju.fi.tp6.service.IBeneficioService;
import ar.edu.unju.fi.tp6.service.IClienteService;
import ar.edu.unju.fi.tp6.service.imp.CompraServiceImp;

@Controller
public class ClienteController {
	private static final Log LOGGER = LogFactory.getLog(CompraServiceImp.class);

	@Autowired
	@Qualifier("clienteServiceMysql")
	private IClienteService clienteService;
	
	@Autowired
	private Beneficio beneficio;
	
	@Autowired
	private Cliente cliente;
	
	@Autowired
	@Qualifier("beneficioServiceMysql")
	private IBeneficioService beneficioService;
	
	@GetMapping("/cliente/nuevo")
	public String getNuevoClientePage(Model model){
		model.addAttribute("cliente", cliente);
		//lista que almacena los beneficio a asignar en el cliente.
		model.addAttribute("beneficiosSeleccionados", beneficioService.listBeneficiosSeleccionados());
		//lista que recupera los beneficos en de la tabla beneficios.
		model.addAttribute("beneficios", beneficioService.obtenerBeneficios());
		//entidad beneficio para realizar la agregacion a beneficiosSeleccionados
		model.addAttribute("beneficio", beneficio);
		return "nuevocliente";
	}
	
	@PostMapping("/cliente/guardar")
	public ModelAndView agregarClientePage(@Valid @ModelAttribute("cliente")Cliente cliente, @ModelAttribute("beneficio")Beneficio beneficio, BindingResult resultadoValidacion) {
		ModelAndView model;
		if(resultadoValidacion.hasErrors()) { //encontró errores.
			model = new ModelAndView("nuevocliente");
			
			model.addObject("cliente", cliente);
			//lista que almacena los beneficio a asignar en el cliente.
			model.addObject("beneficiosSeleccionados", beneficioService.listBeneficiosSeleccionados());
			//lista que recupera los beneficos en de la tabla beneficios.
			model.addObject("beneficios", beneficioService.obtenerBeneficios());
			//entidad beneficio para realizar la agregacion a beneficiosSeleccionados
			model.addObject("beneficio", beneficio);
			
			return model;
		}else { //no encontró errores.
			model = new ModelAndView("clientes");
			cliente.setBeneficios(beneficioService.listBeneficiosSeleccionados());
			
			clienteService.agregarCliente(cliente);
			
			model.addObject("cliente", clienteService.obtenerClientes());
			return model;
		}
	}
	
	@GetMapping("/cliente/listado")
	public ModelAndView getClientesPage() {
		ModelAndView model = new ModelAndView("clientes");
		
		
		model.addObject("cliente",clienteService.obtenerClientes());
		
		return model;
	}
	
	@GetMapping("/cliente/editar/{id}")
	public ModelAndView getClienteEditPage(@PathVariable(value = "id")Long id) {
		LOGGER.info("METODO - - EDITAR CLIENTE");
		ModelAndView model = new ModelAndView("nuevocliente");
				
		List<Cliente> clientes = (List<Cliente>) clienteService.obtenerClientes();
		
		for (Cliente clienteRepo : clientes) {
			if(clienteRepo.getId() == id) {
				//lista que almacena los beneficio a asignar en el cliente.
				model.addObject("beneficiosSeleccionados", clienteRepo.getBeneficios());
				model.addObject("cliente", clienteRepo);
			}
		}
		
		
		
		//lista que recupera los beneficos en de la tabla beneficios.
		model.addObject("beneficios", beneficioService.obtenerBeneficios());
		//entidad beneficio para realizar la agregacion a beneficiosSeleccionados
		model.addObject("beneficio", beneficio);
		
		return model;
	}
	
	@GetMapping("/cliente/eliminar/{id}")
	public ModelAndView getClienteDeletePage(@PathVariable(value = "id")Long id) {
		ModelAndView model = new ModelAndView("redirect:/cliente/listado");
		clienteService.eliminarCliente(id);
		
		return model;
	}
	
}