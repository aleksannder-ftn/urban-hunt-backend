package com.ftn.sss.urbanhunt.repository.interfaces;

import com.ftn.sss.urbanhunt.model.Guest;
import com.ftn.sss.urbanhunt.model.Owner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
public interface GuestRepository extends JpaRepository<Owner, Long> {

}
