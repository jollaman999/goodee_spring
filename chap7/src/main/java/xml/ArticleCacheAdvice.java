package xml;

import org.aspectj.lang.ProceedingJoinPoint;

import java.util.HashMap;
import java.util.Map;

public class ArticleCacheAdvice {

    private Map<Integer, Article> cache = new HashMap<>();

    public Object cache(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("[ACA] cache before 실행");
        // joinPoint.getArgs() : 핵심 메서드의 매개변수 목록 리턴
        // id : 1
        Integer id = (Integer)joinPoint.getArgs()[0];
        Article article = cache.get(id);

        if (article != null) {
            System.out.println("[ACA] cache 에서 Aritcle[" + id + "] 가져옴");
            return article;
        }

        Object ret = joinPoint.proceed(); // loggingAdvice.before() 호출
        System.out.println("[ACA] cache after 실행");
        if (ret instanceof Article) {
            cache.put(id, (Article)ret);
            System.out.println("[ACA] cache 에서 Article [" + id + "] 추가함");
        }

        return  ret;
    }
}
