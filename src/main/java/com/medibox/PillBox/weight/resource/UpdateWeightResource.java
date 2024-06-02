package com.medibox.PillBox.weight.resource;

import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class UpdateWeightResource {
  private Long id;
  //private String value;
  private Boolean reminder;
}
