package sv.cuong.web_sach_be.security;

public class Endpoints {

    public static final String front_end_host = "http://localhost:3000";


    public static String[] PUBLIC_GET_ENDPOINT = {
            "/sach",
            "/sach/**",
            "/hinh-anh",
            "/hinh-anh/**",
            "/nguoi-dung/search/existByTenDangNhap",
            "/nguoi-dung/search/existByEmail",
    };
    public static String[] PUBLIC_POST_ENDPOINT = {
            "/tai-khoan/dang-ky"
    };
    public static String[] ADMIN_GET_ENDPOINT = {
            "/nguoi-dung",
            "/nguoi-dung/**",
    };
}