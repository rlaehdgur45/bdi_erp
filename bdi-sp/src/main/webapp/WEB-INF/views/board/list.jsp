<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
	<meta charset="utf-8">
	<title>스프링테스트</title>
</head>
<script>
window.addEventListener('load',function(){
	var xhr = new XMLHttpRequest();
	xhr.open('GET','/board');
	xhr.onreadystatechange = function(){
		if(xhr.readyState==4){
			if(xhr.status==200){
				var res= JSON.parse(xhr.responseText);
				var html='';
				for(var b of res){
					html += '<tr>';
					html += '<td>' + b.binum + '</td>';
					html += '<td><input type="text" name="bititle' + b.binum + '" value="' + b.bititle + '"></td>';
					html += '<td><input type="text" name="bitext' + b.binum + '" value="' + b.bitext + '"></td>';
					html += '<td><input type="text" name="bifile' + b.binum + '" value="' + b.bifile + '"></td>';
					html += '<td><input type="text" name="bicredat' + b.binum + '" value="' + b.bicredat + '"></td>';
					html += '<td><input type="text" name="bimoddat' + b.binum + '" value="' + b.bimoddat + '"></td>';
					html += '<td><input type="text" name="bicnt' + b.binum + '" value="' + b.bicnt + '"></td>';
					html += '<td><input type="text" name="biactive' + b.binum + '" value="' + b.biactive + '"></td>';
					html += '<td><input type="text" name="uinum' + b.binum + '" value="' + b.uinum + '"></td>';
					html += '<td><button onclick="boardUpdate(' + b.binum +')">수정<button>';
					html += '<button onclick="boardDelete(' + b.binum +')">삭제</button></td>';
					html += '</tr>';
				}
				document.querySelector('#bdBody').insertAdjaccentHTML('beforeend',html);
			}else{
				
			}
		}
	}
	xhr.send();
})
</script>
<body>
<table border="1">
	<thead>
		<tr>
			<th>번호</th>
			<th>제목</th>
			<th>내용</th>
			<th>파일</th>
			<th>올린날짜</th>
			<th>수정날짜</th>
			<th>조회수</th>
			<th>횟수</th>
			<th>유저번호</th>
			<th>수정/삭제</th>
		</tr>
	</thead>
	<tbody id="bdBody">
	
	</tbody>
</table>
<button onclick="boardAdd()">게시물추가</button>
<script>
	function boardUpdate(binum){
		var bititle = document.querySelector('input[name=bititle' + binum + ']').value;
		var bitext = document.querySelector('input[name=bitext' + binum + ']').value;
		var bifile = document.querySelector('input[name=bifile' + binum + ']').value;
		var bicredat = document.querySelector('input[name=bicredat' + binum + ']').value;
		var bimoddat = document.querySelector('input[name=bimoddat' + binum + ']').value;
		var bicnt = document.querySelector('input[name=bicnt' + binum + ']').value;
		var biactive = document.querySelector('input[name=biactive' + binum + ']').value;
		var uinum = document.querySelector('input[name=uinum' + binum + ']').value;
		alert('수정 : ' + binum + bititle + bitext + bifile + bicredat + bimoddat + bicnt + biactive + uinum);
		var param = {binum:binum,bititle:bititle,bitext:bitext,bifile:bifile,bicredat:bicredat,bimoddat:bimoddat,bicnt:bicnt,biactive:biactive,uinum:uinum};
		var xhr = new XMLHttpRequest();
		var url = "/board";
		var.open('PUT',url);
		var.setRequestHeader('Content-type','application/json;charset=utf-8');
		xhr.onreadysatechange = function(){
			if(xhr.readyState==4){
				if(xhr.status==200){
					if(xhr.responseText=='1'){
						alert('수정성공');
						location.href='/uri/board/list';
					}
				}else{
					alert('수정실패');
				}
			}
		}
		alert(JSON.stringify(param));
		xhr.send(JSON.stringify(param));
	}
	function boardDelete(binum){
		var xhr= new XMLHttpRequest();
		var url = "/board/" + binum;
		xhr.open('DELETE',url);
		xhr.onreadystatechange = function(){
			if(xhr.readyState==4){
				if(xhr.status==200){
					if(xhr.responseText=='1'){
						alert('삭제성공');
						location.href='/board';
					}
				}else{
					alert('삭제실패');
				}
			}
		}
		xhr.send();
	}
	function boardAdd(binum){
		var html = '<tr>';
		html += '<td>&nbsp;</td>';
		html += '<td><input type="text" name="bititle"></td>';
		html += '<td><input type="text" name="bitext"></td>';
		html += '<td><input type="text" name="bifile"></td>';
		html += '<td><input type="text" name="bicredat"></td>';
		html += '<td><input type="text" name="bimoddat"></td>';
		html += '<td><input type="text" name="bicnt"></td>';
		html += '<td><input type="text" name="biactive"></td>';
		html += '<td><input type="text" name="uinum"></td>';
		html += '<td><button onclick="boardinsert()">저장<button></td>';
		html += '</tr>';
		document.querSeletor('tbody').insertAdjacentHTML('beforeend',html);
	}
	
</script>
</body>
</html>