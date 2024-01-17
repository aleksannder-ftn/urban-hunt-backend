package com.ftn.sss.urbanhunt.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="guest")
public class Guest extends User {

   @ElementCollection
   @CollectionTable(name = "guest_real_estate_rating", joinColumns = @JoinColumn(name = "guest_id"))
   @MapKeyJoinColumn(name = "real_estate_id")
   @Column(name = "is_liked")
   private Map<RealEstate, Boolean> realEstateRating;

   @Override
   public boolean isAccountNonExpired() {
      return false;
   }

   @Override
   public boolean isAccountNonLocked() {
      return false;
   }

   @Override
   public boolean isCredentialsNonExpired() {
      return false;
   }

   @Override
   public boolean isEnabled() {
      return false;
   }
}
