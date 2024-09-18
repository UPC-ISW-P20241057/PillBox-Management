package com.medibox.PillBox.weight.mapping;

import com.medibox.PillBox.shared.mapping.EnhancedModelMapper;
import com.medibox.PillBox.weight.domain.model.entity.Data;
import com.medibox.PillBox.weight.resource.UpdateDataResource;
import com.medibox.PillBox.weight.resource.DataResource;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;

public class DataMapper implements Serializable {
  @Autowired
  EnhancedModelMapper mapper;

  public Data toModel(UpdateDataResource resource) {
    return this.mapper.map(resource, Data.class);
  }
  public DataResource toResource(Data medicine) {
    return this.mapper.map(medicine, DataResource.class);
  }
}
