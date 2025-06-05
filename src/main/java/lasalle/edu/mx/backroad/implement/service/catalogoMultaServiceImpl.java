package lasalle.edu.mx.backroad.implement.service;

import lasalle.edu.mx.backroad.model.catalogoMultaModel;
import lasalle.edu.mx.backroad.repository.catalogoMultaRepository;
import lasalle.edu.mx.backroad.service.catalogoMultaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class catalogoMultaServiceImpl implements catalogoMultaService {
    @Autowired
    private catalogoMultaRepository catalogoMultaRepository;

    @Override
    public void registrarElemento(catalogoMultaModel catalogoMultaModel) {
        if (catalogoMultaRepository.existsByTipo(catalogoMultaModel.getTipo())) {
            throw new IllegalArgumentException("El tipo de multa ya existe");
        }
        catalogoMultaRepository.save(catalogoMultaModel);
    }

    @Override
    public void eliminarElemento(Long id) {
        catalogoMultaRepository.deleteById(id);
    }

    @Override
    public void actualizarElemento(catalogoMultaModel multa, Long id) {
        Optional<catalogoMultaModel> existente = catalogoMultaRepository.findById(id);
        if (existente.isPresent()) {
            catalogoMultaModel v = existente.get();
            if(catalogoMultaRepository.existsByTipo(multa.getTipo())) {
                throw new IllegalArgumentException("El tipo de multa ya existe");
            }
            v.setTipo(multa.getTipo() != null ? multa.getTipo() : v.getTipo());
            v.setMonto(multa.getMonto() != null ? multa.getMonto() : v.getMonto());

            catalogoMultaRepository.save(v);
        } else {
            throw new IllegalArgumentException("El elemento no existe");
        }
    }

    @Override
    public List<catalogoMultaModel> obtenerCatalogoMultas() {
        return catalogoMultaRepository.findAll();
    }
}
