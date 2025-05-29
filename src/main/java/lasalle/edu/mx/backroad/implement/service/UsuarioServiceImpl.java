package lasalle.edu.mx.backroad.implement.service;

import lasalle.edu.mx.backroad.model.UsuarioModel;
import lasalle.edu.mx.backroad.repository.UsuarioRepository;
import lasalle.edu.mx.backroad.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public void registrarUsuario(UsuarioModel usuario) {
        Optional<UsuarioModel> usuarioExistente = usuarioRepository.findByCorreo(usuario.getCorreo());
        if (usuarioExistente.isPresent()) {
            throw new RuntimeException("El correo ya está en uso");
        }
        String passwordEnTextoPlano = usuario.getPassword();
        String passwordCifrada = passwordEncoder.encode(passwordEnTextoPlano);
        usuario.setPassword(passwordCifrada);
        usuarioRepository.save(usuario);
    }

    @Override
    public List<UsuarioModel> obtenerUsuarios() {
        return usuarioRepository.findAll();
    }

    @Override
    public Optional<UsuarioModel> autenticar(String correo, String password) {
        Optional<UsuarioModel> usuarioOpt = usuarioRepository.findByCorreo(correo);

        if (usuarioOpt.isPresent()) {
            UsuarioModel usuario = usuarioOpt.get();
            if (passwordEncoder.matches(password, usuario.getPassword())) {
                return usuarioOpt; // Autenticación correcta
            }
        }

        return Optional.empty(); // Autenticación fallida
    }

    @Override
    public Optional<UsuarioModel> obtenerUsuarioPorId(Long id) {
        return usuarioRepository.findById(id);
    }

    @Override
    public void actualizarUsuario(Long id, UsuarioModel usuario) {
        Optional<UsuarioModel> existente = usuarioRepository.findById(id);
        if (existente.isPresent()) {
            UsuarioModel v = existente.get();

            v.setCorreo(usuario.getCorreo() != null ? usuario.getCorreo() : v.getCorreo());
            v.setNombre(usuario.getNombre() != null ? usuario.getNombre() : v.getNombre());
            v.setApellidos(usuario.getApellidos() != null ? usuario.getApellidos() : v.getApellidos());
            v.setRol(usuario.getRol() != null ? usuario.getRol() : v.getRol());
            v.setCorreo(usuario.getCorreo() != null ? usuario.getCorreo() : v.getCorreo());
            if (usuario.getPassword() != null) {
                String passwordCifrada = passwordEncoder.encode(usuario.getPassword());
                v.setPassword(passwordCifrada);
            }
            usuarioRepository.save(v);
        }else{
            throw new IllegalArgumentException("El usuario no existe");
        }
    }

    @Override
    public void eliminarUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }
}
