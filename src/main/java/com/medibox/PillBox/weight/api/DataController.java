package com.medibox.PillBox.weight.api;

import com.medibox.PillBox.weight.domain.model.entity.Data;
import com.medibox.PillBox.weight.domain.service.DataService;
import com.medibox.PillBox.weight.mapping.DataMapper;
import com.medibox.PillBox.weight.resource.UpdateDataResource;
import com.medibox.PillBox.weight.resource.DataResource;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/weights")
public class DataController {
  private final DataService dataService;
  private final DataMapper mapper;

  @GetMapping
  public List<Data> getAll() {
    return dataService.getAll();
  }

  @PutMapping("{id}")
  public ResponseEntity<DataResource> update(@PathVariable Long id, @RequestBody UpdateDataResource resource) {
    if (id.equals(resource.getId())) {
      Data existingData = dataService.getById(id).orElseThrow(() -> new RuntimeException("Data not found"));
      if (resource.getReminder() != null) existingData.setReminder(resource.getReminder());
      if (resource.getIsEmpty() != null) existingData.setIsEmpty(resource.getIsEmpty());
      if (resource.getAlmostEmpty() != null) existingData.setAlmostEmpty(resource.getAlmostEmpty());
      if (resource.getNumberAlarm() != null) existingData.setNumberAlarm(resource.getNumberAlarm());
      if (resource.getSsid() != null) existingData.setSsid(resource.getSsid());
      if (resource.getPassword() != null) existingData.setPassword(resource.getPassword());
      DataResource updatedDataResource = mapper.toResource(dataService.update(existingData));
      return new ResponseEntity<>(updatedDataResource, HttpStatus.OK);
    } else {
      return ResponseEntity.badRequest().build();
    }
  }

  @PatchMapping("{id}")
  public ResponseEntity<DataResource> patchUpdate(@PathVariable Long id, @RequestBody UpdateDataResource resource) {
    if (id.equals(resource.getId())) {
      Data existingData = dataService.getById(id).orElseThrow(() -> new RuntimeException("Data not found"));
      existingData.setReminder(resource.getReminder() != null ? resource.getReminder() : false);
      existingData.setIsEmpty(resource.getIsEmpty() != null ? resource.getIsEmpty() : false);
      existingData.setAlmostEmpty(resource.getAlmostEmpty() != null ? resource.getAlmostEmpty() : false);
      existingData.setNumberAlarm(resource.getNumberAlarm() != null ? resource.getNumberAlarm() : 1);
      existingData.setSsid(resource.getSsid() != null ? resource.getSsid() : "");
      existingData.setPassword(resource.getPassword() != null ? resource.getPassword() : "");
      DataResource updatedDataResource = mapper.toResource(dataService.update(existingData));
      return ResponseEntity.ok(updatedDataResource);
    } else {
      return ResponseEntity.badRequest().build();
    }
  }

  @GetMapping("/latest")
  public Data getLatestWeight() {
    return dataService.getLatestWeight();
  }

  @PatchMapping("/latest")
  public ResponseEntity<DataResource> patchLatestWeight(@RequestBody Data data) {
    Data latestData = dataService.getLatestWeight();

    if (data.getValue() != null) {
      latestData.setValue(data.getValue());
    }

    if (data.getSsid() != null) {
      latestData.setSsid(data.getSsid());
    }

    if (data.getPassword() != null) {
      latestData.setPassword(data.getPassword());
    }

    if (data.getIsEmpty() != null) {
      latestData.setIsEmpty(data.getIsEmpty());
    }

    if (data.getAlmostEmpty() != null) {
      latestData.setAlmostEmpty(data.getAlmostEmpty());
    }

    DataResource updatedDataResource = mapper.toResource(dataService.update(latestData));
    return new ResponseEntity<>(updatedDataResource, HttpStatus.OK);
  }

}