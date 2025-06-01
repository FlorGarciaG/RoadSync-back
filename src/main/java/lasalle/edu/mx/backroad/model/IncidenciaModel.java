package lasalle.edu.mx.backroad.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "incidencia")
public class IncidenciaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // autoincremento
    private Long idIncidencia;

    @ManyToOne
    @JoinColumn(name = "id_vehiculo", referencedColumnName = "idVehiculo", nullable = false)
    private VehiculoModel vehiculo;

    @NotNull
    @Column(length = 50)
    private String tipoIncidencia;

    @Column(length = 255)
    private String descripcion;

    @NotNull
    private LocalDate fecha;

    public Long getIdIncidencia() {
        return idIncidencia;
    }

    public void setIdIncidencia(Long idIncidencia) {
        this.idIncidencia = idIncidencia;
    }

    public VehiculoModel getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(VehiculoModel vehiculo) {
        this.vehiculo = vehiculo;
    }

    public String getTipoIncidencia() {
        return tipoIncidencia;
    }

    public void setTipoIncidencia(String tipoIncidencia) {
        this.tipoIncidencia = tipoIncidencia;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
}
