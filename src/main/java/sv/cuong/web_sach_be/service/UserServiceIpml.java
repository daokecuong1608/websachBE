package sv.cuong.web_sach_be.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import sv.cuong.web_sach_be.dao.NguoiDungRepository;
import sv.cuong.web_sach_be.dao.QuyenRepository;
import sv.cuong.web_sach_be.entity.NguoiDung;
import sv.cuong.web_sach_be.entity.Quyen;
import java.util.Collection;
import java.util.stream.Collectors;


@Service
public class UserServiceIpml implements UserService{


    private NguoiDungRepository nguoiDungRepository;


    private QuyenRepository quyenRepository;

    @Autowired
    public UserServiceIpml(QuyenRepository quyenRepository, NguoiDungRepository nguoiDungRepository) {
        this.quyenRepository = quyenRepository;
        this.nguoiDungRepository = nguoiDungRepository;
    }

    @Override
    public NguoiDung findByUsername(String tenDangNhap) {
        return nguoiDungRepository.findByTenDangNhap(tenDangNhap);
    }

    @Override//cho biết đâu là user , mk , role
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       NguoiDung nguoiDung = nguoiDungRepository.findByTenDangNhap(username);
       if(nguoiDung == null) {
         throw  new UsernameNotFoundException("Tài khoản không tồn tại");
       }
        return new User(nguoiDung.getTenDangNhap() , nguoiDung.getMatKhau(),rolesToAuthorities(nguoiDung.getDanhSachQuyen()));
    }

    private Collection<? extends GrantedAuthority> rolesToAuthorities(Collection<Quyen> quyens){
return  quyens.stream().map(role -> new SimpleGrantedAuthority(role.getTenQuyen())).collect(Collectors.toList());
    }
}
