package com.rootuss.BloodDonationCentre.donation.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class StatisticsResponseDto {
    private String bloodGroupWithRh;
    private Long quantity;
}
