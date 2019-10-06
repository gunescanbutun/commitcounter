<%@ page isELIgnored="false" language="java" contentType="text/html; charset=UTF-8" 
	pageEncoding="UTF-8"%> 
<!DOCTYPE html>
<html lang="en">

<head>
<title>CommitCounter</title>
</head>

<body>
<div>
  	Toplam Commit Sayısı: ${model.totalCommitCount}
</div>
<div>
	En çok commit yapan kullanıcı: ${model.loginName }
</div>
<div>
	En çok commit yapanın commit sayısı: ${model.commitCount }
</div>
</body>

</html>
