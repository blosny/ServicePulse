package com.blosny.servicepulse.domain.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "check_results")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class CheckResult {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne // birçok kontrol sonucu tek bir siteye ait olabilir
    @JoinColumn(name = "site_id", nullable=false)
    private Site site; // hangi siteyi kontrol ettik 

    private long statusCode; // http durum kodu (200, 404, 500 gibi)

    private long responseTime; // yanıt süresi (ms cinsinden)

    private boolean success;

    @Column(columnDefinition = "TEXT")
    private String errorMessage; // ai tarafına gidecek hata mesajı

    @Column(columnDefinition = "TEXT")
    private String aiAnalysis; // ai tarafından gelen yorum

    private LocalDateTime checkedAt;
}
