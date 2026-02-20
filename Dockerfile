# 1. Aşama: Çalışma ortamı (Java 21)
FROM eclipse-temurin:21-jdk-alpine

# 2. Aşama: Uygulama klasörü
WORKDIR /app

# 3. Aşama: target klasöründeki JAR'ı konteyner içine kopyala
COPY target/*.jar app.jar

# 4. Aşama: Uygulamayı çalıştır
ENTRYPOINT ["java", "-jar", "app.jar"]