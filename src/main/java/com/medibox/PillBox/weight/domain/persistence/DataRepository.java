package com.medibox.PillBox.weight.domain.persistence;

import com.medibox.PillBox.weight.domain.model.entity.Data;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DataRepository extends JpaRepository<Data, Long> {
    Data findTopByOrderByIdDesc();
}
