package lasalle.edu.mx.backroad.implement.service;

import lasalle.edu.mx.backroad.model.MultaModel;
import lasalle.edu.mx.backroad.repository.MultaRepository;
import lasalle.edu.mx.backroad.service.MultaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class MultaServiceImpl implements MultaService {
    @Autowired
    private MultaRepository multaRepository;

    @Override
    public void registrarMulta (MultaModel multa) {
        multaRepository.save(multa);
    }

    @Override
    public List<MultaModel> obtenerMultas() {
        return multaRepository.findAll();
    }

    @Override
    public Optional<MultaModel> obtenerMultaPorId(Long id) {
        return multaRepository.findById(id);
    }

    @Override
    public void eliminarMulta(Long id) {
        multaRepository.deleteById(id);
    }

    @Override
    public void actualizarMulta(Long id, MultaModel multa) {
        Optional<MultaModel> existente = multaRepository.findById(id);
        if (existente.isPresent()) {
            MultaModel v = existente.get();
            v.setTipoMulta(multa.getTipoMulta() != null ? multa.getTipoMulta() : v.getTipoMulta());
            v.setFecha(multa.getFecha() != null ? multa.getFecha() : v.getFecha());
            v.setMonto(multa.getMonto() != null ? multa.getMonto() : v.getMonto());
            v.setVehiculo(multa.getVehiculo() != null ? multa.getVehiculo() : v.getVehiculo());

            multaRepository.save(v);
        }else {
            throw new IllegalArgumentException("Vehículo no encontrado.");
        }
    }

    @Override
    public List<MultaModel> obtenerMultasPorPlaca(String placa) {
        return multaRepository.findByVehiculoPlacaContainingIgnoreCase(placa);
    }

}
