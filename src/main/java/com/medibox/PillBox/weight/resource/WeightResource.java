package com.medibox.PillBox.weight.resource;

import jakarta.persistence.Column;
import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class WeightResource {
  private Long id;

  private String value;
}
