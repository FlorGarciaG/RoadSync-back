package lasalle.edu.mx.backroad.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "multa")
public class MultaModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // autoincremento
    private Long idMulta;

    @ManyToOne
    @JoinColumn(name = "id_vehiculo", referencedColumnName = "idVehiculo", nullable = false)
    private VehiculoModel vehiculo;

    @NotNull
    @Column(length = 50)
    private String tipoMulta;

    @Column(precision = 10, scale = 2)
    private BigDecimal monto;

    @Column(length = 255)
    private String descripcion;

    @NotNull
    private LocalDate fecha;

    public Long getIdMulta() {
        return idMulta;
    }

    public void setIdMulta(Long idMulta) {
        this.idMulta = idMulta;
    }

    public VehiculoModel getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(VehiculoModel vehiculo) {
        this.vehiculo = vehiculo;
    }

    public String getTipoMulta() {
        return tipoMulta;
    }

    public void setTipoMulta(String tipoMulta) {
        this.tipoMulta = tipoMulta;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
