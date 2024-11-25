package com.recipe.universe.domain.unit.repository;

import com.recipe.universe.domain.unit.entity.Unit;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UnitRepository extends JpaRepository<Unit, Long> {

    public Optional<Unit> findByName(String name);
}
