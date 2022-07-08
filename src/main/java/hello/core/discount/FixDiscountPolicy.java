package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.springframework.stereotype.Component;

@Component //일부러 조회하는 빈이 2개 이상일떄의 오류를 나타냈다
public class FixDiscountPolicy implements DiscountPolicy{

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
