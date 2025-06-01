package lasalle.edu.mx.backroad.controller;

import lasalle.edu.mx.backroad.model.IncidenciaModel;
import lasalle.edu.mx.backroad.service.IncidenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/incidencia")
@CrossOrigin(origins = "*")
public class IncidenciaController {
    @Autowired
    private IncidenciaService incidenciaService;

    @PostMapping("/registro")
    public ResponseEntity<Object> registrarIncidencia(@RequestBody IncidenciaModel incidencia) {
        try{
            incidenciaService.registrarIncidencia(incidencia);
            return ResponseEntity.status(HttpStatus.CREATED).body(incidencia);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> actualizarIncidencia(@PathVariable Long id, @RequestBody IncidenciaModel incidencia) {
        try {
            incidenciaService.actualizarIncidencia(id, incidencia);
            return ResponseEntity.status(HttpStatus.OK).body(incidencia);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al actualizar incidencia: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> eliminarIncidencia(@PathVariable Long id) {
        try{
            incidenciaService.eliminarIncidencia(id);
            return ResponseEntity.status(HttpStatus.OK).body("Incidencia eliminada");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<IncidenciaModel>> obtenerIncidencias() {
        List<IncidenciaModel> lista = incidenciaService.obtenerIncidencias();
        if (lista.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    @GetMapping("/buscar/vehiculo")
    public ResponseEntity<List<IncidenciaModel>> obtenerIncidenciasPorPlaca(@RequestParam String placa) {
        List<IncidenciaModel> lista = incidenciaService.obtenerIncidenciasPorPlaca(placa);
        if (lista.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }
}
