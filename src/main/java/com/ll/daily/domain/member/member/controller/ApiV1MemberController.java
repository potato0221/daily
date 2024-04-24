package com.ll.daily.domain.member.member.controller;


import com.ll.daily.domain.member.member.dto.MemberDto;
import com.ll.daily.domain.member.member.service.MemberService;
import com.ll.daily.global.msg.Msg;
import com.ll.daily.global.rq.Rq;
import com.ll.daily.global.rsData.RsData;
import com.ll.daily.standard.base.Empty;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/members")
@RequiredArgsConstructor
@Tag(name = "ApiV1MemberController", description = "멤버 컨트롤러")
public class ApiV1MemberController {

    private final MemberService memberService;
    private final Rq rq;

    public record LoginRequestBody(@NotBlank String username, @NotBlank String password) {
    }

    public record LoginResponseBody(@NonNull MemberDto item) {
    }

    public record isLoginResponseBody(@NonNull Boolean isLogin){
    }

    public record MeResponseBody(@NonNull MemberDto item) {
    }

    @PostMapping(value = "/login")
    @Operation(summary = "로그인, accessToken, refreshToken 쿠키 생성됨")
    public RsData<LoginResponseBody> login(@Valid @RequestBody LoginRequestBody body) {
        RsData<MemberService.AuthAndMakeTokensResponseBody> authAndMakeTokensRs = memberService.authAndMakeTokens(
                body.username,
                body.password
        );

        rq.setCrossDomainCookie("refreshToken", authAndMakeTokensRs.getData().refreshToken());
        rq.setCrossDomainCookie("accessToken", authAndMakeTokensRs.getData().accessToken());

        return authAndMakeTokensRs.newDataOf(
                new LoginResponseBody(
                        new MemberDto(authAndMakeTokensRs.getData().member())
                )
        );
    }

    @GetMapping(value = "/isLogin")
    @Operation(summary = "로그인 여부")
    public RsData<isLoginResponseBody> isLogin(){

        return RsData.of(
                Msg.E200_1_INQUIRY_SUCCEED.getCode(),
                Msg.E200_1_INQUIRY_SUCCEED.getMsg(),
                new isLoginResponseBody(rq.isLogin())
        );
    }

    @GetMapping("/me")
    @Operation(summary = "회원 정보 조회")
    public RsData<MeResponseBody> getMe() {
        return RsData.of(
                Msg.E200_1_INQUIRY_SUCCEED.getCode(),
                Msg.E200_1_INQUIRY_SUCCEED.getMsg(),
                new MeResponseBody(
                        new MemberDto(rq.getMember())
                )
        );
    }

    @PostMapping("/logout")
    @Operation(summary = "로그아웃")
    public RsData<Empty> logout() {
        rq.setLogout();

        return RsData.of(
                Msg.E200_4_LOGOUT_SUCCEED.getCode(),
                Msg.E200_4_LOGOUT_SUCCEED.getMsg()
        );
    }
}
