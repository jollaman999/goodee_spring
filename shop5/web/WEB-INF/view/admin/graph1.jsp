<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<c:set value="${pageContext.request.contextPath}" var="path" />
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>BAR graph</title>

    <script src="${path}/js/jquery-3.4.1.min.js"></script>
    <script type="text/javascript" src="${path}/js/Chart.bundle.min.js"></script>
</head>
<body>
<div id="canvas-holder" style="width: 100%; height: 100%;">
    <canvas id="chart-area" width="100%" height="100%"></canvas>
</div>
<script type="text/javascript">
    var randomColorFactor = function(){
        return Math.round(Math.random() * 255);
    }
    var randomColor = function(opacity) {
        return "rgba(" + randomColorFactor() + ","
            + randomColorFactor() + ","
            + randomColorFactor() + ","
            + (opacity || '.3') + ")";
    }
    var color = randomColor(1);
    var config = {
        type : 'bar',
        data : {
            datasets : [{
                label : "",
                data:[<c:forEach items="${map}" var="m">"${m.value}",</c:forEach>],
                backgroundColor :
                    [<c:forEach items="${map}" var="m">randomColor(1),</c:forEach>],
            }],
            labels:[<c:forEach items="${map}" var="m">"${m.key}",</c:forEach>]
        },
        options : {
            responsive : true,
            legend : {
                position : 'bottom',
            },
            title : {
                display : true,
                text : '게시판 작성자 분석'
            }, scales : {
                yAxes : [{
                    ticks : {
                        beginAtZero : true
                    }
                }]
            }
        }
    };
    window.onload = function() {
        var ctx = document.getElementById("chart-area").getContext("2d");
        window.myPid = new Chart(ctx,config);
    }
</script>
</body>
</html>
