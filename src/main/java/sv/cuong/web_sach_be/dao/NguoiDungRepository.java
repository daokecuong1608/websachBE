package sv.cuong.web_sach_be.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import sv.cuong.web_sach_be.entity.NguoiDung;

@RepositoryRestResource(path = "nguoi-dung")
public interface NguoiDungRepository extends JpaRepository<NguoiDung, Integer> {

    //ktra tên đăng nhập đã tồn tại hay chưa
    public boolean existsByTenDangNhap(String tenDangNhap);

    public boolean existsByEmail(String email);

    public NguoiDung findByTenDangNhap(String tenDangNhap);

    public NguoiDung findByEmail(String email);
}
