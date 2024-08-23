package hellojpa;

import javax.persistence.Embeddable;
import java.time.LocalDateTime;
// 임베디드 타입
//회원 엔티티 이름, 근무, 종료일, 주소 도시, 주소 번지 , 우편번호
// 주소라는 것을 현재 내 시스템에서 공통으로 쓸수 있지 않을까?
// 시작일 종료일 섞어서 쓸수 있지 않을까? -> 회원 엔티티는 이름, 근무 시작일, 근무 종료일, 주소 도시, 주소 번지, 주소 우편번호를 가진다.
// 이걸 묶어낼수 있는게 임베디드 타입이다

// 임베디드 타입 사용법
// @ Embeddable : 값 타입을 정의하는 곳에 표시
// @ Embedded : 값 타입을 사용하는 곳에 표시
// 기본 생성자는 필수 !.
@Embeddable
public class Period {

    // Period
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }
}
