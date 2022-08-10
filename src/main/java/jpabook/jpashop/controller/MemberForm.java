package jpabook.jpashop.controller;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter @Setter
public class MemberForm {

    // 컨트롤러에서 넘어오는 validation이랑 엔티티에서 원하는 validation은 다를 수 있으므로 서로 안맞을 수 있따.
    // 만약 단순하면 상관없을 순 있지만, 실무에서는 차이가 너무 큰 경우가 많으므로 컨트롤러에서 정재해야할 클래스를 따로 만드는 걸 권장한다.

    @NotEmpty(message = "회원 이름은 필수 입니다.") // validation을 통해서 값이 비어있으면 해당 메세지로 오류 표현?
    private String name;

    private String city;
    private String street;
    private String zipcode;
}
