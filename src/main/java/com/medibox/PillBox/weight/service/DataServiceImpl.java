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
    } else {
      data.forEach(this::ensureNonNullFields);
    }
    return data;
  }

  @Override
  public Data update(Data data) {
    ensureNonNullFields(data);
    return dataRepository.save(data);
  }

  @Override
  public Data getLatestWeight() {
    Data latestData = dataRepository.findTopByOrderByIdDesc();
    ensureNonNullFields(latestData);
    return latestData;
  }

  @Override
  public Data updateWeight(Long id, String value) {
    Data data = dataRepository.findById(id).orElseThrow(() -> new RuntimeException("Data not found"));
    data.setValue(value);
    ensureNonNullFields(data);
    return dataRepository.save(data);
  }

  @Override
  public Optional<Data> getById(Long id) {
    Optional<Data> dataOptional = dataRepository.findById(id);
    dataOptional.ifPresent(this::ensureNonNullFields);
    return dataOptional;
  }

  private void ensureNonNullFields(Data data) {
    if (data.getValue() == null) data.setValue("0");
    if (data.getReminder() == null) data.setReminder(false);
    if (data.getIsEmpty() == null) data.setIsEmpty(false);
    if (data.getAlmostEmpty() == null) data.setAlmostEmpty(false);
    if (data.getNumberAlarm() == null) data.setNumberAlarm(1);
    if (data.getSsid() == null) data.setSsid("");
    if (data.getPassword() == null) data.setPassword("");
    if (data.getWifi() == null) data.setWifi(false);
  }
}
