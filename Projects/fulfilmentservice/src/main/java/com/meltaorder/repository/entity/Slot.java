package com.meltaorder.repository.entity;

import static javax.persistence.GenerationType.IDENTITY;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Getter
@Setter
@Entity
@Table(name = "available_slot")
public class Slot {
  @Id
  @GeneratedValue(strategy = IDENTITY)
  private long id;
  @Column(name = "installation_date", nullable = false)
  private LocalDate installationDate;
  @Column(name = "start_time", nullable = false)
  private LocalTime startTime;
  @Column(name = "end_time", nullable = false)
  private LocalTime endTime;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name="installation_id", nullable=false)
  private InstallationAddress installationAddress;
}
