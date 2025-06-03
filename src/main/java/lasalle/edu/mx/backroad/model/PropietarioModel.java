package lasalle.edu.mx.backroad.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
@Table(name = "propietario")
public class PropietarioModel {
    @NotNull
    @Size(min=2, max=100)
    private String nombre;

    @NotNull
    @Size(min=2, max=100)
    private String apellidos;

    @Id
    @Column(length = 18, unique = true)
    @Size(min=18,max=18)
    @Pattern(regexp = "^[A-Z]{4}[0-9]{6}[HM]{1}[A-Z]{5}[A-Z0-9]{2}$", message = "CURP no válido")
    private String curp;

    @Column(length = 13)
    @Size(min=13,max=13)
    @Pattern(regexp = "^([A-ZÑ&]{3,4})\\d{6}[A-Z0-9]{3}$", message = "RFC no válido")
    private String rfc;

    @NotNull
    @Column(unique = true, length = 100)
    private String licencia;

    @NotNull
    private LocalDate licenciaVencimiento;

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

    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getLicencia() {
        return licencia;
    }

    public void setLicencia(String licencia) {
        this.licencia = licencia;
    }

    public LocalDate getLicenciaVencimiento() {
        return licenciaVencimiento;
    }

    public void setLicenciaVencimiento(LocalDate licenciaVencimiento) {
        this.licenciaVencimiento = licenciaVencimiento;
    }
}
