package lasalle.edu.mx.backroad.service;
import lasalle.edu.mx.backroad.model.catalogoMultaModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface catalogoMultaService {
    void registrarElemento (catalogoMultaModel catalogoMultaModel);
    void eliminarElemento (Long idElemento);
    void actualizarElemento (catalogoMultaModel catalogoMultaModel, Long idElemento);
    List<catalogoMultaModel> obtenerCatalogoMultas();
}
