package sv.cuong.web_sach_be.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import sv.cuong.web_sach_be.entity.ChiTietDonHang;

@RepositoryRestResource(path = "chi-tiet-don-hang")//đánh dấu tập hơp tiêu chuẩn để thực hiện các thao tác cơ bản trên csdl
public interface ChiTietDonHangRepository extends JpaRepository<ChiTietDonHang, Long> {
}