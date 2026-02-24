@LoginVocagame @WebAutomation
Feature: Login Functionality for Vocagame

  Background:
    Given User is on Vocagame Login page

  @UI @TCL01
  Scenario: Verifikasi elemen UI awal halaman login
    Then User should see brand logo and hero image
    And The page title should be "Masuk Akun Vocagame"
    And The subtitle should be "Masukan nomor WhatsApp / Email dan password kamu untuk masuk."
    And Identifier field is displayed with label "Nomor HP atau email"
    And Masuk Akun button should be disabled
    And Google login button and "Daftar Sekarang" link are visible

  @Positive @TCL02
  Scenario: Login berhasil menggunakan Email
    When User enters identifier as "Mentari.test@vq8tdcww.mailosaur.net"
    And User clicks "Masuk Akun" button
    Then User should see password field
    When User enters password as "Mentari@2026"
    And User clicks "Masuk Akun" button
    Then User should see a toast with message "ok"
    And User should be navigated to "dashboard" page

  @Negative @DataDriven @TCL03
  Scenario: Validasi login email tidak valid
    When User enters identifier as "mentari@gmailco"
    Then User should see error "Email harus berupa alamat email yang valid" with type "inline"
    And Masuk Akun button should be disabled

  @Negative @DataDriven @TCL04 @@TCL05 @TCL06 @TCL07
  Scenario Outline: Validasi login dengan berbagai kondisi kesalahan
    When User enters identifier as "<identifier>"
    When User clicks "Masuk Akun" button
    And User enters password as "<password>" if field is displayed
    When User clicks "Masuk Akun" button
    Then User should see error "<error_message>" with type "<error_type>"

    Examples:
      | test_case | identifier                      | password   | error_type | error_message                                                             |
      | TC04      | mentari9@vq8tdcww.mailosaur.net | 123        | inline     | Kata sandi minimal harus 6 karakter.                                      |
      | TC05      | salahin@gmail.com               | pass123456 | toast      | Email tidak ditemukan. Harap gunakan data yang valid dan terdaftar        |
      | TC06      | 088882223332                    | pass123456 | toast      | Phone Number tidak ditemukan. Harap gunakan data yang valid dan terdaftar |
      | TC07      | 081234567890                    | pass123456 | toast      | Kata sandi salah. Verifikasi dan coba lagi.                               |
      | TC08      | mentari@gmail.com               | sandisalah | toast      | Kata sandi salah. Verifikasi dan coba lagi                                |

  @EdgeCase @TC08
  Scenario: Mengubah email melalui icon pensil setelah lanjut ke step password
    When User enters identifier as "mentari@gmail.com"
    And User clicks "Masuk Akun" button
    Then User should see password field
    When User clicks Edit pencil icon
    Then User should be back to identifier step
    And Identifier field should contain "mentari@gmail.com"