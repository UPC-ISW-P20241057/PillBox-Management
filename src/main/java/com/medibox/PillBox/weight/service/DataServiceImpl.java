package com.medibox.PillBox.weight.service;

import com.medibox.PillBox.weight.domain.model.entity.Data;
import com.medibox.PillBox.weight.domain.persistence.DataRepository;
import com.medibox.PillBox.weight.domain.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DataServiceImpl implements DataService {

  @Autowired
  private DataRepository dataRepository;

  @Override
  public List<Data> getAll() {
    List<Data> data = dataRepository.findAll();
    if (data.isEmpty()) {
      Data newData = new Data();
      newData.setId(1L);
      newData.setValue("0");
      newData.setReminder(false);
      newData.setIsEmpty(false);
      newData.setAlmostEmpty(false);
      newData.setNumberAlarm(1);
      newData.setSsid("");
      newData.setPassword("");
      newData.setWifi(false);
      dataRepository.save(newData);
      data.add(newData);
    }
    return data;
  }



  @Override
  public Data update(Data data) {
    return dataRepository.save(data);
  }

  @Override
  public Data getLatestWeight() {
    return dataRepository.findTopByOrderByIdDesc();
  }

  @Override
  public Data updateWeight(Long id, String value) {
    Data data = dataRepository.findById(id).orElseThrow(() -> new RuntimeException("Data not found"));
    data.setValue(value);
    return dataRepository.save(data);
  }

  @Override
  public Optional<Data> getById(Long id) {
    return dataRepository.findById(id);
  }
}
