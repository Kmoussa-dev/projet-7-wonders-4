package org.example.client.modele;

import packageDTOs.CarteDTO;

import java.util.Collection;
import java.util.List;

public interface IFacadeProxy {
    Collection<CarteDTO> getCartes();

    CarteDTO getCarte(String nom);
}
