package org.myungkeun.coworking240502.exception.notFound;

public class NotFoundMemberException extends NotFoundException{
    public NotFoundMemberException() {
        super("존재하지않은 회원입니다.", 1001);
    }
}
