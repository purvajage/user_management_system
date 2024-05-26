package com.demo.resource.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.resource.entities.Tp;

@Repository
public interface TpRepository extends JpaRepository<Tp, Long>  {

}
