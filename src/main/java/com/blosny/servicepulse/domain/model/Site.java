package com.blosny.servicepulse.domain.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity // bu sınıfın veritabanında bir tablo olacağını belirtir.
@Table(name = "sites") // tablo adını 'sites' olarak belirledik.
@Data // getter, setter, equals, hashCode ve toString metodlarını otomatik oluşturur.
@Builder 
@NoArgsConstructor // JPA için parametresiz boş constructor.
@AllArgsConstructor // Builder için tüm parametreli constructor.
public class Site {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name; // sitenin adı

    @Column(nullable = false, unique = true)
    private String url; // sitenin adresi 

    private boolean isUp; // site ayakta mı (true-false)

    private LocalDateTime createdAt; // kayıt tarihi

    @PrePersist // veritabanına kaydedilmeden hemen önce çalışır.
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}