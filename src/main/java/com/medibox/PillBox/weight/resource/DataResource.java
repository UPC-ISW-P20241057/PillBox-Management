package com.medibox.PillBox.weight.resource;

import jakarta.persistence.Column;
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
  private String ssid;
  private String password;
  private Boolean wifi;
}
