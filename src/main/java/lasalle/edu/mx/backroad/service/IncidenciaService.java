package lasalle.edu.mx.backroad.service;

import lasalle.edu.mx.backroad.model.IncidenciaModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface IncidenciaService {
    void registrarIncidencia(IncidenciaModel incidencia);
    List<IncidenciaModel> obtenerIncidencias();
    Optional<IncidenciaModel> obtenerIncidenciaPorId(Long idIncidencia);
    void eliminarIncidencia(Long idIncidencia);
    void actualizarIncidencia(Long idIncidencia, IncidenciaModel incidencia);
    List<IncidenciaModel> obtenerIncidenciasPorPlaca(String placa);
}
