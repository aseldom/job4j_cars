package ru.job4j.cars.service;


import ru.job4j.cars.model.Owner;

import java.util.Collection;
import java.util.Optional;

public interface OwnerService {

    Optional<Owner> add(Owner owner);

    Optional<Owner> update(Owner owner);

    boolean deleteById(int id);

    Optional<Owner> findById(int id);

    Collection<Owner> findAll();

}
