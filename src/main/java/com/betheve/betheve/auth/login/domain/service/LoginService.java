package com.betheve.betheve.auth.login.domain.service;

//import com.betheve.betheve.member.domain.entity.Member;
//import com.betheve.betheve.member.domain.repository.MemberRepository;
import lombok.AllArgsConstructor;
        import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LoginService {

//    @Autowired
//    private final MemberRepository memberRepository;
//
//    @Autowired
//    private final PasswordEncoder passwordEncoder;
//
//    public LoginSuccessInfo login(LoginDto loginDto) {
//
//         Member member = memberRepository.getMemberByAccountId(loginDto.getAccountId()).get();
//
//        if (checkIncorrectLoginInfo(member, loginDto)) {
//            throw new IllegalArgumentException("로그인 정보가 잘못되었습니다.");
//        }
//
//        return new LoginSuccessInfo(member.getAccountId(), member.getMemberName(), member.getNickName());
//
//    }
//
//    private boolean checkIncorrectLoginInfo(Member sourceMember, LoginDto loginDto) {
//        return sourceMember == null || !sourceMember.checkPassword(loginDto.getPassword(), passwordEncoder);
//    }

}
