package lasalle.edu.mx.backroad.repository;


import lasalle.edu.mx.backroad.model.PropietarioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PropietarioRepository extends JpaRepository<PropietarioModel, String> {
    boolean existsByCurp(String curp);
    boolean existsByLicencia(String licencia);
    Optional<PropietarioModel> findByCurp(String curp);
    List<PropietarioModel> findByCurpContainingIgnoreCase(String curp);
}
