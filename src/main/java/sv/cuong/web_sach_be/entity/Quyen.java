package sv.cuong.web_sach_be.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Entity
@Table(name = "quyen")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Quyen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ma_quyen")
    private int maQuyen;
    @Column(name = "ten_quyen")
    private String tenQuyen;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {
            CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH

    })
    @JoinTable(
            name = "nguoi_dung_quyen"
            , joinColumns = @JoinColumn(name = "ma_quyen")
            , inverseJoinColumns = @JoinColumn(name = "ma_nguoi_dung")
    )
    List<NguoiDung> danhSachNguoiDung;
}
