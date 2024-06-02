package com.medibox.PillBox.weight.mapping;

import com.medibox.PillBox.shared.mapping.EnhancedModelMapper;
import com.medibox.PillBox.weight.domain.model.entity.Weight;
import com.medibox.PillBox.weight.resource.UpdateWeightResource;
import com.medibox.PillBox.weight.resource.WeightResource;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;

public class WeightMapper implements Serializable {
  @Autowired
  EnhancedModelMapper mapper;

  public Weight toModel(UpdateWeightResource resource) {
    return this.mapper.map(resource, Weight.class);
  }
  public WeightResource toResource(Weight medicine) {
    return this.mapper.map(medicine, WeightResource.class);
  }
}
