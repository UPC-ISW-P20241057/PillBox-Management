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

  @GetMapping("/latest")
  public Weight getLatestWeight() {
    return weightService.getLatestWeight();
  }

  @PutMapping("/latest")
  public Weight updateLatestWeight(@RequestBody Weight weight) {
    Weight latestWeight = weightService.getLatestWeight();
    return weightService.updateWeight(latestWeight.getId(), weight.getValue());
  }
}
