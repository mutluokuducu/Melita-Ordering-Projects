package com.meltaorder.repository.entity;

import static javax.persistence.EnumType.STRING;
import static javax.persistence.GenerationType.IDENTITY;

import com.meltaorder.dto.response.Products;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Getter
@Setter
@Entity
@Table(name = "ordering_packages")
public class Packages {
 @Id
 @GeneratedValue(strategy = IDENTITY)
 private long id;
 @Column(name = "products_name", nullable = false)
 @Enumerated(STRING)
 private Products productsName;
 @Column(name = "package_name", nullable = false)
 private String packageName;

 @Column(name = "date_time", nullable = false)
 private LocalDateTime localDateTime;
 @ManyToOne(fetch = FetchType.LAZY)
 @JoinColumn(name="person_id", nullable=false)
 private PersonalDetails personalDetails;

}
