package com.mohanjp.store.repositories;

import com.mohanjp.store.entity.ProfileEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProfileRepository extends CrudRepository<ProfileEntity, Long> {

    // custom query with SQL
    @Query(value = "select * from profiles where loyalty_Points > :loyaltyPoints", nativeQuery = true)
    List<ProfileEntity> findProfilesByLoyaltyPoints(@Param("loyaltyPoints") Integer loyaltyPoints);

    // custom query with JPQL
    @Query("select p from ProfileEntity p where p.loyaltyPoints > :loyaltyPoints")
    List<ProfileEntity> findProfilesByLoyaltyPointsJPQL(Integer loyaltyPoints);
}