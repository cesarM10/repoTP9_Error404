package ar.edu.unju.fi.tp6.service.imp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.tp6.model.Cliente;
import ar.edu.unju.fi.tp6.repository.IClienteRepository;
import ar.edu.unju.fi.tp6.service.IClienteService;

@Service("clienteServiceMysql")
public class ClienteServiceMysqlImp implements IClienteService{
	
	@Autowired
	private Cliente cliente;
	
	@Autowired
	private IClienteRepository clienteRepository;
	
	@Override
	public void generarTablaCliente() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public Cliente getCliente() {
		return cliente;
	}

	@Override
	public void agregarCliente(Cliente cliente) {
		clienteRepository.save(cliente);
		
	}

	@Override
	public List<Cliente> obtenerClientes() {
		List<Cliente> clientes = (List<Cliente>) clienteRepository.findAll();
		return clientes;
	}

	@Override
	public Optional<Cliente> getClientePorId(Long id) {
		Optional<Cliente> cliente = clienteRepository.findById(id);
		return cliente;
	}

	@Override
	public void eliminarCliente(Long id) {
		clienteRepository.deleteById(id);
		
	}

}
