package com.advertisement.clicklog.repository;

import com.advertisement.clicklog.entity.ClickLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface ClickLogRepository extends JpaRepository<ClickLog, Integer> {

  int countAllByCampaign(final int campaign);

  @Query(
      "SELECT COUNT(c) FROM ClickLog c WHERE c.campaign = :campaign AND c.timestamp >= :startAt AND c.timestamp <= :endAt")
  int countByCampaignAndTimestampBetween(
      @Param("campaign") int campaign,
      @Param("startAt") LocalDateTime startAt,
      @Param("endAt") LocalDateTime endAt);

  @Query("SELECT COUNT(c) FROM ClickLog c WHERE c.campaign = :campaign AND c.timestamp >= :startAt")
  int countByCampaignAndTimestampAfter(
      @Param("campaign") int campaign, @Param("startAt") LocalDateTime startAt);

  @Query("SELECT COUNT(c) FROM ClickLog c WHERE c.campaign = :campaign AND  c.timestamp <= :endAt")
  int countByCampaignAndTimestampBefore(
      @Param("campaign") int campaign, @Param("endAt") LocalDateTime endAt);
}
