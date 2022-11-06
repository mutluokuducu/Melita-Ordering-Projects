package com.meltaorder.services;


import com.meltaorder.dto.EmailDto;
import com.meltaorder.repository.entity.PersonalDetails;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;


@Service
@RequiredArgsConstructor
@Slf4j
public class EmailService {

  @Autowired
  private JavaMailSender mailSender;
  @Autowired
  private FreeMarkerConfigurer freemarkerConfig;

  @Value("${mail.to}")
  private String to;

  @Value("${mail.subject}")
  private String subject;

  private static final Logger LOGGER = LoggerFactory.getLogger(EmailService.class);

  public void sendEmail(PersonalDetails personalDetails) {
    LOGGER.info("##### Started sending email #");
    String address = personalDetails.getInstallationAddress().getAddressLine1() + " "
        + personalDetails.getInstallationAddress().getAddressLine2() + " "
        + personalDetails.getInstallationAddress().getAddressLine3();

    Map<String, Object> templateData = new HashMap<>();
    templateData.put("id", personalDetails.getId());
    templateData.put("fullName", personalDetails.getFullName());
    templateData.put("phoneNumber", personalDetails.getPhoneNumber());
    templateData.put("emailAddress", personalDetails.getEmailAddress());

    templateData.put("addressLine", address);
    templateData.put("postCode", personalDetails.getInstallationAddress().getPostCode());
    templateData.put("city", personalDetails.getInstallationAddress().getCity());
    templateData.put("country", personalDetails.getInstallationAddress().getCountry());

    List<String> dateSlot = new LinkedList<>();
    personalDetails.getInstallationAddress().getSlotList()
        .forEach(x -> dateSlot.add(x.getInstallationDate().toString()));
    templateData.put("installationDate", dateSlot);
    List<String> startTime = new LinkedList<>();
    personalDetails.getInstallationAddress().getSlotList()
        .forEach(x -> startTime.add(x.getStartTime().toString()));
    templateData.put("startTime", startTime);
    List<String> endTime = new LinkedList<>();
    personalDetails.getInstallationAddress().getSlotList()
        .forEach(x -> endTime.add(x.getEndTime().toString()));
    templateData.put("endTime", endTime);

    List<String> productsName = new LinkedList<>();
    personalDetails.getPackagesList().forEach(x -> productsName.add(x.getProductsName().name()));
    templateData.put("productsName", productsName);

    List<String> packageName = new LinkedList<>();
    personalDetails.getPackagesList().forEach(x -> packageName.add(x.getPackageName()));
    templateData.put("packageName", packageName);

    EmailDto emailDto = EmailDto.builder()
        .to(to)
        .subject(subject)
        .emailData(templateData)
        .build();

    MimeMessage mimeMessage = mailSender.createMimeMessage();
    try {

      MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,
          MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
          StandardCharsets.UTF_8.name());

      String templateContent = FreeMarkerTemplateUtils
          .processTemplateIntoString(freemarkerConfig.getConfiguration()
                  .getTemplate("/email-template.ftlh"),
              emailDto.getEmailData());

      helper.setTo(emailDto.getTo());
      helper.setSubject(emailDto.getSubject());
      helper.setText(templateContent, true);
      mailSender.send(mimeMessage);

      LOGGER.info("##### Email send #");
    } catch (Exception e) {
      System.out.println("Sending email failed, check log...");

      LOGGER.error("##### Email getting error #" + e.getMessage());
      e.printStackTrace();
    }
  }
}
