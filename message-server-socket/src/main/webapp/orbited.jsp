<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
		<title>robited</title>
		<%
			String command = request.getParameter("orbited_command");
			String userId = request.getParameter("userId");
			String messageTypes = request.getParameter("messageTypes");

			com.rensea.message.server.MessageServerSettings messageServerSettings =com.sirius.component.spring.BeanLocator.getBean(com.rensea.message.server.MessageServerSettings.class);
			String host = messageServerSettings.getSetting("server.host");
			String port = messageServerSettings.getSetting("server.port");
			String rensea_host = messageServerSettings.getSetting("rensea.host");
			String comet_host = messageServerSettings.getSetting("comet.host");
		%>
		<script type="text/javascript">document.domain = document.domain;</script>
		<script type="text/javascript" src="http://<%=comet_host%>/static/Orbited.js"></script>
	</head>
	<body>
		<script type="text/javascript">
			var userId = "<%=userId%>";
			var messageTypes = "<%=messageTypes%>";
			var comet_connection = null;

			function comet_init(){
				comet_connection = new Orbited.TCPSocket();
				comet_connection.open("<%=host%>", <%=port%>, false);

				comet_connection.onopen = function() {
					if (userId != "null" && messageTypes != "null") {
						var authentication = "authentication={userId:'"+ userId +"',messageTypes:" + messageTypes + ",clientType:'flash'}";
			            comet_connection.send(authentication);
			            document.getElementById("orbited_callback").src = 'http://<%=rensea_host%>/html/orbited_proxy.html?comet_connected=true';
		            }
		        }

		        comet_connection.onclose = function(code) {
		        	//authenticationed = false;
		        	//start();
		        	document.getElementById("orbited_callback").src = 'http://<%=rensea_host%>/html/orbited_proxy.html?comet_connected=false';
		        }
			}

			comet_init();

			var last_ping_time = 0;
			function comet_ping(userId){
				comet_connection.send("ping={userId:'"+ userId +"'}");
				var curDate=new Date();
				var cur_time = curDate.getTime();
				/*如果60秒内没有收到ping那么重置链接*/
				if ((cur_time - last_ping_time) > (1000*60)){
					if (comet_connection != null) {
						comet_connection.close();
					}
					comet_init();
					Comet.connect();
				}
			}

			var myDate=new Date();
			last_ping_time = myDate.getTime();
			setInterval("comet_ping("+userId+")",1000*30);

			var Comet = function(){

				return {
					connect : function(){
						comet_connection.onread = function(data) {
				        	if (data.indexOf("ping=")==0) {
				        		var myDate=new Date();
				        		last_ping_time = myDate.getTime();
				        	}
				        	document.getElementById("orbited_callback").src = 'http://<%=rensea_host%>/html/orbited_proxy.html?data='+data;
				        }
					},

					send : function(message){
						comet_connection.onread = function(data) {
				        	if (data.indexOf("ping=")==0) {
				        		var myDate=new Date();
				        		last_ping_time = myDate.getTime();
				        	}
				        	document.getElementById("orbited_callback").src = 'http://<%=rensea_host%>/html/orbited_proxy.html?data='+data;
				        	comet_connection.send(message);
				        }
					},

					close : function(){
						comet_connection.close();
						document.getElementById("orbited_callback").src = 'http://<%=rensea_host%>/html/orbited_proxy.html?comet_connected=false';
					}
				};

			}();
		</script>
		<% if ("connect".equals(command)) { %>
				<script type="text/javascript">Comet.connect();</script>
		<% } else if ("close".equals(command)) { %>
			    <script type="text/javascript">Comet.close();</script>
		<% } else if (command != null && command != "") {%>
				<script type="text/javascript">Comet.send("<%=command%>");</script>
		<% } %>
		<iframe id="orbited_callback" width="0" height="0"></iframe>
	</body>
</html>