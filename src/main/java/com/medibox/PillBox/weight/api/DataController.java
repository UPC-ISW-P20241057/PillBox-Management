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
      updateDataFromResource(existingData, resource);
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
      updateDataFromResource(existingData, resource);
      DataResource updatedDataResource = mapper.toResource(dataService.update(existingData));
      return ResponseEntity.ok(updatedDataResource);
    } else {
      return ResponseEntity.badRequest().build();
    }
  }

  @GetMapping("/latest")
  public DataResource getLatestWeight() {
    Data latestData = dataService.getLatestWeight();
    return mapper.toResource(latestData);
  }

  @PatchMapping("/latest")
  public ResponseEntity<DataResource> patchLatestWeight(@RequestBody UpdateDataResource resource) {
    Data latestData = dataService.getLatestWeight();
    updateDataFromResource(latestData, resource);
    DataResource updatedDataResource = mapper.toResource(dataService.update(latestData));
    return new ResponseEntity<>(updatedDataResource, HttpStatus.OK);
  }

  private void updateDataFromResource(Data data, UpdateDataResource resource) {
    if (resource.getReminder() != null) data.setReminder(resource.getReminder());
    if (resource.getIsEmpty() != null) data.setIsEmpty(resource.getIsEmpty());
    if (resource.getAlmostEmpty() != null) data.setAlmostEmpty(resource.getAlmostEmpty());
    if (resource.getNumberAlarm() != null) data.setNumberAlarm(resource.getNumberAlarm());
    if (resource.getSsid() != null) data.setSsid(resource.getSsid());
    if (resource.getPassword() != null) data.setPassword(resource.getPassword());
  }
}
