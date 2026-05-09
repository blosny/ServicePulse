# ServicePulse: Yapay Zeka Destekli Sistem İzleme ve Analiz Platformu

ServicePulse, kritik yazılım servislerinin erişilebilirliğini ve performansını gerçek zamanlı olarak takip eden, servis kesintisi durumunda **Google Gemini AI** entegrasyonu ile otomatik kök neden analizi (Root Cause Analysis) gerçekleştiren modern bir backend projesidir.

## Teknik Özellikler
- **Reaktif İzleme Mekanizması**: Spring WebFlux (WebClient) kullanılarak non-blocking yapıda yüksek performanslı servis kontrolleri.
- **AI Destekli Analiz**: Servislerin çökme durumlarında dönen hata mesajları **Gemini AI (1.5 Flash)** modeline iletilerek saniyeler içinde çözüm önerileri üretilir.
- **Zamanlanmış Görevler**: Özelleştirilebilir Scheduler mekanizması ile servisler belirlenen aralıklarla otomatik denetlenir.
- **Kalıcı Veri Yönetimi**: Yanıt süreleri ve AI analizleri **PostgreSQL** veritabanında zaman serisi mantığıyla saklanır.
- **Dinamik Dashboard**: Tailwind CSS tabanlı arayüz üzerinden servis durumları ve geçmiş analiz raporları izlenebilir.
- **Otomatik Uyarılar**: Servis kesintilerinde anlık bildirim ve durum takibi.

## Teknoloji Yığını
- **Backend**: Java 21, Spring Boot 3.4.x
- **Veritabanı**: PostgreSQL, Spring Data JPA
- **İletişim**: Spring Reactive Web (WebClient)
- **AI**: Google Gemini AI API
- **DevOps**: Docker, Docker Compose, GitHub Actions (CI)
- **Arayüz**: HTML5, Tailwind CSS, JavaScript (Fetch API)

## Yazılım Mimarisi
Proje, sürdürülebilirlik için **Hexagonal Architecture (Port and Adapter)** prensiplerine uygun olarak katmanlandırılmıştır:
- **Domain**: İş mantığı ve temel modellerin bulunduğu teknolojiden bağımsız çekirdek katman.
- **Application**: Kullanım senaryolarının (Usecases) ve zamanlanmış görevlerin yönetildiği katman.
- **Infrastructure**: Dış dünya bağlantılarının (PostgreSQL, Gemini API, WebController) gerçekleştirildiği adaptör katmanı.

## Kurulum ve Çalıştırma

1. **Repoyu Klonlayın**:
   ```bash
   git clone https://github.com/blosny/ServicePulse.git
   cd ServicePulse
   ```

2. **API Yapılandırması**:
   `src/main/resources/application.properties` dosyasına Gemini API anahtarınızı ekleyin:
   ```properties
   gemini.api.key=YOUR_API_KEY_HERE
   ```

3. **Docker ile Başlatma**:
   Projeyi konteynerize edilmiş halde ayağa kaldırın:
   ```bash
   ./mvnw clean package -DskipTests
   docker compose up --build
   ```

4. **Erişim**:
   Uygulama arayüzüne `http://localhost:8080` adresinden ulaşabilirsiniz.

## DevOps ve CI/CD
- **Konteynerleştirme**: Docker Compose ile izole ağda orkestrasyon.
- **Sürekli Entegrasyon**: GitHub Actions ile her push işleminde otomatik derleme ve test süreçleri.

---
*Geliştirici: [blosny](https://github.com/blosny)*
