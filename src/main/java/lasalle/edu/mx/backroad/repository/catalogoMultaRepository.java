package lasalle.edu.mx.backroad.repository;

import lasalle.edu.mx.backroad.model.catalogoMultaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface catalogoMultaRepository extends JpaRepository<catalogoMultaModel, Long> {
    boolean existsByTipo(String tipo);
}

