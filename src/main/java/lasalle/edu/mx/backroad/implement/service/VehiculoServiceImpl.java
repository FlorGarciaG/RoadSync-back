package lasalle.edu.mx.backroad.implement.service;

import lasalle.edu.mx.backroad.model.VehiculoModel;
import lasalle.edu.mx.backroad.repository.VehiculoRepository;
import lasalle.edu.mx.backroad.service.VehiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehiculoServiceImpl implements VehiculoService {
    @Autowired
    VehiculoRepository vehiculoRepository;

    @Override
    public VehiculoModel registrarVehiculo(VehiculoModel vehiculo) {
        //Validación de duplicados
        if (vehiculoRepository.existsByNumMotor(vehiculo.getNumMotor())) {
            throw new IllegalArgumentException("Número de motor ya registrado");
        }
        if (vehiculoRepository.existsByNumSerie(vehiculo.getNumSerie())) {
            throw new IllegalArgumentException("Numero de serie ya registrado");
        }
        return vehiculoRepository.save(vehiculo);
    }

    @Override
    public List<VehiculoModel> obtenerVehiculos() {
        return vehiculoRepository.findAll();
    }

    @Override
    public Optional<VehiculoModel> obtenerVehiculoPorId(Long idVehiculo) {
        return vehiculoRepository.findById(idVehiculo);
    }

    @Override
    public void eliminarVehiculo(Long idVehiculo) {
        vehiculoRepository.deleteById(idVehiculo);
    }

    @Override
    public void actualizarVehiculo(Long idVehiculo, VehiculoModel vehiculo) {
        Optional<VehiculoModel> existente = vehiculoRepository.findById(idVehiculo);
        if (existente.isPresent()) {
            VehiculoModel v = existente.get();

            v.setTipo(vehiculo.getTipo() != null ? vehiculo.getTipo() : v.getTipo());
            v.setPlaca(vehiculo.getPlaca() != null ? vehiculo.getPlaca() : v.getPlaca());
            v.setMarca(vehiculo.getMarca() != null ? vehiculo.getMarca() : v.getMarca());
            v.setModelo(vehiculo.getModelo() != null ? vehiculo.getModelo() : v.getModelo());
            v.setTarjeta(vehiculo.getTarjeta() != null ? vehiculo.getTarjeta() : v.getTarjeta());
            v.setUso(vehiculo.getUso() != null ? vehiculo.getUso() : v.getUso());
            v.setAnioAuto(vehiculo.getAnioAuto() != null ? vehiculo.getAnioAuto() : v.getAnioAuto());
            v.setTipo_combustible(vehiculo.getTipo_combustible() != null ? vehiculo.getTipo_combustible() : v.getTipo_combustible());
            v.setNumMotor(vehiculo.getNumMotor() != null ? vehiculo.getNumMotor() : v.getNumMotor());
            v.setNumSerie(vehiculo.getNumSerie() != null ? vehiculo.getNumSerie() : v.getNumSerie());
            v.setTarjetaVencimiento(vehiculo.getTarjetaVencimiento() != null ? vehiculo.getTarjetaVencimiento() : v.getTarjetaVencimiento());
            v.setPropietario(vehiculo.getPropietario() != null ? vehiculo.getPropietario() : v.getPropietario());

            vehiculoRepository.save(v);
        } else {
            throw new IllegalArgumentException("Vehículo no encontrado.");
        }
    }

    @Override
    public List<VehiculoModel> buscarVehiculosPorCurp(String curpParcial) {
        return vehiculoRepository.findByPropietarioCurpContainingIgnoreCase(curpParcial);
    }

    @Override
    public List<VehiculoModel> buscarVehiculosPorPlaca(String placaParcial) {
        return vehiculoRepository.findByPlacaContainingIgnoreCase(placaParcial);
    }
}
