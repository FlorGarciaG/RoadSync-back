package lasalle.edu.mx.backroad.controller;

import lasalle.edu.mx.backroad.model.MultaModel;
import lasalle.edu.mx.backroad.service.MultaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/multa")
@CrossOrigin(origins = "*")
public class MultaController {
    @Autowired
    private MultaService multaService;

    @PostMapping("/registro")
    public ResponseEntity<Object> registrarMulta(@RequestBody MultaModel multa) {
        try {
            multaService.registrarMulta(multa);
            return ResponseEntity.status(HttpStatus.CREATED).body(multa);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<MultaModel>> getAllMultas() {
        List<MultaModel> lista = multaService.obtenerMultas();
        if (lista.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(lista, HttpStatus.OK);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> modificarMulta(@RequestBody MultaModel multa, @PathVariable Long id) {
        try {
            multaService.actualizarMulta(id, multa);
            return ResponseEntity.status(HttpStatus.OK).body(multa);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al actualizar multa: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> eliminarMulta(@PathVariable Long id) {
        Optional<MultaModel> multaExistente = multaService.obtenerMultaPorId(id);
        if (multaExistente.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("La multa no existe");
        }
        try {
            multaService.eliminarMulta(id);
            return ResponseEntity.ok("Multa eliminada con exito");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.
                    BAD_REQUEST).body("Error al eliminar: " + e.getMessage());
        }
    }

    @GetMapping("/buscar/vehiculo")
    public ResponseEntity<List<MultaModel>> buscarMultasPorPlaca(@RequestParam String placa) {
        List<MultaModel> multas = multaService.obtenerMultasPorPlaca(placa);
        if (multas.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(multas, HttpStatus.OK);
    }
}
