<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
		<title>demo</title>
		<script type="text/javascript" src="<%=request.getContextPath()%>/jquery-1.3.2.min.js"></script>
		<script type="text/javascript">
		var MessageManager = function(){

			var listeners = {};

			function getDateStr(){
				var now= new Date();
				var month=now.getMonth()+1;
				var day=now.getDate();
				var hour=now.getHours();
				var minute=now.getMinutes();
				var second=now.getSeconds();
				return (month+"/"+day+"/"+hour+":"+minute+":"+second);
			}

			return {
				printlog : function(data) {
					var log_console = document.getElementById("socket_log_console");
					if (log_console) {
						if (log_console.innerHTML.length >= 5000)
							log_console.innerHTML = "";
						log_console.innerHTML += (getDateStr() + ":" + data + "<br />");
					}
				},

				addListener : function(messageType, listener) {
					listeners[messageType] = listener;
				},

				newMessage : function(data) {

					if (!data)
						return;

					var messages = data['messages'];
					if (!messages || messages.length==0)
						return;

					for(var i=0; i<messages.length; i++) {
						var message = messages[i];
						if (!message || !message.messageType)
							continue;
						var listener = listeners[message.messageType];

						if (!listener) {
							MessageManager.printlog("can not find listener for message type:"+message.messageType);
							continue;
						}

						try {
				        	listener(message);
				        } catch(e) {
							MessageManager.printlog("publish message due to error:"+e);
						}
					}
				}
			}
		}();

		function SocketOnData(data){
			MessageManager.printlog(data);
		 	var jsonMessage = eval('('+data+')');
			MessageManager.newMessage(jsonMessage);
		}

		function SocketSend(data){
		 	window.document.socket.send(data);
		}

		function SocketReset() {
			MessageManager.printlog("connection reset!");
		}

		function SocketOnConnect() {
			MessageManager.printlog("connection connect!");
		}

		function SocketOnClose() {
			MessageManager.printlog("connection close!");
		}

		function SocketOnError(error){
			MessageManager.printlog("connection error:"+error);
		}

		var sub_messageTypes = "";

		function getHost() {
			return "127.0.0.1";
		}

		function getUser() {
			return "292";
		}

		function getMessageTypes() {
			return sub_messageTypes;
		}

		function setMessageTypes(types) {
			sub_messageTypes = types;
		}
		</script>
	</head>
	<body>
		<div id="socket_log_console">
		</div>

	  	<object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000"
				id="socket" width="100%" height="100%"
				codebase="http://fpdownload.macromedia.com/get/flashplayer/current/swflash.cab">
				<param name="quality" value="high" />
				<param name="bgcolor" value="#869ca7" />
				<param name="swLiveConnect" value="true" />
				<param name="allowScriptAccess" value="sameDomain" />
				<embed src="main.swf" quality="high" bgcolor="#869ca7"
					width="400" height="250" name="socket" align="middle"
					play="true"
					loop="false"
					quality="high"
					allowScriptAccess="sameDomain"
					type="application/x-shockwave-flash"
					pluginspage="http://www.adobe.com/go/getflashplayer">
				</embed>
		</object>

	</body>
</html>