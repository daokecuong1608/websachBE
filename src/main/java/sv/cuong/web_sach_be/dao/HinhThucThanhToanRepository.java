package sv.cuong.web_sach_be.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import sv.cuong.web_sach_be.entity.HinhThucThanhToan;

@RepositoryRestResource(path = "hinh-thuc-thanh-toan")
public interface HinhThucThanhToanRepository extends JpaRepository<HinhThucThanhToan, Integer> {
    public  boolean existsByTenHinhThucThanhToan(String tenHinhThucThanhToan);
}