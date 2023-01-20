package com.ssafy.myhome.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import java.net.URLEncoder;


public class location {
	 /**
     * 경위도 정보로 주소를 불러오는 메소드
     * @throws UnsupportedEncodingException 
     */
     public static String[] getXY(String address) throws UnsupportedEncodingException{
    	 
//       String url = URLEncoder("https://dapi.kakao.com/v2/local/search/address.json?query=" + address, "UTF-8");
    	 String url = "https://dapi.kakao.com/v2/local/search/address.json?query=" + URLEncoder.encode(address, "UTF-8");
       System.out.println(address);
       System.out.println(url);
       String[] XY = null;
       try{
         XY = getRegionAddress(getJSONData(url));
         //LOGGER.info(addr);
       }catch(Exception e){
         System.out.println("주소 api 요청 에러");
         e.printStackTrace();
       }
         return XY;
     }

   /**
     * REST API로 통신하여 받은 JSON형태의 데이터를 String으로 받아오는 메소드
     */
	private static String getJSONData(String apiUrl) throws Exception {
   	HttpURLConnection conn = null;
   	StringBuffer response = new StringBuffer();
   	 
   	//인증키 - KakaoAK하고 한 칸 띄워주셔야해요!
   	String auth = "KakaoAK " + "735f17c46e21f44f580be47785165fde";

   	//URL 설정
       URL url = new URL(apiUrl);
//       System.out.println(url);
        
       conn = (HttpURLConnection) url.openConnection();
       
       //Request 형식 설정
       conn.setRequestMethod("GET");
       conn.setRequestProperty("X-Requested-With", "curl");
       conn.setRequestProperty("Authorization", auth);

       //request에 JSON data 준비
       conn.setDoOutput(true);
        
       //보내고 결과값 받기
       int responseCode = conn.getResponseCode();
       if (responseCode == 400) {
           System.out.println("400:: 해당 명령을 실행할 수 없음");
           System.out.println(conn.getResponseMessage());
       } else if (responseCode == 401) {
           System.out.println("401:: Authorization가 잘못됨");
       } else if (responseCode == 500) {
           System.out.println("500:: 서버 에러, 문의 필요");
       } else { // 성공 후 응답 JSON 데이터받기
       	 
       	 Charset charset = Charset.forName("UTF-8");
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), charset));
            
            String inputLine;
            while ((inputLine = br.readLine()) != null) {
            	response.append(inputLine); 
            } 
        }
        
        return response.toString();
   }
   
    /**
     * JSON형태의 String 데이터에서 주소값(address_name)만 받아오기
     */
	private static String[] getRegionAddress(String jsonString) {
       String x = "";
       String y = "";
       JSONObject jObj = (JSONObject) JSONValue.parse(jsonString);
       JSONObject meta = (JSONObject) jObj.get("meta");
       long size = (long) meta.get("total_count");
       
       if(size>0){
           JSONArray jArray = (JSONArray) jObj.get("documents");
           JSONObject subJobj = (JSONObject) jArray.get(0);
           JSONObject roadAddress =  (JSONObject) subJobj.get("road_address");
           
           if(roadAddress == null){
               JSONObject subsubJobj = (JSONObject) subJobj.get("address");
               x = (String) subsubJobj.get("x");
               y = (String) subsubJobj.get("y");
           }else{
               x = (String) roadAddress.get("x");
               y = (String) roadAddress.get("y");
           }
           
           if(x.equals("") || x==null){
               subJobj = (JSONObject) jArray.get(1);
               subJobj = (JSONObject) subJobj.get("address");
               x =(String) subJobj.get("x");    
               y =(String) subJobj.get("y");    
           }
       }
       return new String[] {y,x};
   }
	
	// 두 좌표 사이의 거리를 구하는 함수
    // dsitance(첫번쨰 좌표의 위도, 첫번째 좌표의 경도, 두번째 좌표의 위도, 두번째 좌표의 경도)
	public static double distance(double lat1, double lon1, double lat2, double lon2){
        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1))* Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1))*Math.cos(deg2rad(lat2))*Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60*1.1515*1609.344;

        return dist; //단위 meter
    }
    //10진수를 radian(라디안)으로 변환
    private static double deg2rad(double deg){
        return (deg * Math.PI/180.0);
    }
    //radian(라디안)을 10진수로 변환
    private static double rad2deg(double rad){
        return (rad * 180 / Math.PI);
    }
}
