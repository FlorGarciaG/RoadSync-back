package lasalle.edu.mx.backroad.repository;

import lasalle.edu.mx.backroad.model.VehiculoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehiculoRepository extends JpaRepository<VehiculoModel, Long> {
    List<VehiculoModel> findByPropietarioCurpContainingIgnoreCase(String curp);

}
