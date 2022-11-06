package com.meltaorder.service;

import static com.meltaorder.utils.ObjectFactory.buildPersonalDetails;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.meltaorder.dto.EmailDto;
import com.meltaorder.services.EmailService;
import freemarker.cache.ClassTemplateLoader;
import freemarker.cache.FileTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.ui.freemarker.FreeMarkerConfigurationFactoryBean;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

@SpringBootTest
public class EmailServiceTest {

  @InjectMocks
  private EmailService emailService;

  @Mock
  private JavaMailSender javaMailSender;

  @Mock
  private MimeMessage message;

  @Mock
  private JavaMailSender mailSender;
  @Mock
  private FreeMarkerConfigurer freemarkerConfig;

  @Mock
  private FreeMarkerTemplateUtils freeMarkerTemplateUtils;

//  @Before
//  public void setUp() throws IOException {
//    initMocks(this);
//    emailService = new EmailService(javaMailSender);
//  }

  @Test
  public void sendEmail_sendsEmailWithCorrectContent()
      throws MessagingException, IOException, TemplateException {
    ArgumentCaptor<MimeMessage> mimeMessageArgumentCaptor = ArgumentCaptor.forClass(
        MimeMessage.class);


    when(javaMailSender.createMimeMessage()).thenReturn(message);
    when(mailSender.createMimeMessage()).thenReturn(message);

    doNothing().when(javaMailSender).send(message);
   when(freemarkerConfig.getConfiguration()).thenReturn(getConfiguration());
    emailService.sendEmail(buildPersonalDetails());

    //verify(javaMailSender, times(0)).send((mimeMessageArgumentCaptor.capture()));
    verify(emailService).sendEmail(buildPersonalDetails());
    assertEquals("subject", mimeMessageArgumentCaptor.getValue().getSubject());
  }

  @Test
  public void sendHtmlMessageTest() throws MessagingException {

    Map<String, Object> templateData = new HashMap<>();
    templateData.put("id", "1");
    templateData.put("fullName", "Alex");
    templateData.put("phoneNumber", "12334566");
    templateData.put("emailAddress", "tex@text.com");
    EmailDto emailDto = EmailDto.builder()
        .to("order@gmail.com")
        .subject("subject")
        .emailData(templateData)
        .build();

    Assertions.assertDoesNotThrow(() -> emailService.sendEmail(buildPersonalDetails()));

  }

  public Configuration getConfiguration() throws IOException {
    ClassTemplateLoader ctl = new ClassTemplateLoader(getClass(), "templates");
    FileTemplateLoader fileTemplateLoader = new FileTemplateLoader(new File("com/meltaorder/templates"));
    Configuration config = new Configuration();
    config.setTemplateLoader(ctl);
    return config;
  }



}
