package sv.cuong.web_sach_be.service;


import org.springframework.security.core.userdetails.UserDetailsService;
import sv.cuong.web_sach_be.entity.NguoiDung;

public interface UserService extends UserDetailsService {
    public NguoiDung findByUsername(String tenDangNhap);


}
