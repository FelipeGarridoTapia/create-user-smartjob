package com.smartjob.createuser.dtos.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PhoneDto {
    private String number;
    private String cityCode;
    private String countryCode;
}
