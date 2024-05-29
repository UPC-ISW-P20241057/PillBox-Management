package com.medibox.PillBox.weight.api;

import com.medibox.PillBox.weight.domain.model.entity.Weight;
import com.medibox.PillBox.weight.domain.service.WeightService;
import com.medibox.PillBox.weight.mapping.WeightMapper;
import com.medibox.PillBox.weight.resource.CreateWeightResource;
import com.medibox.PillBox.weight.resource.WeightResource;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("weights")
public class WeightController {
  private final WeightService weightService;
  private final WeightMapper mapper;

  @GetMapping
  public List<Weight> getAll(){
    return weightService.getAll();
  }

  @PostMapping
  public WeightResource save(@RequestBody CreateWeightResource resource) {
    return mapper.toResource( weightService.save( mapper.toModel(resource) ) );
  }

  /*@PostMapping
  public Weight save(@RequestBody Weight weight) {
    return weightService.save(weight);
  }*/
}
