package lasalle.edu.mx.backroad.controller;

import lasalle.edu.mx.backroad.model.VehiculoModel;
import lasalle.edu.mx.backroad.service.VehiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/vehiculo")
public class VehiculoController {

    @Autowired
    private VehiculoService vehiculoService;

    @PostMapping("/registro")
    public ResponseEntity<Object> registrarVehiculo(@RequestBody VehiculoModel vehiculo) {
        try {
            VehiculoModel nuevoVehiculo = vehiculoService.registrarVehiculo(vehiculo);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoVehiculo);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al registrar vehículo: " + e.getMessage());
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<VehiculoModel>> obtenerVehiculos() {
        List<VehiculoModel> lista = vehiculoService.obtenerVehiculos();
        if (lista.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> obtenerVehiculoPorId(@PathVariable Long id) {
        Optional<VehiculoModel> vehiculoOpt = vehiculoService.obtenerVehiculoPorId(id);
        if (vehiculoOpt.isPresent()) {
            return ResponseEntity.ok(vehiculoOpt.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Vehículo no encontrado");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> actualizarVehiculo(@PathVariable Long id, @RequestBody VehiculoModel vehiculo) {
        try {
            vehiculoService.actualizarVehiculo(id, vehiculo);
            return ResponseEntity.ok("Vehiculo actualizado exitosamente");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al actualizar vehículo: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> eliminarVehiculo(@PathVariable Long id) {
        Optional<VehiculoModel> vehiculoOpt = vehiculoService.obtenerVehiculoPorId(id);
        if (vehiculoOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Vehículo no encontrado");
        }
        try {
            vehiculoService.eliminarVehiculo(id);
            return ResponseEntity.ok("Vehículo eliminado con éxito");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al eliminar vehículo: " + e.getMessage());
        }
    }

}
