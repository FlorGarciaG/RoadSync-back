package lasalle.edu.mx.backroad.service;

import lasalle.edu.mx.backroad.model.VehiculoModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface VehiculoService {
    VehiculoModel registrarVehiculo(VehiculoModel vehiculo);
    List<VehiculoModel> obtenerVehiculos();
    Optional<VehiculoModel> obtenerVehiculoPorId(Long idVehiculo);
    void eliminarVehiculo(Long idVehiculo);
    void actualizarVehiculo (Long idVehiculo, VehiculoModel vehiculo);
    List<VehiculoModel> buscarVehiculosPorCurp(String curpParcial);
    List<VehiculoModel> buscarVehiculosPorPlaca(String placaParcial);
}
