# ServicePulse: Yapay Zeka Destekli Sistem İzleme ve Analiz Platformu

ServicePulse, kritik yazılım servislerinin erişilebilirliğini ve performansını gerçek zamanlı olarak takip eden, servis kesintisi durumunda Google Gemini AI entegrasyonu ile otomatik kök neden analizi (Root Cause Analysis) gerçekleştiren bir backend projesidir.

## Teknik Özellikler

- **Reaktif İzleme Mekanizması:** Spring WebFlux tabanlı WebClient kullanılarak, non-blocking yapıda yüksek performanslı servis kontrolleri gerçekleştirilir.
- **Yapay Zeka Entegrasyonu:** Servislerin çökme durumlarında dönen hata mesajları Gemini AI (1.5 Flash) modeline iletilerek, olası hata nedenleri ve teknik çözüm önerileri saniyeler içinde üretilir.
- **Kalıcı Veri Yönetimi:** Yapılan tüm kontroller, yanıt süreleri ve AI analizleri PostgreSQL veritabanında zaman serisi mantığıyla saklanır.
- **Zamanlanmış Görev Yönetimi:** Özelleştirilebilir Scheduler mekanizması ile servisler belirlenen aralıklarla otomatik olarak denetlenir.
- **Modern Görselleştirme:** Tailwind CSS tabanlı dinamik dashboard üzerinden servis durumları ve geçmiş analiz raporları izlenebilir.

## Kullanılan Teknolojiler

- **Backend:** Java 21, Spring Boot 3.4.x
- **Veritabanı:** PostgreSQL, Spring Data JPA
- **İletişim:** Spring Reactive Web (WebClient)
- **Yapay Zeka:** Google Gemini AI API
- **DevOps:** Docker, Docker Compose, GitHub Actions (CI)
- **Arayüz:** HTML5, Tailwind CSS, JavaScript (Fetch API)

## Yazılım Mimarisi

Proje, bağımlılıkların yönetimi ve sürdürülebilirlik açısından Hexagonal Architecture (Port and Adapter) prensiplerine uygun olarak katmanlandırılmıştır:

- **Domain:** İş mantığı ve temel modellerin (Site, CheckResult) bulunduğu, teknolojiden bağımsız çekirdek katman.
- **Application:** Kullanım senaryolarının (Usecases) ve zamanlanmış görevlerin yönetildiği katman.
- **Infrastructure:** Dış dünya bağlantılarının (PostgreSQL, Gemini API, WebController) gerçekleştirildiği adaptör katmanı.

## Kurulum ve Çalıştırma

Sistemi yerel makinenizde veya bir sunucuda konteynerize edilmiş halde çalıştırmak için aşağıdaki adımları izleyiniz:

1. **Repoyu Klonlayın:**
   git clone https://github.com/blosny/ServicePulse.git

2. **API Yapılandırması:**
   src/main/resources/application.properties dosyasına giderek Gemini API anahtarınızı tanımlayınız:
   gemini.api.key=ANAHTARINIZ

3. **Projeyi Paketleyin:**
   ./mvnw clean package -DskipTests

4. **Sistemi Docker ile Ayağa Kaldırın:**
   docker compose up --build

5. **Erişim:**
   Uygulama arayüzüne http://localhost:8080 adresinden ulaşabilirsiniz.

## DevOps Süreçleri

- **Konteynerleştirme:** Uygulama ve veritabanı Docker Compose ile izole bir ağda orkestre edilmektedir.
- **Sürekli Entegrasyon (CI):** GitHub Actions kullanılarak, her push işleminde kodun derleme ve paketleme süreçleri otomatik olarak test edilmektedir.
