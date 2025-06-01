package lasalle.edu.mx.backroad.repository;

import lasalle.edu.mx.backroad.model.IncidenciaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IncidenciaRepository extends JpaRepository<IncidenciaModel, Long> {
    List<IncidenciaModel> findByVehiculoPlacaContainingIgnoreCase(String placa);
}
