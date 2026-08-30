package ma.airport.objetsperdus.repository;

import ma.airport.objetsperdus.entity.ObjetTrouve;
import ma.airport.objetsperdus.entity.StatutObjet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ObjetTrouveRepository extends JpaRepository<ObjetTrouve, Long> {
    // Trouver les objets par leur catégorie (ex: "Électronique")
    List<ObjetTrouve> findByCategorie(String categorie);

    // Trouver les objets selon leur statut (ex: tous ceux qui sont "STOCKE")
    List<ObjetTrouve> findByStatut(StatutObjet statut);
}
