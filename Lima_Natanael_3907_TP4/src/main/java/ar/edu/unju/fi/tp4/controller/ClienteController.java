package ar.edu.unju.fi.tp4.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.fi.tp4.model.Cliente;
import ar.edu.unju.fi.tp4.service.IClienteService;

@Controller
public class ClienteController {
	@Autowired
	private Cliente cliente;

	@Autowired
	@Qualifier("tableService")
	private IClienteService clienteService;

	// Mostrar la pagina formulario Cliente
	@GetMapping("/index/cliente")
	public String getPageMain(Model model) {
		model.addAttribute(cliente);
		return "nuevocliente";
	}

	// Guarda el formulario Cliente
	@PostMapping("/index/guardarcliente")
	public ModelAndView getProcesoGuardar(@ModelAttribute("cliente") Cliente cliente) {
		ModelAndView model = new ModelAndView("index");
		clienteService.agregarCliente(cliente);
		model.addObject("clientes", clienteService.obtenerCliente());
		return model;
	}

	// Muestra la tabla de Clientes
	@GetMapping("/index/listado")
	public ModelAndView getProcesoListado() {
		ModelAndView model = new ModelAndView("mostrarclientes");

		if (clienteService.obtenerCliente() == null) {
			clienteService.generarList();
		}

		model.addObject("clientes", clienteService.obtenerCliente());
		return model;
	}

}
