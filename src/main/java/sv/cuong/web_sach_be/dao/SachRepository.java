package sv.cuong.web_sach_be.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.RequestParam;
import sv.cuong.web_sach_be.entity.Sach;

@RepositoryRestResource(path = "sach")
public interface SachRepository extends JpaRepository<Sach, Integer> {

    //RequestParam => lấy dữ liệu từ các tham số truy vấn (query prarameters)
    Page<Sach> findByTenSachContaining(@RequestParam("tenSach")String tenSach , Pageable pageable);

    Page<Sach> findByDanhSachTheLoai_MaTheLoai(@RequestParam("maTheLoai")int maTheLoai , Pageable pageable);

    Page<Sach> findByTenSachContainingAndDanhSachTheLoai_MaTheLoai(@RequestParam("tenSach")String tenSach ,@RequestParam("maTheLoai")int maTheLoai , Pageable pageable);

    public  boolean existsByTenSach(String tenSach);

    public boolean existsByISBN(String ISBN);
}
