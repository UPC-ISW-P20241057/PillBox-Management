package com.medibox.PillBox.weight.domain.service;

import com.medibox.PillBox.weight.domain.model.entity.Weight;

import java.util.List;

public interface WeightService {
  List<Weight> getAll();
  Weight save(Weight weight);
}
