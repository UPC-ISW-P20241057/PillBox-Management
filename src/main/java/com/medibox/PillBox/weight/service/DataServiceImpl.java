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
      Data newData1 = new Data();
      newData1.setId(1L);
      newData1.setValue("0");
      newData1.setReminder(false);
      newData1.setIsEmpty(false);
      newData1.setAlmostEmpty(false);
      newData1.setNumberAlarm(1);
      newData1.setSsid("");
      newData1.setPassword("");
      dataRepository.save(newData1);
      data.add(newData1);

      Data newData2 = new Data();
      newData2.setId(2L);
      newData2.setValue("0");
      newData2.setReminder(false);
      newData2.setIsEmpty(false);
      newData2.setAlmostEmpty(false);
      newData2.setNumberAlarm(1);
      newData2.setSsid("");
      newData2.setPassword("");
      dataRepository.save(newData2);
      data.add(newData2);
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
  public Data updateWeight(Long id, String value, Boolean isEmpty, Boolean almostEmpty) {
    Data data = dataRepository.findById(id).orElseThrow(() -> new RuntimeException("Data not found"));
    data.setValue(value);

    if (isEmpty != null) {
      data.setIsEmpty(isEmpty);
    }

    if (almostEmpty != null) {
      data.setAlmostEmpty(almostEmpty);
    }

    return dataRepository.save(data);
  }

  @Override
  public Data getById(Long id) {
    return dataRepository.findById(id).orElseThrow(() -> new RuntimeException("Data not found"));
  }

  @Override
  public Data updateWeightById(Long id, String value, Boolean isEmpty, Boolean almostEmpty) {
    Data data = dataRepository.findById(id).orElseThrow(() -> new RuntimeException("Data not found"));

    if (value != null) {
      data.setValue(value);
    }

    if (isEmpty != null) {
      data.setIsEmpty(isEmpty);
    }

    if (almostEmpty != null) {
      data.setAlmostEmpty(almostEmpty);
    }

    return dataRepository.save(data);
  }

}
