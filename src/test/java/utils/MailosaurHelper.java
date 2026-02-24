package utils;

import com.mailosaur.MailosaurClient;
import com.mailosaur.models.Message;
import com.mailosaur.models.MessageSearchParams;
import com.mailosaur.models.SearchCriteria;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MailosaurHelper {

    // Memastikan API Key dan Server ID tersedia
    private static final String API_KEY = ConfigReader.get("mailosaur.api.key");
    private static final String SERVER_ID = ConfigReader.get("mailosaur.server.id");

    /**
     * Mengambil kode OTP terbaru dari email tertentu.
     *
     * @param emailAddress Alamat email tujuan (e.g. user.SERVER_ID@mailosaur.net)
     * @return String kode OTP atau null jika tidak ditemukan
     */
    public static String getOTPFromEmail(String emailAddress) {
        if (API_KEY == null || SERVER_ID == null) {
            throw new RuntimeException("Mailosaur config missing!");
        }

        MailosaurClient client = new MailosaurClient(API_KEY);

        MessageSearchParams params = new MessageSearchParams()
                .withServer(SERVER_ID)
                .withTimeout(10000); // timeout per attempt

        SearchCriteria criteria = new SearchCriteria()
                .withSentTo(emailAddress);

        int maxRetry = 5;
        int attempt = 0;

        while (attempt < maxRetry) {
            try {
                System.out.println("[Mailosaur] Attempt " + (attempt + 1));

                Message message = client.messages().get(params, criteria);

                if (message != null) {

                    System.out.println("====== MAILOSAUR DEBUG ======");
                    System.out.println("Subject: " + message.subject());
                    System.out.println("Text Body: " + message.text().body());
                    System.out.println("HTML Body: " + message.html().body());
                    System.out.println("=============================");

//                     Mengambil OTP dari SUBJECT
                    Matcher subjectMatcher = Pattern.compile("\\d{6}")
                            .matcher(message.subject());
                    if (subjectMatcher.find()) {
                        return subjectMatcher.group();
                    }
//
//                    // 2️⃣ Coba ambil dari HTML BODY (INI YANG PENTING)
                    String htmlBody = message.html().body();
                    Matcher htmlMatcher = Pattern.compile("\\d{6}")
                            .matcher(htmlBody);
                    if (htmlMatcher.find()) {
                        return htmlMatcher.group();
                    }

//                    // 3️⃣ (Optional) fallback ke text body
                    String textBody = message.text().body();
                    Matcher textMatcher = Pattern.compile("\\d{6}")
                            .matcher(textBody);
                    if (textMatcher.find()) {
                        return textMatcher.group();
                    }
                }
            } catch (Exception e) {
                System.out.println("[Mailosaur] OTP not found yet, retrying...");
            }

            attempt++;
            try {
                Thread.sleep(4000); // TUNGGU 4 DETIK
            } catch (InterruptedException ignored) {
            }
        }

        return null;
    }
}