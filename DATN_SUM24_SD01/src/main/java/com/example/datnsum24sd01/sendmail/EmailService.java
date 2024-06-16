// package com.example.datnsum24sd01.sendmail;

// <<<<<<< duongpvph20305
// import jakarta.mail.internet.MimeMessage;
// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.mail.SimpleMailMessage;
// import org.springframework.mail.javamail.JavaMailSender;
// import org.springframework.mail.javamail.MimeMessageHelper;
// import org.springframework.stereotype.Service;

// @Service
// =======
// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.mail.SimpleMailMessage;
// import org.springframework.mail.javamail.JavaMailSender;

// >>>>>>> main
// public class EmailService {
//     private final JavaMailSender javaMailSender;

//     @Value("duongpvph20350@fpt.edu.vn")
//     private String senderEmail;

//     public EmailService(JavaMailSender javaMailSender) {
//         this.javaMailSender = javaMailSender;
//     }

// <<<<<<< duongpvph20305
//     public void sendNewAccountNVEmail(String recipientEmail, String email ,String matKhau) {
// =======
//     public void sendNewAccountNVEmail(String recipientEmail, String email ,String matkhau) {
// >>>>>>> main
//         SimpleMailMessage message = new SimpleMailMessage();
//         message.setFrom(senderEmail);
//         message.setTo(recipientEmail);
//         message.setSubject("Chào Mừng: Thư xác nhận nhân viên của BeeStore");
//         message.setText("Chào "+ recipientEmail + " ,\n\n" +
//                 "Bạn vừa dùng mail này để đăng kí tài khoản BeeStore,\n\n" +
//                 "Tài khoản mới với tên đăng nhập : "+ email+" ,\n\n" +
// <<<<<<< duongpvph20305
//                 "Mật Khẩu đăng nhập : " + matKhau + " ,\n\n" +
// =======
//                 "Mật Khẩu đăng nhập : " + matkhau + " ,\n\n" +
// >>>>>>> main
//                 "Một lần nữa chúc mừng bạn là thành viên của BeeStore : http://localhost:8080/beestore/trang-chu  ,\n\n" +
//                 " * Quý khách vui lòng không trả lời email này * ,\n\n" +
//                 "Trân trọng,\n[BEESTORE]");

//         javaMailSender.send(message);
//     }
//     public void sendNewAccountKHEmail(String recipientEmail, String email ,String matkhau) {
//         SimpleMailMessage message = new SimpleMailMessage();
//         message.setFrom(senderEmail);
//         message.setTo(recipientEmail);
//         message.setSubject("Thông báo: Tài khoản mới đã được tạo");
//         message.setText("Chào '"+ recipientEmail +"' ,\n\n" +
//                 "Bạn vừa dùng mail này để đăng kí tài khoản BeeStore,\n\n" +
//                 "Tài khoản mới với tên đăng nhập : "+ email +" ,\n\n" +
//                 "Mật Khẩu đăng nhập : " +matkhau  + " ,\n\n" +
//                 "Cùng đăng nhập để trải nhiệm nhiều tiện ích tuyệt vời nhé :  http://localhost:8080/beestore/trang-chu  ,\n\n" +
//                 " * Quý khách vui lòng không trả lời email này * ,\n\n" +
//                 "Trân trọng,\n[BEESTORE]");

//         javaMailSender.send(message);
//     }

// <<<<<<< duongpvph20305
//     public void sendMaPhieuGiamGiaKH(String recipientEmail,String content) {
//         SimpleMailMessage message = new SimpleMailMessage();
//         message.setText(content);
//         message.setFrom(senderEmail);
//         message.setTo(recipientEmail);
//         message.setSubject("Thông báo: BeeStore gửi đến bạn 1 ưu Đãi");
//         message.setText("Chào '"+ recipientEmail +"' ,\n\n" +
//                 "Bạn là một khách hàng thân thiết của BeeStore . Để tri ân và thay lời cảm ơn đến bạn BeeStore xin gửi bạn ưu đãi khi mua hàng tại của hàng hay trên hệ thống của BeeStore,\n\n" +
//                 "Bạn hãy nhanh tay dùng mã giảm giá này để nhận nhiều ưu đãi của chúng tôi nhé  : " +content + " ,\n\n" +
//                 "Một lần nữa cảm ơn và hẹn gặp lại bạn !  ,\n\n" +
//                 " * Quý khách vui lòng không trả lời email này * ,\n\n" +
//                 "Trân trọng,\n[BEESTORE]");

//         javaMailSender.send(message);
//     }
// }

// =======

// }
// >>>>>>> main
