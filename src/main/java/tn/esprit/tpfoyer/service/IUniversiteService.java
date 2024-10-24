<<<<<<< HEAD
package tn.esprit.tpfoyer.service;

import tn.esprit.tpfoyer.entity.Universite;

import java.util.List;

public interface IUniversiteService {

    public List<Universite> retrieveAllUniversites();
    public Universite retrieveUniversite(Long universiteId);
    public Universite addUniversite(Universite f);
    public void removeUniversite(Long universiteId);
    public Universite modifyUniversite(Universite universite);

    // Here we will add later methods calling keywords and methods calling JPQL

}
=======
package tn.esprit.tpfoyer.service;

import tn.esprit.tpfoyer.entity.Universite;

import java.util.List;

public interface IUniversiteService {

    public List<Universite> retrieveAllUniversites();
    public Universite retrieveUniversite(Long universiteId);
    public Universite addUniversite(Universite f);
    public void removeUniversite(Long universiteId);
    public Universite modifyUniversite(Universite universite);

    List<Universite> findUniversitesByNom(String nom);

}
>>>>>>> 04f219e (Ajout des modifications Mockito et Junit)
