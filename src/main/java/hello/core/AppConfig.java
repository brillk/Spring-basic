package hello.core;


import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//구현 객체를 생성하고, 연결하는 책임을 가졌다
@Configuration //설정정보
public class AppConfig {

    // ctrl + alt + m
    @Bean
    public MemberService memberService() {

        return new MemberServiceImpl(memberRepository());
    }
    @Bean
    public MemberRepository memberRepository() {

        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService() {

        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    public DiscountPolicy discountPolicy() {
        return new RateDiscountPolicy();
    }

}
