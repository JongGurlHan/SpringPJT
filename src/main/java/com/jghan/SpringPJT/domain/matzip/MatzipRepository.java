package com.jghan.SpringPJT.domain.matzip;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MatzipRepository extends JpaRepository<Matzip, Integer> {

//    @Modifying
//    @Query(value = "INSERT INTO matzip(name, category, address, url, lat, lng, userId, createDate) " +
//                    "VALUES( :name, :category, :address, :url, :lat, :lng, :userId, now())", nativeQuery = true)
//    Matzip mSave(@Param("name") String name,
//                 @Param("category") String category,
//                 @Param("address") String address,
//                 @Param("url") String url,
//                 @Param("lat") String lat),
//                 @Param("userId") String userId)
//            );



}
