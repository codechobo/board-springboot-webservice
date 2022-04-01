package com.example.boardspringbootwebservice.config;

import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.stereotype.Component;

@Component
@EnableJpaAuditing // JAP Auditing 활성화
public class JpaAuditingConfig {
}
