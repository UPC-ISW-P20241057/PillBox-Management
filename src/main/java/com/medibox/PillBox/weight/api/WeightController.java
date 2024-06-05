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
public class WeightController {
  private final DataService dataService;
  private final DataMapper mapper;

  @GetMapping
  public List<Data> getAll(){
    return dataService.getAll();
  }

  @PutMapping("{id}")
  public ResponseEntity<DataResource> update(@PathVariable Long id, @RequestBody UpdateDataResource resource) {
    if(id.equals(resource.getId())) {
      Data existingData = dataService.getById(id).orElseThrow(() -> new RuntimeException("Data not found"));
      existingData.setReminder(resource.getReminder() != null ? resource.getReminder() : false);
      DataResource updatedDataResource = mapper.toResource(dataService.update(existingData));
      return new ResponseEntity<>(updatedDataResource, HttpStatus.OK);
    } else {
      return ResponseEntity.badRequest().build();
    }
  }

  @PatchMapping("{id}")
  public ResponseEntity<DataResource> patchUpdate(@PathVariable Long id, @RequestBody UpdateDataResource resource) {
    if(id.equals(resource.getId())) {
      Data existingData = dataService.getById(id).orElseThrow(() -> new RuntimeException("Data not found"));
      if (resource.getReminder() != null) {
        existingData.setReminder(resource.getReminder());
      }
      if (resource.getIsEmpty() != null) {
        existingData.setIsEmpty(resource.getIsEmpty());
      } else {
        existingData.setIsEmpty(false); // Valor predeterminado si no se proporciona en el cuerpo de la solicitud
      }
      if (resource.getAlmostEmpty() != null) {
        existingData.setAlmostEmpty(resource.getAlmostEmpty());
      } else {
        existingData.setAlmostEmpty(false); // Valor predeterminado si no se proporciona en el cuerpo de la solicitud
      }
      if (resource.getNumberAlarm() != null) {
        existingData.setNumberAlarm(resource.getNumberAlarm());
      } else {
        existingData.setNumberAlarm(1); // Valor predeterminado si no se proporciona en el cuerpo de la solicitud
      }
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
    latestData.setValue(data.getValue()); // Actualiza solo el valor del peso
    DataResource updatedDataResource = mapper.toResource(dataService.update(latestData));
    return new ResponseEntity<>(updatedDataResource, HttpStatus.OK);
  }
}
