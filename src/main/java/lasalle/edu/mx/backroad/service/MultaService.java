package lasalle.edu.mx.backroad.service;

import lasalle.edu.mx.backroad.model.MultaModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface MultaService {
    void registrarMulta(MultaModel multa);
    List<MultaModel> obtenerMultas();
    Optional<MultaModel> obtenerMultaPorId(Long idMulta);
    void eliminarMulta(Long idMulta);
    void actualizarMulta(Long idMulta, MultaModel multa);
    List<MultaModel> obtenerMultasPorPlaca(String placa);
}
