package lasalle.edu.mx.backroad.model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "catalogoMulta")
public class catalogoMultaModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTipo;

    @Column(precision = 10, scale = 2, nullable = false)
    private BigDecimal monto;

    @Column(length = 70, unique = true, nullable = false)
    private String tipo;

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Long getIdTipo() {
        return idTipo;
    }

}
