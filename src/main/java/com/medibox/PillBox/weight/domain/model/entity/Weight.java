package com.medibox.PillBox.weight.domain.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "weights")
public class Weight {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "value", length = 50)
  private String value;

  @Column(name = "reminder", nullable = false)
  private Boolean reminder = false;
}
