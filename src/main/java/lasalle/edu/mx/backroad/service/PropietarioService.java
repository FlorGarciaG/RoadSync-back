package lasalle.edu.mx.backroad.service;

import lasalle.edu.mx.backroad.model.PropietarioModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface PropietarioService {
    void registrarPropietario(PropietarioModel propietario);
    List<PropietarioModel> obtenerPropietarios();
    Optional<PropietarioModel> obtenerPropietarioPorCurp(String curp);
    void eliminarPropietario(String curp);
    void actualizarPropietario(String curp, PropietarioModel propietario);
}
