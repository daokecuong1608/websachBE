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
@Table(name = "sach")
public class Sach {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ma_sach", length = 256)
    private int maSach;
    @Column(name = "ten_sach", length = 256)
    private String tenSach;
    @Column(name = "ten_tac_gia", length = 256)
    private String tenTacGia;
    @Column(name = "isbn", length = 256)  // ISBN (International Standard Book Number) của sách.
    private String ISBN;
    @Column(name = "mo_ta", columnDefinition = "")
    private String moTa;
    @Column(name = "gia_niem_yet")
    private double giaNiemYet;
    @Column(name = "gia_ban")
    private double giaBan;
    @Column(name = "so_luong")  // Số lượng sách trong kho.
    private int soLuong;
    @Column(name = "trung_binh_xep_hang")
    // Trung bình điểm đánh giá của sách. 0-5.0. 0.5 là điểm trung bình của sách đầu tiên. 5.0 là điểm cao nhất của sách đầu tiên. 5.0 là điểm cao nhất của sách cu
    private double trungBinhXepHang;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {
            CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH

    })
    @JoinTable(
            name = "sach_theloai"
            , joinColumns = @JoinColumn(name = "ma_sach")
            , inverseJoinColumns = @JoinColumn(name = "ma_the_loai")
    )
    private List<TheLoai> danhSachTheLoai;

    //mappedBy : chỉ định tên của trường hoặc thuộc tính trên đối tượng ở phía một (one side) mà quản lý mối quan hệ
//tao ra entity sách sẽ tồn tại
    @OneToMany(mappedBy = "sach", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<HinhAnh> danhSachHinhAnh;

    @OneToMany(mappedBy = "sach", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<SuDanhGia> danhSachSuDanhGia;

    @OneToMany(mappedBy = "sach", fetch = FetchType.LAZY, cascade = {
            CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH}
    )
    private List<ChiTietDonHang> danhSachChiTietDonHang;

    @OneToMany(mappedBy = "sach", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<SachYeuThich> danhSachSachYeuThich;

}