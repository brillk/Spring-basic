package hello.core.member;

public class MemberServiceImpl implements MemberService{


    private final MemberRepository memberRepository;
    // 추상화에만 의존한다

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
    //구현체에 뭐가 들어갈지는 생성자를 통해서 전달한다


    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findbyId((memberId));
    }

    //테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
