package sv.cuong.web_sach_be.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import sv.cuong.web_sach_be.entity.TheLoai;

@RepositoryRestResource(path ="the-loai")
public interface TheLoaiRepository extends JpaRepository<TheLoai, Integer> {
    public boolean existsByTenTheLoai(String tenTheLoai);
}