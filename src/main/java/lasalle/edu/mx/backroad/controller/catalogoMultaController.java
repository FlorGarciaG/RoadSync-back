package lasalle.edu.mx.backroad.controller;

import lasalle.edu.mx.backroad.model.catalogoMultaModel;
import lasalle.edu.mx.backroad.service.catalogoMultaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/catalogo")
@CrossOrigin(origins = "*")
public class catalogoMultaController {
    @Autowired
    private catalogoMultaService catalogoMultaService;

    @PostMapping("/registro")
    public ResponseEntity<Object> registrarElemento(@RequestBody catalogoMultaModel multa) {
        try{
            catalogoMultaService.registrarElemento(multa);
            return new ResponseEntity<>(multa, HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> eliminarElemento(@PathVariable Long id) {
        try{
            catalogoMultaService.eliminarElemento(id);
            return ResponseEntity.status(HttpStatus.OK).body("Incidencia eliminada");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> actualizarElemento(@PathVariable Long id, @RequestBody catalogoMultaModel multa) {
        try{
            catalogoMultaService.actualizarElemento(multa,id);
            return ResponseEntity.status(HttpStatus.OK).body("Incidencia actualizada");
        }catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<catalogoMultaModel>> listarElementos() {
        List<catalogoMultaModel> lista = catalogoMultaService.obtenerCatalogoMultas();
        if (lista.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }
}
