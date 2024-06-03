package com.medibox.PillBox.weight.api;

import com.medibox.PillBox.weight.domain.model.entity.Weight;
import com.medibox.PillBox.weight.domain.service.WeightService;
import com.medibox.PillBox.weight.mapping.WeightMapper;
import com.medibox.PillBox.weight.resource.UpdateWeightResource;
import com.medibox.PillBox.weight.resource.WeightResource;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/weights")
public class WeightController {
  private final WeightService weightService;
  private final WeightMapper mapper;

  @GetMapping
  public List<Weight> getAll(){
    return weightService.getAll();
  }

  @PutMapping("{id}")
  public ResponseEntity<WeightResource> update(@PathVariable Long id, @RequestBody UpdateWeightResource resource) {
    if(id.equals(resource.getId())) {
      Weight existingWeight = weightService.getById(id).orElseThrow(() -> new RuntimeException("Weight not found"));
      existingWeight.setReminder(resource.getReminder() != null ? resource.getReminder() : false);
      WeightResource updatedWeightResource = mapper.toResource(weightService.update(existingWeight));
      return new ResponseEntity<>(updatedWeightResource, HttpStatus.OK);
    } else {
      return ResponseEntity.badRequest().build();
    }
  }

  @PatchMapping("{id}")
  public ResponseEntity<WeightResource> patchUpdate(@PathVariable Long id, @RequestBody UpdateWeightResource resource) {
    if(id.equals(resource.getId())) {
      Weight existingWeight = weightService.getById(id).orElseThrow(() -> new RuntimeException("Weight not found"));
      if (resource.getReminder() != null) {
        existingWeight.setReminder(resource.getReminder());
      }
      if (resource.getIsEmpty() != null) {
        existingWeight.setIsEmpty(resource.getIsEmpty());
      } else {
        existingWeight.setIsEmpty(false); // Valor predeterminado si no se proporciona en el cuerpo de la solicitud
      }
      if (resource.getAlmostEmpty() != null) {
        existingWeight.setAlmostEmpty(resource.getAlmostEmpty());
      } else {
        existingWeight.setAlmostEmpty(false); // Valor predeterminado si no se proporciona en el cuerpo de la solicitud
      }
      if (resource.getNumberAlarm() != null) {
        existingWeight.setNumberAlarm(resource.getNumberAlarm());
      } else {
        existingWeight.setNumberAlarm(1); // Valor predeterminado si no se proporciona en el cuerpo de la solicitud
      }
      WeightResource updatedWeightResource = mapper.toResource(weightService.update(existingWeight));
      return ResponseEntity.ok(updatedWeightResource);
    } else {
      return ResponseEntity.badRequest().build();
    }
  }


  @GetMapping("/latest")
  public Weight getLatestWeight() {
    return weightService.getLatestWeight();
  }

  @PatchMapping("/latest")
  public ResponseEntity<WeightResource> patchLatestWeight(@RequestBody Weight weight) {
    Weight latestWeight = weightService.getLatestWeight();
    latestWeight.setValue(weight.getValue()); // Actualiza solo el valor del peso
    WeightResource updatedWeightResource = mapper.toResource(weightService.update(latestWeight));
    return new ResponseEntity<>(updatedWeightResource, HttpStatus.OK);
  }
}
