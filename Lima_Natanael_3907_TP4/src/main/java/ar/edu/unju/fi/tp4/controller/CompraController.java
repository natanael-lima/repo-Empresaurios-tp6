package ar.edu.unju.fi.tp4.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.fi.tp4.model.Compra;
import ar.edu.unju.fi.tp4.service.ICompraService;
import ar.edu.unju.fi.tp4.service.IProductoService;

@Controller
public class CompraController {
	
	@Autowired
	private Compra compra;
	
	@Autowired
	@Qualifier("tableCompra")
	private ICompraService compraService;
	
	@Autowired
    private IProductoService productoService;
	
	//Formulario Compra
	@GetMapping("/index/compra")
	public String getFormCompra(Model model) {
		String text="";
		String textProd=productoService.mostrarUltimoProducto().getNombre();
		model.addAttribute(compra);
		model.addAttribute("textProd",textProd);
		if(productoService.obtenerListaProducto().size()==1) {
		   text="No ingreso un producto";
		   model.addAttribute("text",text);
		}
		
		return "nuevacompra";
	}
	//Guardar Formulario Compra
	@PostMapping("/index/guardarCompra")
	public ModelAndView getGuardarCompra(@ModelAttribute("compra") Compra compra) {
		ModelAndView model = new ModelAndView("index");
		compra.setProducto(productoService.mostrarUltimoProducto());
		compraService.agregarCompra(compra);
		//model.addObject("compras",compraService.obtenerCompras());
		return model;
	}
	//Mostrar tabla Compra
	@GetMapping("/index/listadoCompra")
	 public ModelAndView getCompraListado() {
		 ModelAndView model = new ModelAndView("mostrarcompra");
		
		if(compraService.obtenerCompras() == null) {
			 compraService.generarListaCompras();
		}
		
		model.addObject("compras",compraService.obtenerCompras());
		return model;
  	}
}
