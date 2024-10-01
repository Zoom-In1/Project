package com.example.EcoCamper.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.EcoCamper.dto.MyReviewDTO;
import com.example.EcoCamper.dto.OrderlistDTO;
import com.example.EcoCamper.entity.ShopReview;

public interface ShopReviewRepository extends JpaRepository<ShopReview, Integer>{
	@Query(value = "select *from (select rownum rn, tt.*from"
			   + " (select * from Shop_Review  where shopreviewpcode=:pcode order by shopreviewseq desc) tt)"
			   + "  where rn >=:startNum and rn <=:endNum", nativeQuery = true)
	List<ShopReview> findbyStartNumAndEndNum(@Param("startNum")int startNum,@Param("endNum")int endNum,@Param("pcode")String pcode);
	
	long countByShopreviewpcode(String pcode);
	
	
	@Query(value = "select COUNT(*) from Shop_Review where shopreviewid like %:userId%", nativeQuery = true)
	int countByUserId(@Param("userId")String userId);
	
	
	@Query("SELECT new com.example.EcoCamper.dto.MyReviewDTO(r.shopreviewseq, r.shopreviewpcode, r.shopreviewid, "
	        + "r.shopreviewcontent, r.rating, r.logtime, s.pname) "
	        + "FROM ShopReview r "
	        + "JOIN Shop s ON r.shopreviewpcode = s.pcode "
	        + "WHERE r.shopreviewid = :userId "
	        + "ORDER BY r.logtime DESC")
	List<MyReviewDTO> findbyUserId(@Param("userId")String userId);
}
