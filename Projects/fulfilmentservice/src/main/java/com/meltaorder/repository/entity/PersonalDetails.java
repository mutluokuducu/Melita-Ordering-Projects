package com.meltaorder.repository.entity;

import static javax.persistence.EnumType.STRING;
import static javax.persistence.GenerationType.IDENTITY;

import com.meltaorder.dto.response.ApproveStatus;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.ToString.Exclude;
import org.hibernate.Hibernate;

@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Entity
@Getter
@Setter
@ToString
@Table(name = "personal_details")
public class PersonalDetails {

  @Id
  @GeneratedValue(strategy = IDENTITY)
  private long id;
  @Column(name = "full_name", nullable = false)
  private String fullName;
  @Column(name = "phone_number", nullable = false)
  private String phoneNumber;
  @Column(name = "email_address", nullable = false)
  private  String  emailAddress;
  @Column(name = "birth_date", nullable = false)
  private LocalDate birthDate;
  @Column(name = "date_time", nullable = false)
  private LocalDateTime localDateTime;
  @Column(name = "approve_status", nullable = false)
  @Enumerated(STRING)
  private ApproveStatus approveStatus;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "address_id", referencedColumnName = "id")
  private InstallationAddress installationAddress;
  @OneToMany(mappedBy = "personalDetails",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @Exclude
  private List<Packages> packagesList;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
      return false;
    }
    PersonalDetails that = (PersonalDetails) o;
    return Long.valueOf( id )!= null && Objects.equals(id, that.id);
  }

  @Override
  public int hashCode() {
    return getClass().hashCode();
  }
}