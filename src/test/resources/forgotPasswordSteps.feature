@WebAutomation @ResetPassword
Feature: Registration Process on VocaGame

  Background:
    Given User is on VocaGame Landing page
    When User clicks "Masuk" button
    And System navigate user to login page

  @PositifcaseReset @TCR01
  Scenario: Register with valid data
    When User clicks "Lupa Password" button
    And User should be navigated to "forgot-password" page
    When User enters identifier as "Mentari.test@vq8tdcww.mailosaur.net"
    When User clicks "Kirim Kode OTP" button
    And User selects "Email" as the verification method
    And User clicks "Lanjut" button
    Then User should see a toast with message "OTP has been sent via email"
    And System show OTP verifications modal
    When User inputs the valid OTP code from email
    Then The "Verifikasi" button color should be green and status is active
    When User clicks "Verifikasi" button
    Then User should see a success toast "OTP Valid"
    And System show reset password page layout
    And User enters new password as "Mentari@2027"
    Then User should see a toast with message "Successfully Reset Password"
    And User is automatically redirected to the Login page
