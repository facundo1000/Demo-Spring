package com.demospring.usersapp.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "adress")
public class Address {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Integer id;

  @Column(name = "street")
  private String street;

  @Column(name = "number")
  private String number;

  @Column(name = "city")
  private String city;

  @ManyToOne private Profile profile;
}
