package kr.pe.playdata.controller;

import java.util.List;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import kr.pe.playdata.config.security.JwtLoginToken.JwtTokenProvider;
import kr.pe.playdata.model.domain.Member;
import kr.pe.playdata.repository.MemberRepository;
import lombok.RequiredArgsConstructor;

@Api(tags = { "1. Sign" })
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/v1")
public class SignController {

    private final MemberRepository userJpaRepo;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

//    @ApiOperation(value = "로그인", notes = "이메일 회원 로그인을 한다.")
//    @PostMapping(value = "/signin")
//    public SingleResult<String> signin() {
//        Member user = userJpaRepo.findByMemberId(id).orElseThrow(RuntimeException::new);
//        if (!passwordEncoder.matches(password, user.getPassword()))
//            throw new RuntimeException();
//
//        return responseService.getSingleResult(jwtTokenProvider.createToken(String.valueOf(user.getMsrl()), user.getRoles()));
//
// }
    
    @PostMapping("/members/login")
    public String login(@ApiParam(value = "회원ID : 이메일", required = true) @RequestParam String id,
            			@ApiParam(value = "비밀번호", required = true) @RequestParam String password) {
        Member member = userJpaRepo.findByMemberId(id)
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 E-MAIL 입니다."));
        if (!passwordEncoder.matches(password, member.getPassword())) {
            throw new IllegalArgumentException("잘못된 비밀번호입니다.");
        }
		List<String> role = member.getRole();
        return jwtTokenProvider.createToken(String.valueOf(member.getMemberIdx()), member.getRole());
    }

// @ApiOperation(value = "가입", notes = "회원가입을 한다.")
// @PostMapping(value = "/signup")
// public CommonResult signin(@ApiParam(value = "회원ID : 이메일", required = true) @RequestParam String id,
//                               @ApiParam(value = "비밀번호", required = true) @RequestParam String password,
//                               @ApiParam(value = "이름", required = true) @RequestParam String name) {
//
//        userJpaRepo.save(User.builder()
//                .uid(id)
//                .password(passwordEncoder.encode(password))
//                .name(name)
//                
//                .build());
//        
//    }

}