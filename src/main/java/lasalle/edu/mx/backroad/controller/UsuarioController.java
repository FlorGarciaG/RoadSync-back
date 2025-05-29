package lasalle.edu.mx.backroad.controller;


import lasalle.edu.mx.backroad.model.UsuarioModel;
import lasalle.edu.mx.backroad.repository.UsuarioRepository;
import lasalle.edu.mx.backroad.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/usuario")
@CrossOrigin(origins = "*")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    //Registrar usuario
    @PostMapping("/registro")
    public ResponseEntity<Object> registrarUsuario(@RequestBody UsuarioModel usuario) {
        try {
            usuarioService.registrarUsuario(usuario);
            return ResponseEntity.status(HttpStatus.CREATED).body("Usuario registrado con exito");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al registrar: " + e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody Map<String, String> loginData) {
        try {
            String correo = loginData.get("correo");
            String password = loginData.get("password");

            // Validar que correo y password estén presentes y no vacíos
            if (correo == null || correo.isBlank() || password == null || password.isBlank()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Formato inválido: correo y password son obligatorios.");
            }

            Optional<UsuarioModel> usuario = usuarioService.autenticar(correo, password);

            if (usuario.isPresent()) {
                return ResponseEntity.status(HttpStatus.OK).body(usuario.get());
            } else {
                // Usuario o contraseña incorrectos
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Correo o contraseña incorrectos.");
            }

        } catch (Exception e) {
            // Error inesperado del servidor
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno del servidor. Por favor intente más tarde.");
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<UsuarioModel>> obtenerUsuarios() {
        List<UsuarioModel> usuarios = usuarioService.obtenerUsuarios();
        if (usuarios.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(usuarios, HttpStatus.OK);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> actualizarUsuario(@PathVariable Long id, @RequestBody UsuarioModel usuarioActualizado) {
        Optional<UsuarioModel> usuarioExistenteOpt = usuarioService.obtenerUsuarioPorId(id);

        if (usuarioExistenteOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado");
        }

        try {
            UsuarioModel usuarioExistente = usuarioExistenteOpt.get();

            if (usuarioActualizado.getNombre() != null) {
                usuarioExistente.setNombre(usuarioActualizado.getNombre());
            }
            if (usuarioActualizado.getApellidos() != null) {
                usuarioExistente.setApellidos(usuarioActualizado.getApellidos());
            }
            if (usuarioActualizado.getCorreo() != null) {
                usuarioExistente.setCorreo(usuarioActualizado.getCorreo());
            }
            if (usuarioActualizado.getRol() != null) {
                usuarioExistente.setRol(usuarioActualizado.getRol());
            }
            if (usuarioActualizado.getPassword() != null) {
                String passwordCifrada = passwordEncoder.encode(usuarioActualizado.getPassword());
                usuarioExistente.setPassword(passwordCifrada);
            }

            usuarioService.actualizarUsuario(id, usuarioExistente);
            return ResponseEntity.status(HttpStatus.OK).body("Usuario actualizado con éxito");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al actualizar: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> eliminarUsuario(@PathVariable Long id) {
        if (usuarioService.obtenerUsuarioPorId(id).isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado");
        }
        try {
            usuarioService.eliminarUsuario(id);
            return ResponseEntity.status(HttpStatus.OK).body("Usuario eliminado con exito");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al eliminar: " + e.getMessage());
        }
    }
}
