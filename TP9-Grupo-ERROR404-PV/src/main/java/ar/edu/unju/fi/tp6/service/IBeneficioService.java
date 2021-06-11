package ar.edu.unju.fi.tp6.service;

import java.util.List;
import java.util.Optional;

import ar.edu.unju.fi.tp6.model.Beneficio;

public interface IBeneficioService {

	public void agregarBeneficio(Beneficio beneficio);
	
	public Beneficio getBeneficio();
	
	public List<Beneficio> listBeneficiosSeleccionados();
	
	public List<Beneficio> obtenerBeneficios();
	
	public Optional<Beneficio> getBeneficioPorId(Long id);
	
	public void agregarBeneficioEncontrado(Beneficio beneficioEncontrado);
	
	public void quitarBeneficioListaSeleccionados(Long id);
}
