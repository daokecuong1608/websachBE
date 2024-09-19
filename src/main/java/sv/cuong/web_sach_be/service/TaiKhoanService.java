package sv.cuong.web_sach_be.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import sv.cuong.web_sach_be.dao.NguoiDungRepository;
import sv.cuong.web_sach_be.entity.NguoiDung;
import sv.cuong.web_sach_be.entity.ThongBao;

@Service
public class TaiKhoanService {

    @Autowired
private NguoiDungRepository nguoiDungRepository;



    public ResponseEntity<?>dangKyNguoiDung(NguoiDung nguoiDung){
        //kiểm tra tên đăng nhập đã tồn tại chưa
        if(nguoiDungRepository.existsByTenDangNhap(nguoiDung.getTenDangNhap())){
            return  ResponseEntity.badRequest().body(new ThongBao( "Tên đăng nhập đã tồn tại!"));
        }
        //kiểm tra email đã tồn tại chưa
        if(nguoiDungRepository.existsByEmail(nguoiDung.getEmail())){
            return  ResponseEntity.badRequest().body(new ThongBao("Email đã tồn tại!"));
        }

        //thêm người dùng vào csdl
        NguoiDung nguoiDung_dangKy = nguoiDungRepository.save(nguoiDung);
        return ResponseEntity.ok(new ThongBao("Đăng ký thành công!"));
    }
}
