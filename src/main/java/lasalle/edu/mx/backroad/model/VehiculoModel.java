package lasalle.edu.mx.backroad.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Entity
@Table(name = "vehiculo")
public class VehiculoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // autoincremento
    private Long idVehiculo;

    @ManyToOne
    @JoinColumn(name = "curp_propietario", referencedColumnName = "curp", nullable = false)
    private PropietarioModel propietario;

    @Column(length = 50)
    private String tipo;

    @NotNull
    @Column(length = 10)
    private String placa;

    @Column(length = 50)
    private String marca;

    @Column(length = 50)
    private String modelo;

    @NotNull
    @Column(length = 50)
    private String tarjeta;

    @NotNull
    private LocalDate tarjetaVencimiento;

    @NotNull
    @Column(unique = true)
    private String numSerie;

    @NotNull
    @Column(unique = true)
    private String numMotor;

    private String tipo_combustible;

    @Min(value = 1000)
    @Max(value = 9999)
    private Integer anioAuto;

    @NotNull
    private String uso;

    public Long getIdVehiculo() {
        return idVehiculo;
    }

    public void setIdVehiculo(Long idVehiculo) {
        this.idVehiculo = idVehiculo;
    }

    public PropietarioModel getPropietario() {
        return propietario;
    }

    public void setPropietario(PropietarioModel propietario) {
        this.propietario = propietario;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getTarjeta() {
        return tarjeta;
    }

    public void setTarjeta(String tarjeta) {
        this.tarjeta = tarjeta;
    }

    public LocalDate getTarjetaVencimiento() {
        return tarjetaVencimiento;
    }

    public void setTarjetaVencimiento(LocalDate tarjetaVencimiento) {
        this.tarjetaVencimiento = tarjetaVencimiento;
    }

    public String getTipo_combustible() {
        return tipo_combustible;
    }

    public void setTipo_combustible(String tipo_combustible) {
        this.tipo_combustible = tipo_combustible;
    }

    public String getUso() {
        return uso;
    }

    public void setUso(String uso) {
        this.uso = uso;
    }

    public String getNumSerie() {
        return numSerie;
    }

    public void setNumSerie(String numSerie) {
        this.numSerie = numSerie;
    }

    public String getNumMotor() {
        return numMotor;
    }

    public void setNumMotor(String numMotor) {
        this.numMotor = numMotor;
    }

    public Integer getAnioAuto() {
        return anioAuto;
    }

    public void setAnioAuto(Integer anioAuto) {
        this.anioAuto = anioAuto;
    }
}
