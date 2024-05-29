package com.medibox.PillBox.weight.service;

import com.medibox.PillBox.weight.domain.model.entity.Weight;
import com.medibox.PillBox.weight.domain.persistence.WeightRepository;
import com.medibox.PillBox.weight.domain.service.WeightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WeightServiceImpl implements WeightService {

  @Autowired
  private WeightRepository weightRepository;

  @Override
  public List<Weight> getAll() {
    return weightRepository.findAll();
  }

  @Override
  public Weight save(Weight weight) {
    return weightRepository.save(weight);
  }
}
