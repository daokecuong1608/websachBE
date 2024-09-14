package sv.cuong.web_sach_be.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import sv.cuong.web_sach_be.entity.HinhThucGiaoHang;

@RepositoryRestResource(path = "hinh-thuc-giao-hang")
public interface HinhThucGiaoHangRepository extends JpaRepository<HinhThucGiaoHang, Integer> {
    public boolean existsByTenHinhThucGiaoHang(String tenHinhThucGiaoHang);
}