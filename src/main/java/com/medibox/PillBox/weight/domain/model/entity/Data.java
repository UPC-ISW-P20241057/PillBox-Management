package com.medibox.PillBox.weight.domain.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "data")
public class Data {
  @Id
  private Long id;

  @Column(name = "value", length = 50)
  private String value;

  @Column(name = "reminder", nullable = false)
  private Boolean reminder;

  @Column(name = "is_empty", nullable = false)
  private Boolean isEmpty;

  @Column(name = "almost_empty", nullable = false)
  private Boolean almostEmpty;

  @Column(name = "number_alarm", nullable = false)
  private Integer numberAlarm;
}
