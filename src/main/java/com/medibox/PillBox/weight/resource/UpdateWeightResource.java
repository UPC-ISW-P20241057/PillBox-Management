package com.medibox.PillBox.weight.resource;

import jakarta.persistence.Column;
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
  private Boolean isEmpty;
  private Boolean almostEmpty;
  private Integer numberAlarm;
}
