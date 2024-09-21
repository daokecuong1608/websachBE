package sv.cuong.web_sach_be.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import sv.cuong.web_sach_be.entity.NguoiDung;
import sv.cuong.web_sach_be.security.JwtResponse;
import sv.cuong.web_sach_be.security.LoginRequest;
import sv.cuong.web_sach_be.service.JwtService;
import sv.cuong.web_sach_be.service.TaiKhoanService;
import sv.cuong.web_sach_be.service.UserService;

@RestController
@RequestMapping("/tai-khoan")
@CrossOrigin(origins = "*")
public class TaiKhoanController {

    @Autowired
    private TaiKhoanService taiKhoanService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/dang-ky")
    public ResponseEntity<?> dangKyNguoiDung(@Validated @RequestBody NguoiDung nguoiDung) {
        ResponseEntity<?> response = taiKhoanService.dangKyNguoiDung(nguoiDung);
        return response;
    }

    @GetMapping("/kich-hoat")
    public ResponseEntity<?> kichHoatTaiKhoan(@RequestParam String email, @RequestParam String maKichHoat) {
        ResponseEntity<?> response = taiKhoanService.kichHoatTaiKhoan(email, maKichHoat);
        return response;
    }


    @PostMapping("/dang-nhap")
    public ResponseEntity<?> dangNhap(@Validated @RequestBody LoginRequest loginRequest) {
        //xac thuc nguoi dung bang ten dang nhap va mat khau
        System.out.println("dang nhap va mat khau" + loginRequest.getUsername() + "=" + loginRequest.getPassword());
        try {
            //System.out.println("hihihihihii");
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

            //neu xac thuc thanh cong , tao token jwt
            if (authentication.isAuthenticated()) {
                final String jwt = jwtService.generateToken(loginRequest.getUsername());
                return ResponseEntity.ok(new JwtResponse(jwt));
            }
        } catch (AuthenticationException e) {
            //xac thuc ko thanh cong tra ve loi
            return ResponseEntity.badRequest().body("Ten dang nhap hoac mat khau khong chinh xac");
        }
        return ResponseEntity.badRequest().body("Xác thực không thành công .");

    }
}
