package sv.cuong.web_sach_be.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "hinh_thuc_thanh_toan")
public class HinhThucThanhToan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ma_hinh_thuc_thanh_toan")
    private int maHinhThucThanhToan;
    @Column(name = "ten_hinh_thuc_thanh_toan")
    private String tenHinhThucThanhToan;
    @Column(name = "mo_ta")
    private String moTa;
    @Column(name = "chi_phi_thanh_toan")
    private double chiPhiThanhToan;


    @OneToMany(mappedBy = "hinhThucThanhToan", cascade = {
            CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH

    })
    private List<DonHang> danhSachDonHang;
}
