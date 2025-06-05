package lasalle.edu.mx.backroad.repository;

import jakarta.validation.constraints.NotNull;
import lasalle.edu.mx.backroad.model.VehiculoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehiculoRepository extends JpaRepository<VehiculoModel, Long> {
    boolean existsByNumMotor(String num_motor);
    boolean existsByNumSerie(String num_serie);
    List<VehiculoModel> findByPropietarioCurpContainingIgnoreCase(String curp);
    List<VehiculoModel> findByPlacaContainingIgnoreCase(String placa);
}
