@WebAutomation @ResetPassword
Feature: Registration Process on VocaGame

  Background:
    Given User is on VocaGame Landing page
    And User clicks "Masuk" button
    And System navigate user to login page
    And User enters identifier as "Mentari.test@vq8tdcww.mailosaur.net"
    And User clicks "Masuk Akun" button
    And User enters password as "Mentari@2026"
    And User clicks "Masuk Akun" button
    And User should be navigated to "dashboard" page

  @OrderProduct
  Scenario: Purchase Nintendo eShop Card via QRIS
    When User input "Nintendo" on search bar
    And User selects Nintendo product
    Then User should be navigated to "nintendo" page
    And User verifies email field is filled correctly
    When User selects $10 denomination
    And User completes payment using QRIS
    Then User should see a toast with message "success checkout"
    And System should display order number and QR code