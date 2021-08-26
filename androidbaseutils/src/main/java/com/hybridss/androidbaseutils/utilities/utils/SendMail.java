package com.hybridss.androidbaseutils.utilities.utils;


import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMail {

    private final String username = "kops90@live.com";
    private final String password = "migueloo2550082";
    private final String correo;

    private String html = "<!DOCTYPE html>\n" +
            "<html>\n" +
            "  <head>\n" +
            "    <style type=\"text/css\">\n" +
            "      a .yshortcuts:hover {\n" +
            "        background-color: transparent !important;\n" +
            "        border: none !important;\n" +
            "        color: inherit !important\n" +
            "      }\n" +
            "      a .yshortcuts:active {\n" +
            "        background-color: transparent !important;\n" +
            "        border: none !important;\n" +
            "        color: inherit !important\n" +
            "      }\n" +
            "      a .yshortcuts:focus {\n" +
            "        background-color: transparent !important;\n" +
            "        border: none !important;\n" +
            "        color: inherit !important\n" +
            "      }\n" +
            "    </style>\n" +
            "    <style media=\"only screen and (max-width: 520px)\" type=\"text/css\">\n" +
            "      /* /\\/\\/\\/\\/\\/\\/\\/\\/ RESPONSIVE MOJO /\\/\\/\\/\\/\\/\\/\\/\\/ */\n" +
            "      /*  must escape media query with double symbol */\n" +
            "      @media only screen and (max-width: 520px) {\n" +
            "        .main-table {\n" +
            "          width: 90% !important;\n" +
            "        }\n" +
            "        .top {\n" +
            "          padding-top: 33px !important;\n" +
            "          padding-bottom: 37px !important;\n" +
            "        }\n" +
            "        .content {\n" +
            "          padding: 24px 29px !important;\n" +
            "        }\n" +
            "        .verify-button {\n" +
            "          padding: 25px 0 !important;\n" +
            "        }\n" +
            "      }\n" +
            "    </style>\n" +
            "  </head>\n" +
            "  <body align=\"center\" style=\"margin:0; padding:0; -webkit-text-size-adjust:100%; -ms-text-size-adjust:100%; background:#ffffff; width:100%; font-family:'Roboto',sans-serif; font-size:16px; text-align:center; line-height:22px; color:#AAB2BA\" width=\"100%\">\n" +
            "    <table class=\"main-table\" height=\"100%\" style=\"border-collapse:collapse !important; mso-table-lspace:0pt; mso-table-rspace:0pt; font-family:'Roboto', sans-serif; padding-left:0; padding-right:0; padding-top:0; padding-bottom:0; margin:20px auto 10px; padding:0; height:100%; width:80%; max-width:600px\" width=\"80%\">\n" +
            "      <tr>\n" +
            "        <td align=\"center\" class=\"top\" style=\"border-collapse:collapse !important; mso-table-lspace:0pt; mso-table-rspace:0pt; font-family:'Roboto', sans-serif; padding-left:0; padding-right:0; padding-top:72px; padding-bottom:48px\" valign=\"top\">\n" +
            "          <a data-click-track-id=\"3182\" href=\"http://links.similarweb.com/ls/click?upn=JbPuKfV-2Bu6Hl38CZidBHW9DNXcqZvn-2Fgow2fQgxcS7cG5VaQYuTMOQiobieYPK171AAA_jYfCVuKHG2lyBUzkteIayk7yiQpmByTqL-2BkMLjkIRPIV5vSm2FEcEyimIOqSF07dxa0eEU-2BDYs-2BSnlPMO-2B-2FJLrQwNQamOawiPENt1BlXoGghObzCjwWAcw56bfkAyIKesNHVTTRuufhFocJYCdfg9wpQMcUnhXCHEyxoYHlzp5GnsVcrObXe-2F-2FGGwu-2Bfp9cy8ntetJtx-2BuryTiNy0xov14PmfsxYBC7UZs66dLjjZwai18TgyNbOQpIgrXqkBKhEc-2BGnxv1ngMqcRN7q-2FE3txq8B8kcAaX-2FUBueYKr5s-2F6D0K87ac-2B6wVVJLvbCTXJMtybF0i-2B6pgVT79h8ftqpJqyW-2FzzcIi5Qbgf09lqvqqkvZshzRmxnRlPpyHq-2BIzwOTt1wlfW-2BPgB4P2F4xeSJQ2uH4jQmIuv24mv39BGN6XJxjORMhewqKO5-2FuFHy2L-2Bxlnm5i10reawPzoyWiOEro8WOCRLWyI9UAsEb-2FT9QHeyM-3D\" style=\"color:#3999c1 !important\" title=\"SimilarWeb\"><img alt=\"Similar Web\" class=\"1ex\" height=\"auto\" src=\"https://d1pgqke3goo8l6.cloudfront.net/swIzngZbTUFFBIjwu1zw_Group%402x.png\" style=\"height:auto; line-height:100%; border:0; outline:none; text-decoration:none\" width=\"159\" /></a>\n" +
            "        </td>\n" +
            "      </tr>\n" +
            "      <tr>\n" +
            "        <td align=\"center\" style=\"border-collapse:collapse !important; mso-table-lspace:0pt; mso-table-rspace:0pt; font-family:'Roboto', sans-serif; padding-left:0; padding-right:0; padding-top:0; padding-bottom:0\" valign=\"top\">\n" +
            "\n" +
            "          <!-- BODY -->\n" +
            "          <div style=\"border: 1px solid rgba(223,226,230,0.6); border-radius: 4px; background-image:url(https://d1pgqke3goo8l6.cloudfront.net/DNHYRhpZQ2G5MrcSDPDm_help%20wave%402x.png); background-repeat: no-repeat; background-position: bottom -3px right -3px; background-size: 36%;\">\n" +
            "            <table class=\"container\" style=\"border-collapse:collapse !important; mso-table-lspace:0pt; mso-table-rspace:0pt; font-family:'Roboto', sans-serif; padding-left:0; padding-right:0; padding-top:0; padding-bottom:0; width:100%; max-width:600px; margin:0 auto; padding:0; clear:both\" width=\"100%\">\n" +
            "              <tr>\n" +
            "                <td style=\"border-collapse:collapse !important; mso-table-lspace:0pt; mso-table-rspace:0pt; font-family:'Roboto', sans-serif; padding-left:0; padding-right:0; padding-top:0; padding-bottom:0\">\n" +
            "                  <table class=\"row\" style=\"border-collapse:collapse !important; mso-table-lspace:0pt; mso-table-rspace:0pt; font-family:'Roboto', sans-serif; padding-left:0; padding-right:0; padding-top:0; padding-bottom:0; width:100%\" width=\"100%\">\n" +
            "                    <tr>\n" +
            "                      <td class=\"content\" style=\"border-collapse:collapse !important; mso-table-lspace:0pt; mso-table-rspace:0pt; font-family:'Roboto', sans-serif; padding-top:48px; padding-right:48px; padding-bottom:48px; padding-left:48px\">\n" +
            "                        <table class=\"row\" style=\"border-collapse:collapse !important; mso-table-lspace:0pt; mso-table-rspace:0pt; font-family:'Roboto', sans-serif; padding-left:0; padding-right:0; padding-top:0; padding-bottom:0; width:100%\" width=\"100%\">\n" +
            "                          <tr>\n" +
            "                            <td style=\"border-collapse:collapse !important; mso-table-lspace:0pt; mso-table-rspace:0pt; padding-left:0; padding-right:0; padding-top:0; padding-bottom:0; font-family:'Roboto', sans-serif; font-size:24px; line-height:38px; color:#1B2653\">\n" +
            "                              Cambio de Contraseña\n" +
            "                            </td>\n" +
            "                          </tr>\n" +
            "                          <tr>\n" +
            "                            <td style=\"border-collapse:collapse !important; mso-table-lspace:0pt; mso-table-rspace:0pt; font-family:'Roboto', sans-serif; padding-left:0; padding-right:0; color:#2A3E52; padding-top:16px; padding-bottom:0px\">\n" +
            "                              Hemos recibido un pedido de cambio de contraseña de tu cuenta. Si has sido tu, ingresa el siguiente codigo para reiniciar la contraseña.\n" +
            "                            </td>\n" +
            "                          </tr>\n" +
            "                          <tr>\n" +
            "                            <td style=\"border-collapse:collapse !important; mso-table-lspace:0pt; mso-table-rspace:0pt; font-family:'Roboto', sans-serif; padding-left:0; padding-right:0; color:#2A3E52; padding-top:16px; padding-bottom:0px\">\n" +
            "                              Si no quieres ingresar una nueva contraseña o no has solicitado el reinicio, ignora este mensaje.\n" +
            "                            </td>\n" +
            "                          </tr>\n" +
            "                          <tr>\n" +
            "                            <td align=\"center\" style=\"border-collapse:collapse !important; mso-table-lspace:0pt; mso-table-rspace:0pt; font-family:'Roboto', sans-serif; padding-left:0; padding-right:0; padding-bottom:0; color:#000; font-weight:bold; font-size:24px; padding-top:24px; text-align:center\">\n" +
            "                              %s\n" +
            "                            </td>\n" +
            "                          </tr>\n" +
            "                          <tr>\n" +
            "                            <td style=\"border-collapse:collapse !important; mso-table-lspace:0pt; mso-table-rspace:0pt; color:#2A3E52; font-family:'Roboto', sans-serif; font-size:16px; line-height:22px; padding-top:0px; padding-right:0px; padding-bottom:26px; padding-left:0\">\n" +
            "                              ¡Que tengas un gran Dia!, <br />Tu clinica Mary Jose\n" +
            "                            </td>\n" +
            "                          </tr>\n" +
            "                          <tr>\n" +
            "                            <td style=\"border-collapse:collapse !important; mso-table-lspace:0pt; mso-table-rspace:0pt; padding-left:0; padding-top:0; padding-bottom:0; font-family:'Roboto', sans-serif; font-size:14px; line-height:16px; padding-right:80px\">\n" +
            "                              Need help? Contact <a href=\"mailto:support@similarweb.com\" style=\"color:#4F8DF9 !important; text-decoration:none\" target=\"_blank\">miguelooutn@gmail.com</a>\n" +
            "                            </td>\n" +
            "                          </tr>\n" +
            "                        </table>\n" +
            "                      </td>\n" +
            "                    </tr>\n" +
            "                  </table>\n" +
            "                </td>\n" +
            "              </tr>\n" +
            "            </table>\n" +
            "          </div>\n" +
            "\n" +
            "          <!-- BODY END -->\n" +
            "        </td>\n" +
            "      </tr>\n" +
            "      <tr>\n" +
            "        <td align=\"center\" style=\"border-collapse:collapse !important; mso-table-lspace:0pt; mso-table-rspace:0pt; padding-left:0; padding-right:0; padding-top:0; font-size:14px; font-family:'Roboto', sans-serif; line-height:16px; text-align:center; padding-bottom:80px\">\n" +
            "          © Hibryds LTD 2012-2021. All rights reserved. <br />\n" +
            "        </td>\n" +
            "      </tr>\n" +
            "    </table>\n" +
            "  </body>\n" +
            "</html>";

    public SendMail(String correo) {
        this.correo = correo;
    }

    public boolean enviarCorreoRecuperacion(String codigo) {
        boolean response = false;
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "outlook.office365.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(correo));
            message.setSubject("Test" + codigo);
            message.setContent(html.replace("%s",codigo), "text/html; charset=utf-8");

            Transport.send(message);

            System.out.println("Done");
            response = true;

        } catch (AddressException e) {
            throw new RuntimeException(e);
        } catch (javax.mail.MessagingException e) {
            e.printStackTrace();
        }
        return response;
    }

}
