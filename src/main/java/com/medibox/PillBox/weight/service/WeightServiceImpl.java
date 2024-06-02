package com.medibox.PillBox.weight.service;

import com.medibox.PillBox.weight.domain.model.entity.Weight;
import com.medibox.PillBox.weight.domain.persistence.WeightRepository;
import com.medibox.PillBox.weight.domain.service.WeightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WeightServiceImpl implements WeightService {

  @Autowired
  private WeightRepository weightRepository;

  @Override
  public List<Weight> getAll() {
    return weightRepository.findAll();
  }

  @Override
  public Weight update(Weight weight) {
    return weightRepository.save(weight);
  }

  @Override
  public Weight getLatestWeight() {
    return weightRepository.findTopByOrderByIdDesc();
  }

  @Override
  public Weight updateWeight(Long id, String value) {
    Weight weight = weightRepository.findById(id).orElseThrow(() -> new RuntimeException("Weight not found"));
    weight.setValue(value);
    return weightRepository.save(weight);
  }

  @Override
  public Optional<Weight> getById(Long id) {
    return weightRepository.findById(id);
  }
}
