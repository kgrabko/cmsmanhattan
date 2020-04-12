package com.cbsinc.cms.export.hsql;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;
import java.util.LinkedList;
import java.util.Map;

/**
* Пример отправки POST запроса из Java
*/
public class TestPostRequest {
private boolean useProxy;
private String proxyAdress;
private int proxyPort;

public static void main(String[] args) {
	//https://dmapi.joker.com/request/login?username=grabko@mail.ru&password=xvabcvje
		
	TestPostRequest testPostRequest = new TestPostRequest();
//создаём лист параметров для запроса
java.util.List<String[]> params = new LinkedList<String[]>();
params.add(new String[]{"username", "grabko@mail.ru"});
params.add(new String[]{"password", "xvabcvje"});
////будем использовать прокси
//testPostRequest.useProxy = true;
//testPostRequest.proxyAdress = "myProxy";
//testPostRequest.proxyPort = 1111;
try {
//отправляем запрос на страницу логина http://vkontakte.ru
System.out.println(testPostRequest.sendPostRequest("https://dmapi.joker.com/request/login", params));
params = new LinkedList<String[]>();
params.add(new String[]{"domain", "irr.bz"});

System.out.println(testPostRequest.sendPostRequest("https://dmapi.joker.com/request/dns-zone-get", params));
} 
catch (IOException e) 
{
e.printStackTrace();
}

}

public static void main1(String[] args) {
	TestPostRequest testPostRequest = new TestPostRequest();
//	создаём лист параметров для запроса
	java.util.List<String[]> params = new LinkedList<String[]>();
	params.add(new String[]{"email", "email=test%40mail.ru"});
	params.add(new String[]{"pass", "test"});
////	будем использовать прокси
//	testPostRequest.useProxy = true;
//	testPostRequest.proxyAdress = "myProxy";
//	testPostRequest.proxyPort = 1111;
	try {
//	отправляем запрос на страницу логина http://vkontakte.ru
	System.out.println(testPostRequest.sendPostRequest("http://vkontakte.ru/login.php", params));
	} catch (IOException e) {
	e.printStackTrace();
	}
	}
/**
* возвращает соединение с сервером
*/
private HttpURLConnection getConnection(String urlLocation) throws IOException {
URL url = new URL(urlLocation);
HttpURLConnection connection = null;
if (useProxy) {
connection = (HttpURLConnection) url.openConnection(new Proxy(java.net.Proxy.Type.HTTP, new InetSocketAddress(proxyAdress, proxyPort)));
} else {
connection = (HttpURLConnection) url.openConnection();
}
connection.setDoInput(true);
connection.setDoOutput(true);
connection.setConnectTimeout(Integer.MAX_VALUE);
populateConnectionWithStandartProperties(connection);
return connection;
}

/**
* Указываем в запросе обычные для браузеров параметры
*/
private void populateConnectionWithStandartProperties(HttpURLConnection connection) {
connection.setRequestProperty("Accept", "text/html");
connection.setRequestProperty("Accept-Language", "en-US");
connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 5.1; ru; rv:1.8.1.12) Gecko/20080201 Firefox");
connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=utf-8");
connection.setRequestProperty("Pragma", "no-cache");
connection.setInstanceFollowRedirects(false);
}

/**
* Возвращает все cookie, которые сервер присылает нам с ответом
*/
private String getResponseCookies(HttpURLConnection connection) {
Map responseHeaders = connection.getHeaderFields();
java.util.List responseCookies = (java.util.List) responseHeaders.get("Set-Cookie");
String allCookies = "";
if (responseCookies != null) {
for (int i = 0; i < responseCookies.size(); i++) {
allCookies = allCookies + responseCookies.get(i) + " ";
}
}
return allCookies;
}

/**
* Посылает POST запрос и возвращает response code, cookies и исходный код страницы.
*/
private String sendPostRequest(String urlLocation, java.util.List parameters) throws IOException {
StringBuffer result = new StringBuffer();
HttpURLConnection connection = getConnection(urlLocation);
System.out.println("Соединение с " + urlLocation + " установлено.");
connection.setRequestMethod("POST");
connection.setRequestProperty("Referer", urlLocation);
connection.setRequestProperty("Cookie", "your cookies may be here");
String data = "";
if (parameters != null) {
for (int i = 0; i < parameters.size(); i++) {
String param[] = (String[]) parameters.get(i);
if (i != 0) {
data = data + "&";
}
data = data + param[0] + "=" + URLEncoder.encode(param[1], "UTF-8");
}

}
if (parameters != null && data.length() != 0) {
connection.setRequestProperty("Content-Length", Integer.toString(data.length()));
}
connection.connect();
if (parameters != null && data.length() != 0) {
System.out.println("Отправка данных..");
PrintWriter out = new PrintWriter(connection.getOutputStream());
out.write(data);
out.flush();
System.out.println("Получение ответа от сервера..");
}
result.append("response code: ").append(connection.getResponseCode()).append("\n");
result.append("response cookies: ").append(getResponseCookies(connection)).append("\n");
result.append("response page source: ").append("\n");

BufferedReader rd = new BufferedReader(new InputStreamReader(connection.getInputStream()));
String line;
while ((line = rd.readLine()) != null) {
result.append(line).append("\n");
}
connection.disconnect();
return result.toString();
}
}
