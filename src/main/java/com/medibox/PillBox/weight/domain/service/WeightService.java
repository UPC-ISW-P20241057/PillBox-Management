package com.medibox.PillBox.weight.domain.service;

import com.medibox.PillBox.weight.domain.model.entity.Weight;

import java.util.List;
import java.util.Optional;

public interface WeightService {
  List<Weight> getAll();
  Weight update(Weight weight);
  Weight getLatestWeight();
  Weight updateWeight(Long id, String value);
  Optional<Weight> getById(Long id);
}
