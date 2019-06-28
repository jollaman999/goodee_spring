package chap3;

public class WriteImpl {
    private ArticleDao dao;
    public WriteImpl(ArticleDao dao) {
        this.dao = dao;
    }

    void write() {
        System.out.println("WriteImpl.write 메서드 호출");
        dao.insert();
    }
}
