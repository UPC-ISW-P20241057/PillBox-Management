package com.medibox.PillBox.weight.domain.service;

import com.medibox.PillBox.weight.domain.model.entity.Data;

import java.util.List;
import java.util.Optional;

public interface DataService {
  List<Data> getAll();
  Data update(Data data);
  Data getLatestWeight();
  Data updateWeight(Long id, String value, Boolean isEmpty, Boolean almostEmpty);
  Optional<Data> getById(Long id);
}
