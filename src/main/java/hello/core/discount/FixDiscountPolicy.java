package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component //일부러 조회하는 빈이 2개 이상일떄의 오류를 나타냈다

public class FixDiscountPolicy implements DiscountPolicy{

    /* 해결 방법
    * 1. @Autowired 필드명 매칭 => 타입 매칭을 시도, 여러 빈이 있다면 필드이름, 파라미터 이름으로 빈 이름을 추가 매칭한다.
    * 2. @Qualifier => 추가 구분자를 붙여주는 방법, 주입 시 추가적인 방법을 제공하고 빈 이름을 변경하는 건 아니다 => @Qualifier끼리 매칭, 빈 이름 매칭, 에러 발생
    * 3. @Primary => 우선 순위를 정한다, @Autowired 시 여러 빈이 매칭되면 @Primary가 우선권을 가진다.
    * 번외: @Qualifier > @Primary 우선순위는 더 명확하게 제시한 @Qualifier가 더 높다
    * */

    private int discountFixAmount = 1000; // 할인 금액

    @Override
    public int discount(Member member, int price) {
        if(member.getGrade() == Grade.VIP) {
            return discountFixAmount;
        } else {
            return 0;
        }
    }
}
