package lasalle.edu.mx.backroad.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;


@Entity
@Table (name = "usuario")
public class UsuarioModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // autoincremento
    private Long idUsuario;

    @NotNull
    @Size(min = 2, max = 100)
    @Column(length = 100)
    private String nombre;

    @NotNull
    @Size(min = 2, max = 100)
    @Column(length = 100)
    private String apellidos;

    @NotNull
    @Column(length = 50)
    private String rol;

    @NotNull
    @Size(min = 2, max = 100)
    @Column(unique = true, length = 100)
    private String correo;

    @NotNull
    @Size(min = 6, max = 255)
    @Column(length = 255)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;


    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
