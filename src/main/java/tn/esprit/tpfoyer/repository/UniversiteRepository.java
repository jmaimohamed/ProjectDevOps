<<<<<<< HEAD
package tn.esprit.tpfoyer.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.tpfoyer.entity.Universite;

@Repository
public interface UniversiteRepository extends JpaRepository<Universite, Long>
{


}
=======
package tn.esprit.tpfoyer.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.tpfoyer.entity.Universite;

import java.util.List;

@Repository
public interface UniversiteRepository extends JpaRepository<Universite, Long>
{
    List<Universite> findByNomUniversiteContainingIgnoreCase(String nom);

}
>>>>>>> 04f219e (Ajout des modifications Mockito et Junit)
