package ar.edu.unju.fi.tp6.service.imp;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.tp6.model.Beneficio;
import ar.edu.unju.fi.tp6.repository.IBeneficioRepository;
import ar.edu.unju.fi.tp6.service.IBeneficioService;

@Service("beneficioServiceMysql")
public class BeneficioServiceMysqlImp implements IBeneficioService{
	
	List<Beneficio> beneficiosSeleccionados = new ArrayList<Beneficio>();
	
	@Autowired
	private Beneficio beneficio;
	
	@Autowired
	private IBeneficioRepository beneficioRepository;
	
	@Override
	public void agregarBeneficio(Beneficio beneficio) {
		beneficioRepository.save(beneficio);
	}

	@Override
	public Beneficio getBeneficio() {
		return beneficio;
	}

	@Override
	public List<Beneficio> listBeneficiosSeleccionados() {
		
		return beneficiosSeleccionados;
	}

	@Override
	public List<Beneficio> obtenerBeneficios() {
		List<Beneficio> beneficios = (List<Beneficio>)beneficioRepository.findAll();
		return beneficios;
	}

	@Override
	public Optional<Beneficio> getBeneficioPorId(Long id) {
		Optional<Beneficio> beneficio = beneficioRepository.findById(id);
		return beneficio;
	}

	@Override
	public void agregarBeneficioEncontrado(Beneficio beneficioEncontrado) {
		beneficiosSeleccionados.add(beneficioEncontrado);
	}

	public void quitarBeneficioListaSeleccionados(Long id) {
		for (int i = 0; i < beneficiosSeleccionados.size(); i++) {
			if(beneficiosSeleccionados.get(i).getId() == id){
				beneficiosSeleccionados.remove(i);
			}
		}
	}
}
