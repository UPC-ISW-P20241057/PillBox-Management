package com.medibox.PillBox.weight.resource;

import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class UpdateDataResource {
  private Long id;
  //private String value;
  private Boolean reminder;
  //private Boolean isEmpty;
  //private Boolean almostEmpty;
  private Integer numberAlarm;
  private String ssid;
  private String password;
  //private Boolean wifi;
}
