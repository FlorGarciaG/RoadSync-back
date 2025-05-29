package lasalle.edu.mx.backroad.implement.service;

import lasalle.edu.mx.backroad.model.PropietarioModel;
import lasalle.edu.mx.backroad.repository.PropietarioRepository;
import lasalle.edu.mx.backroad.service.PropietarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PropietarioServiceImpl implements PropietarioService {
    @Autowired
    private PropietarioRepository propietarioRepository;

    @Override
    public void registrarPropietario (PropietarioModel propietario) {
        // Validaciones de duplicados
        if (propietarioRepository.existsByCurp(propietario.getCurp())) {
            throw new IllegalArgumentException("Ya existe un propietario con ese CURP.");
        }
        if (propietarioRepository.existsByRfc(propietario.getRfc())) {
            throw new IllegalArgumentException("Ya existe un propietario con ese RFC.");
        }
        if (propietarioRepository.existsByLicencia(propietario.getLicencia())) {
            throw new IllegalArgumentException("Ya existe un propietario con esa licencia.");
        }
        propietarioRepository.save(propietario);
    }

    @Override
    public List<PropietarioModel> obtenerPropietarios() {
        return propietarioRepository.findAll();
    }

    @Override
    public Optional<PropietarioModel> obtenerPropietarioPorCurp(String curp) {
        return propietarioRepository.findByCurp(curp);
    }

    @Override
    public void eliminarPropietario(String curp) {
        propietarioRepository.deleteById(curp);
    }

    @Override
    public void actualizarPropietario(String curp, PropietarioModel propietario) {
        Optional<PropietarioModel> existente = propietarioRepository.findByCurp(curp);
        if (existente.isPresent()) {
            PropietarioModel v = existente.get();

            v.setNombre(propietario.getNombre()!=null ? propietario.getNombre() : v.getNombre());
            v.setApellidos(propietario.getApellidos() != null ? propietario.getApellidos() : v.getApellidos());
            v.setRfc(propietario.getRfc() != null ? propietario.getRfc() : v.getRfc());
            v.setLicencia(propietario.getLicencia() != null ? propietario.getLicencia() : v.getLicencia());
            v.setLicenciaVencimiento(propietario.getLicenciaVencimiento() != null ? propietario.getLicenciaVencimiento() : v.getLicenciaVencimiento());
            propietarioRepository.save(v);
        } else {
            throw new IllegalArgumentException("Propietario no encontrado.");
        }
    }
}
