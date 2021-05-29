package ar.edu.unju.fi.tp4.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import ar.edu.unju.fi.tp4.model.Producto;
import ar.edu.unju.fi.tp4.service.IProductoService;

@Controller
public class ProductoController {
	
	
	@Autowired
    private IProductoService productoService;
	
	@Autowired
    private Producto producto;
	
	//Formulario Producto
	@GetMapping("/index/producto")
	public String getPageProd(Model model) {
		model.addAttribute(producto);
		return "nuevoprod";
	}
	
	//Guardar Fomurlario Producto
	@PostMapping("/index/guardar")
	public String getProceso(@ModelAttribute("producto") Producto producto ) {
		productoService.agregarList(producto);
    	return "index";
	}
	
	//Mostrar Tabla Producto
	@GetMapping("/index/ultimo")
	public String getProcesoUltimo( Model model ) {
		model.addAttribute("prod",productoService.obtenerList());
    	return "mostrarprod";
	}
}
