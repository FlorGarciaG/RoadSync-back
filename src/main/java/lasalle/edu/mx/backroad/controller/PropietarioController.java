package lasalle.edu.mx.backroad.controller;


import lasalle.edu.mx.backroad.model.PropietarioModel;
import lasalle.edu.mx.backroad.service.PropietarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/propietario")
public class PropietarioController {
    @Autowired
    private PropietarioService propietarioService;

    @PostMapping("/registro")
    public ResponseEntity<Object> registrarPropietario(@RequestBody PropietarioModel propietario) {
        try {
            propietarioService.registrarPropietario(propietario);
            return ResponseEntity.status(HttpStatus.CREATED).body("Propietario registrado con exito");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al registrar: " + e.getMessage());
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<PropietarioModel>> obtenerPropietarios() {
        List<PropietarioModel> propietarios = propietarioService.obtenerPropietarios();
        if (propietarios.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(propietarios, HttpStatus.OK);
        }
    }

    @GetMapping("/{curp}")
    public ResponseEntity<Object> obtenerPropietario(@PathVariable String curp) {
        Optional<PropietarioModel> propietarioOpt = propietarioService.obtenerPropietarioPorCurp(curp);

        if (propietarioOpt.isPresent()) {
            return ResponseEntity.ok(propietarioOpt.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Propietario no encontrado");
        }
    }

    @PutMapping("/{curp}")
    public ResponseEntity<Object> actualizarPropietario(@PathVariable String curp, @RequestBody PropietarioModel propietario) {
        Optional<PropietarioModel> propietarioExistenteOpt = propietarioService.obtenerPropietarioPorCurp(curp);

        if (propietarioExistenteOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Propietario no encontrado");
        }

        try {
            PropietarioModel propietarioExistente = propietarioExistenteOpt.get();

            if (propietario.getNombre() == null) {
                propietario.setNombre(propietarioExistente.getNombre());
            }
            if (propietario.getApellidos() == null) {
                propietario.setApellidos(propietarioExistente.getApellidos());
            }
            if (propietario.getLicencia() == null) {
                propietario.setLicencia(propietarioExistente.getLicencia());
            }
            if (propietario.getLicenciaVencimiento() == null) {
                propietario.setLicenciaVencimiento(propietarioExistente.getLicenciaVencimiento());
            }
            if (propietario.getRfc() == null) {
                propietario.setRfc(propietarioExistente.getRfc());
            }

            propietarioService.actualizarPropietario(curp, propietario);
            return ResponseEntity.ok("Propietario actualizado con exito");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al actualizar: " + e.getMessage());
        }
    }

    @DeleteMapping("/{curp}")
    public ResponseEntity<Object> eliminarPropietario(@PathVariable String curp) {
        Optional<PropietarioModel> propietarioExistenteOpt = propietarioService.obtenerPropietarioPorCurp(curp);

        if (propietarioExistenteOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Propietario no encontrado");
        }

        try{
            propietarioService.eliminarPropietario(curp);
            return ResponseEntity.ok("Propietario eliminado con exito");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.
                    BAD_REQUEST).body("Error al eliminar: " + e.getMessage());
        }

    }

}
