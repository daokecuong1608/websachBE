package sv.cuong.web_sach_be.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import sv.cuong.web_sach_be.dao.NguoiDungRepository;
import sv.cuong.web_sach_be.entity.NguoiDung;
import sv.cuong.web_sach_be.entity.ThongBao;

import java.util.UUID;

@Service
public class TaiKhoanService {

    @Autowired
    private NguoiDungRepository nguoiDungRepository;

    @Autowired
    public BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private EmailService emailService;

    public ResponseEntity<?> dangKyNguoiDung(NguoiDung nguoiDung) {
        //kiểm tra tên đăng nhập đã tồn tại chưa
        if (nguoiDungRepository.existsByTenDangNhap(nguoiDung.getTenDangNhap())) {
            return ResponseEntity.badRequest().body(new ThongBao("Tên đăng nhập đã tồn tại!"));
        }
        //kiểm tra email đã tồn tại chưa
        if (nguoiDungRepository.existsByEmail(nguoiDung.getEmail())) {
            return ResponseEntity.badRequest().body(new ThongBao("Email đã tồn tại!"));
        }

        //ma hoa mat khau
        String encrypassword = passwordEncoder.encode(nguoiDung.getMatKhau());
        nguoiDung.setMatKhau(encrypassword);

        //gan va gui thong tin kich hoat
        nguoiDung.setMaKichHoat(taoMaKichHoat());
        nguoiDung.setDaKichHoat(false);

        //thêm người dùng vào csdl
        NguoiDung nguoiDung_dangKy = nguoiDungRepository.save(nguoiDung);

        //gui email kich hoat
        guiEmailKichHoat(nguoiDung.getEmail(), nguoiDung.getMaKichHoat());


        return ResponseEntity.ok(new ThongBao("Đăng ký thành công!"));
    }

    //tao ma ngau nhien
    private String taoMaKichHoat() {
        return UUID.randomUUID().toString();
    }

    private void guiEmailKichHoat(String email, String maKichHoat) {
        String subject = "Kích hoạt tài khoản của bạn tại trang web của chúng tôi";
        String text = "Vui lòng sử dụng mã sau để kích hoạt tài khoản <" + email + ">:<html><body><br/><h1>" + maKichHoat + "</h1></body></html></html>";
        emailService.sendMessage("daokecuong1608@gmail.com", email, subject, text);
    }
}
