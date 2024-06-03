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
    List<Weight> weights = weightRepository.findAll();
    if (weights.isEmpty()) {
      Weight newWeight = new Weight();
      newWeight.setId(1L);
      newWeight.setValue("0");
      newWeight.setReminder(false);
      newWeight.setIsEmpty(false);
      newWeight.setAlmostEmpty(false);
      newWeight.setNumberAlarm(1);
      weightRepository.save(newWeight);
      weights.add(newWeight);
    }
    return weights;
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
