package sv.cuong.web_sach_be.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "hinh_anh")
public class HinhAnh {

    @Id
    @Column(name = "ma_hinh_anh")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int maHinhAnh;
    @Column(name = "ten_hinh_anh")
    private String tenHinhAnh;
    @Column(name = "la_icon")
    private boolean laIcon;
    @Column(name = "duong_dan")
    private String duongDan;
    @Column(name = "du_lieu_anh", columnDefinition = "LONGTEXT")
    @Lob
    private String duLieuAnh;

    @ManyToOne(cascade = {
            CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH
    })
    //nullable: set hinh anh ton  tai bat buoc phai co quyen sach
    @JoinColumn(name = "ma_sach" , nullable = false)
    private Sach sach;
}
