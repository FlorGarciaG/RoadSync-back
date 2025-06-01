package lasalle.edu.mx.backroad.implement.service;

import lasalle.edu.mx.backroad.model.IncidenciaModel;
import lasalle.edu.mx.backroad.repository.IncidenciaRepository;
import lasalle.edu.mx.backroad.service.IncidenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IncidenciaServiceImpl implements IncidenciaService {
    @Autowired
    IncidenciaRepository incidenciaRepository;

    @Override
    public void registrarIncidencia (IncidenciaModel incidencia) {
        incidenciaRepository.save(incidencia);
    }

    @Override
    public List<IncidenciaModel> obtenerIncidencias () {
        return incidenciaRepository.findAll();
    }

    @Override
    public void eliminarIncidencia(Long idIncidencia) {
        incidenciaRepository.deleteById(idIncidencia);
    }

    @Override
    public Optional<IncidenciaModel> obtenerIncidenciaPorId(Long idIncidencia) {
        return incidenciaRepository.findById(idIncidencia);
    }

    @Override
    public void actualizarIncidencia(Long idIncidencia, IncidenciaModel inc) {
        Optional<IncidenciaModel> existente = incidenciaRepository.findById(idIncidencia);
        if (existente.isPresent()) {
            IncidenciaModel v = existente.get();
            v.setVehiculo(inc.getVehiculo() != null ? inc.getVehiculo() : v.getVehiculo());
            v.setFecha(inc.getFecha() != null ? inc.getFecha() : v.getFecha());
            v.setDescripcion(inc.getDescripcion() != null ? inc.getDescripcion() : v.getDescripcion());
            v.setTipoIncidencia(inc.getTipoIncidencia() != null ? inc.getTipoIncidencia() : v.getTipoIncidencia());

            incidenciaRepository.save(v);
        } else {
            throw new IllegalArgumentException("Incidencia no encontrada.");
        }
    }

    @Override
    public List<IncidenciaModel> obtenerIncidenciasPorPlaca(String placa) {
        return incidenciaRepository.findByVehiculoPlacaContainingIgnoreCase(placa);
    }
}
