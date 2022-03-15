package com.sp.fc.web.config;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;
import lombok.Builder;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RequestInfo {
    private String remoteIp;
    private String sessionId;
    private LocalDateTime loginTime;
}
