package com.medibox.PillBox.weight.domain.persistence;

import com.medibox.PillBox.weight.domain.model.entity.Weight;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WeightRepository extends JpaRepository<Weight, Long> {
}
