package com.medibox.PillBox.weight.resource;

import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class DataResource {
  private Boolean reminder;
  private Boolean isEmpty;
  private Boolean almostEmpty;
  private Integer numberAlarm;
}
