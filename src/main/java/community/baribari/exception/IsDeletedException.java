package community.baribari.exception;

public class IsDeletedException extends RuntimeException{
    public IsDeletedException() {
        super("삭제된 게시물입니다.");
    }
}
