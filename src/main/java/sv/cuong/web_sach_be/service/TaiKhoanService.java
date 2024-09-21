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
        text += "</br> click vào đường link để kích hoạt tài khoản:";
        String url = "http://localhost:3000/kichHoat/" + email + "/" + maKichHoat;
        text += "</br> <a href=" + url + ">" + url + "</a>";

        emailService.sendMessage("daokecuong1608@gmail.com", email, subject, text);
    }

    public ResponseEntity<?> kichHoatTaiKhoan(String email, String maKichHoat) {
        NguoiDung nguoiDung = nguoiDungRepository.findByEmail(email);
        if (nguoiDung == null) {
            return ResponseEntity.badRequest().body(new ThongBao("Người dùng không tồn tại "));
        }
        if (nguoiDung.isDaKichHoat()) {
            return ResponseEntity.badRequest().body(new ThongBao("Tài khoản đã được kích hoạt "));
        }
        if (maKichHoat.equals(nguoiDung.getMaKichHoat())) {
            nguoiDung.setDaKichHoat(true);
            nguoiDungRepository.save(nguoiDung);
            return ResponseEntity.ok("Kích hoạt tài khoản thành công");
        } else {
            return ResponseEntity.badRequest().body(new ThongBao("Mã kích hoạt không đúng"));
        }
    }
}
