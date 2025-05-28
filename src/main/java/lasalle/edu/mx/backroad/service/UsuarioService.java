package lasalle.edu.mx.backroad.service;

import lasalle.edu.mx.backroad.model.UsuarioModel;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {
    void registrarUsuario(UsuarioModel usuario);
    List<UsuarioModel> obtenerUsuarios();
    Optional<UsuarioModel> autenticar(String correo, String password);
    Optional<UsuarioModel> obtenerUsuarioPorId(Long id);
    void actualizarUsuario(Long id, UsuarioModel usuario);
    void eliminarUsuario(Long id);
}
