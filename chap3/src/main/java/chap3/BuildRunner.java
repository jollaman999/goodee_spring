package chap3;

import java.util.List;

class BuildRunner {
    private String path;

    public void setPath(String path) {
        this.path = path;
    }

    void build(List<String> srcdirs, String bindir) {
        StringBuilder info = new StringBuilder("프로젝트 경로 : " + path + "\n");

        for (String dir : srcdirs) {
            info.append("소스파일 경로 : ").append(dir).append("\n");
        }

        info.append("클래스 파일 경로 : ").append(bindir).append("\n");
        System.out.println(info);
    }
}
