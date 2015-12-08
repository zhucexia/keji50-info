<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>



<iframe style="display:none;" name="iframe" src="http://localhost:8580/common/login-user.jsp" width="100%"></iframe>
<script>
window.onload = function(){
	var a = window.frames["iframe"].document.body.innerHTML;
	console.log(a);
}
</script>