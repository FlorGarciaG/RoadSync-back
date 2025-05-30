package lasalle.edu.mx.backroad.repository;

import lasalle.edu.mx.backroad.model.MultaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MultaRepository extends JpaRepository<MultaModel, Long> {
    List<MultaModel> findByVehiculoPlacaContainingIgnoreCase(String placa);

}
