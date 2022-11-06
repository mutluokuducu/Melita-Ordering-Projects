package com.meltaorder.repository.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Entity
@Getter
@Setter
@Table(name = "Installation_address")
public class InstallationAddress {

  @Id
  @GeneratedValue(strategy = IDENTITY)
  private long id;
  @Column(name = "address_line1", nullable = false)
  private String addressLine1;
  @Column(name = "address_line2")
  private String addressLine2;
  @Column(name = "address_line3")
  private String addressLine3;
  @Column(name = "post_code")
  private String postCode;
  @Column(name = "city", nullable = false)
  private String city;
  @Column(name = "country", nullable = false)
  private String country;

  @OneToMany(mappedBy = "installationAddress", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private List<Slot> slotList;

  @OneToOne(mappedBy = "installationAddress")
  private PersonalDetails personalDetails;
}