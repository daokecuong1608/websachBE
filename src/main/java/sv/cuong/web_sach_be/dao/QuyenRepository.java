package sv.cuong.web_sach_be.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import sv.cuong.web_sach_be.entity.Quyen;

@RepositoryRestResource(path = "quyen")
public interface QuyenRepository extends JpaRepository<Quyen, Integer> {
public Quyen findByTenQuyen(String tenQuyen);
}