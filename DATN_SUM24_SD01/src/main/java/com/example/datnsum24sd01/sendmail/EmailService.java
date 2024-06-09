package com.example.datnsum24sd01.sendmail;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    private final JavaMailSender javaMailSender;

    @Value("duongpvph20350@fpt.edu.vn")
    private String senderEmail;

    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendNewAccountNVEmail(String recipientEmail, String email ,String matKhau) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(senderEmail);
        message.setTo(recipientEmail);
        message.setSubject("Chào Mừng: Thư xác nhận nhân viên của BeeStore");
        message.setText("Chào "+ recipientEmail + " ,\n\n" +
                "Bạn vừa dùng mail này để đăng kí tài khoản BeeStore,\n\n" +
                "Tài khoản mới với tên đăng nhập : "+ email+" ,\n\n" +
                "Mật Khẩu đăng nhập : " + matKhau + " ,\n\n" +
                "Một lần nữa chúc mừng bạn là thành viên của BeeStore : http://localhost:8080/beestore/trang-chu  ,\n\n" +
                " * Quý khách vui lòng không trả lời email này * ,\n\n" +
                "Trân trọng,\n[BEESTORE]");

        javaMailSender.send(message);
    }
    public void sendNewAccountKHEmail(String recipientEmail, String email ,String matkhau) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(senderEmail);
        message.setTo(recipientEmail);
        message.setSubject("Thông báo: Tài khoản mới đã được tạo");
        message.setText("Chào '"+ recipientEmail +"' ,\n\n" +
                "Bạn vừa dùng mail này để đăng kí tài khoản BeeStore,\n\n" +
                "Tài khoản mới với tên đăng nhập : "+ email +" ,\n\n" +
                "Mật Khẩu đăng nhập : " +matkhau  + " ,\n\n" +
                "Cùng đăng nhập để trải nhiệm nhiều tiện ích tuyệt vời nhé :  http://localhost:8080/beestore/trang-chu  ,\n\n" +
                " * Quý khách vui lòng không trả lời email này * ,\n\n" +
                "Trân trọng,\n[BEESTORE]");

        javaMailSender.send(message);
    }


}
