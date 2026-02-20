package com.blosny.servicepulse.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor 
@AllArgsConstructor 
public class SiteRequest {
    private String name;
    private String url;
}