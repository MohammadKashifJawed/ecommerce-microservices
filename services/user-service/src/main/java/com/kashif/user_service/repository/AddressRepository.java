package com.kashif.user_service.repository;

import com.kashif.user_service.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.Optional;

public interface AddressRepository extends JpaRepository<Address, Long> {
    List<Address> findByUserId(Long userId);


    @Query("""
        SELECT a FROM Address a
        WHERE a.id = :addressId
        AND a.userId = :userId
    """)
    Optional<Address> findByIdAndUserId(@Param("addressId") Long addressId, @Param("userId") Long userId);
}
