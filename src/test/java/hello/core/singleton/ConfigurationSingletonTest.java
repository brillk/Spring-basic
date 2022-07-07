package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberRepository;
import hello.core.member.MemberServiceImpl;
import hello.core.order.OrderServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

public class ConfigurationSingletonTest {

    @Test
    void configurationTest() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
        OrderServiceImpl orderService = ac.getBean("orderService", OrderServiceImpl.class);
        MemberRepository memberRepository = ac.getBean("memberRepository", MemberRepository.class);

        MemberRepository memberRepository1 = memberService.getMemberRepository();
        MemberRepository memberRepository2 = orderService.getMemberRepository();


        System.out.println(" memberService -> memberRepository = " + memberRepository2);
        System.out.println(" orderService -> memberRepository = " + memberRepository1);
        System.out.println(" memberRepository = " + memberRepository);

        assertThat(memberService.getMemberRepository()).isSameAs(memberRepository);
        assertThat(orderService.getMemberRepository()).isSameAs(memberRepository);
    }


    @Test
    void configurationDeep() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        AppConfig bean = ac.getBean(AppConfig.class);
        System.out.println("bean = " + bean.getClass());
        // bean = class hello.core.AppConfig$$EnhancerBySpringCGLIB$$6f837f94
        // 순수한 자바 코드로는 class hello.core.AppConfig 까지만 나오지만
        // @COnfiguration 안에 AppConfig을 복사하고 다른 코드를 추가한 저장소가 있다
        // 그곳이 memberService를 처음 생성되고, 저장하고 다시 호출할때, 이곳에서 꺼내서 다시 쓴다
        // 그래서 스프링 싱글톤을 가능케 한다
    }
}
