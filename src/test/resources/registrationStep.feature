@WebAutomation @Registration
Feature: Registration Process on VocaGame

  Background:
    Given User is on VocaGame Landing page
    When User clicks "Masuk" button
    And System navigate user to login page
    And User clicks "Daftar Sekarang" button
    Then User is successfully navigated to the Registration page

  @PageVerification @TC01
  Scenario: Verify Registration Page initial UI state
    Then The registration page should display the following elements:
      | field            | visibility | state    | placeholder         |
      | first_name       | displayed  | enabled  | Nama Depan          |
      | last_name        | displayed  | enabled  | Nama Belakang       |
      | whatsapp_number  | displayed  | enabled  | Nomor WhatsApp      |
      | email            | displayed  | enabled  | Email               |
      | password         | displayed  | enabled  | Password            |
      | confirm_password | displayed  | enabled  | Konfirmasi Password |
      | buat_akun        | displayed  | disabled | Buat Akun           |
    And All password criteria indicators should be in default state
    And The "Syarat dan Ketentuan" checkbox should be unchecked

  @PositiveRegistrationCase @TCR02
  Scenario: Register with valid data
    When User fills the registration form with following data:
      | field           | value              |
      | first_name      | Testing            |
      | last_name       | Automation Testing      |
      | whatsapp_number | 0812345678929       |
      | email           | Mentari.test2@vq8tdcww.mailosaur.net |
      | password        | Mentari@2026       |
    Then User verifies all password criteria indicators are checked and green
    When User checks the "Syarat dan Ketentuan" checkbox
    Then The Buat Akun button status should be enabled
    When User clicks "Buat Akun" button
    And User selects "Email" as the verification method
    And User clicks "Lanjut" button
    Then User should see a toast with message "OTP has been sent via email"
    And System show OTP verifications modal
    When User inputs the valid OTP code from email
    Then The "Verifikasi" button color should be green and status is active
    When User clicks "Verifikasi" button
    Then User should see a success toast "OTP Valid"
    And User is automatically redirected to the Login page

#  @NegativeCase @InvalidInput @TC03_to_TC11
#  Scenario Outline: Registration with invalid or incomplete data
#    When User enters "<field>" as "<value>"
#    Then User should see an error validation message "<error_message>"
#    And The "Buat Akun" button should remain disabled
#
#    Examples:
#      | test_case | test_case_name               | field          | value           | error_message             |
#      | TC03      | Short Phone Number           | nomor_whatsapp | 0812            | Minimal 10 digit          |
#      | TC04      | Registered Phone Number      | nomor_whatsapp | 081234567890    | Nomor sudah terdaftar     |
#      | TC05      | Invalid Email Format         | email          | mentari.ayu     | Format email tidak valid  |
#      | TC06      | Registered Email             | email          | user@exists.com | Email sudah digunakan     |
#      | TC07      | Password lacks numbers       | password       | Mentari@        | Harus mengandung angka    |
#      | TC08      | Password lacks symbols       | password       | Mentari2026     | Harus mengandung simbol   |
#      | TC09      | Password too short           | password       | Men1@           | Minimal 8 karakter        |
#      | TC10      | Empty Name Field             | nama_lengkap   |                 | Nama tidak boleh kosong   |
#      | TC11      | Valid data but T&C unchecked | checkbox       | unchecked       | [Button remains disabled] |

#  @NegativeCase @OTPValidation @TC12_to_TC13
#  Scenario Outline: OTP Verification failures
#    Given User is on the OTP Verification page
#    When User enters OTP code as "<otp_input>"
#    And User clicks "Verifikasi"
#    Then User should see an error message "<error_feedback>"
#    And User remains on the OTP page
#
#    Examples:
#      | test_case | test_case_name     | otp_input | error_feedback       |
#      | TC12      | Incorrect OTP Code | 000000    | Kode OTP tidak valid |
#      | TC13      | Expired OTP Code   | 123456    | Kode OTP kedaluwarsa |

#skenario regist dengan email sudah terdaftar
#  skenario regist dengan wa sudah terdaftar
#    Then User should see a success toast "User sudah terdaftar. Silahkan coba menggunakan email atau nomor handphone lain"

