# Automation Testing
## Prasyarat

1. Pastikan Anda telah menginstal Java JDK versi 17. Anda dapat mengunduh dan menginstal Java JDK dari situs resminya: [Java SE Development Kit 16 Downloads](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html).

   Untuk memeriksa apakah Java JDK sudah terinstal, jalankan perintah berikut di terminal:
    ```
    java -version
    ``` 

3. Pastikan Anda telah menginstal Apache Maven. Maven adalah alat manajemen proyek yang digunakan untuk mengelola dependensi dan membangun proyek Java. Anda dapat mengunduh dan menginstal Maven dari situs resminya: [Apache Maven Download](https://maven.apache.org/download.cgi).
   Untuk memeriksa apakah Maven sudah terinstal, jalankan perintah berikut di terminal:
     ```
    mvn -v
    ```

4. Pastikan Anda telah menginstal plugin `Cucumber for Java` dan `Gherkins` untuk mendukung pengujian otomatis. Bila Anda menggunakan IntelliJ, Anda bisa menginstalnya dari plugin dengan menggunakan shortcut `ctrl + alt + s`, lalu instal plugin yang diperlukan. Jangan lupa untuk merestart IDE setelah plugin berhasil ditambahkan.
   <br> <img src="main/src/image/plugin.png">




## Cara Menjalankan

1. Buka proyek dengan IDE favorit Anda. Disarankan untuk menggunakan IntelliJ IDEA untuk kenyamanan pengembangan.

2. Lakukan build proyek untuk menyiapkan aplikasi dengan perintah build di IDE Anda atau dengan perintah:
    ```
    mvn clean install
    ```

3. Jalankan program untuk melakukan pengujian otomatis dengan perintah:
    ```
    mvn test
    ```
   Ini akan menjalankan program secara otomatis dan mengeksekusi skenario berdasarkan isi file test runner yaitu `CucumberRunnerTest.java`


# Alat dan Teknologi yang Digunakan

1. ![Maven](https://img.shields.io/badge/Maven-Versi_3.9.6-%2374CBEB)
   <br>Maven digunakan sebagai alat manajemen proyek untuk mengelola dependensi dan membangun proyek Java. Kunjungi [situs web Maven](https://maven.apache.org/) untuk informasi lebih lanjut.

2. ![](https://img.shields.io/badge/JUnit-Framework_Pengujian-%23FFE57E)
   <br>JUnit adalah framework pengujian unit untuk bahasa pemrograman Java. Kunjungi [situs web JUnit](https://junit.org/junit5/) untuk informasi lebih lanjut.

3. ![Cucumber for Java](https://img.shields.io/badge/Cucumber_for_Java-Framework_Pengujian-%23efcfe3)
   <br>Cucumber for Java adalah framework pengujian otomatis yang menggunakan bahasa Gherkin untuk menulis skenario pengujian. Kunjungi [situs web Cucumber](https://cucumber.io/docs/guides/10-minute-tutorial/) untuk informasi lebih lanjut.

4. ![Gherkin](https://img.shields.io/badge/Gherkin-Bahasa_Pengujian-%2393D3D5)
   <br>Gherkin adalah bahasa alami yang digunakan untuk menulis skenario pengujian dalam kerangka kerja pengujian perilaku seperti Cucumber. Kunjungi [dokumentasi Cucumber](https://cucumber.io/docs/gherkin/) untuk informasi lebih lanjut.

4. ![Selenium](https://img.shields.io/badge/Selenium-Framework_Pengujian-%23FF69B4)
   <br>Selenium adalah library untuk otomatisasi pengujian web. Kunjungi [situs web Selenium](https://www.selenium.dev/documentation/en/) untuk informasi lebih lanjut.

5. ![ChromeDriver](https://img.shields.io/badge/ChomeDriver-WebDriver-%2374CB)
   <br>ChromeDriver adalah driver untuk mengotomatisasi pengujian di browser Microsoft Edge. Kunjungi [situs web Selenium](https://www.selenium.dev/documentation/en/) untuk informasi lebih lanjut.

# Pengembang
### [<img src="https://github.com/mentarialysias.png" width="20" style="border-radius:50%" >](https://github.com/mentarialysias) <i> Mentari Ayu Alysia Sudrajat </i> 




