package com.example.CoffeeShop.jwt;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class KakaoAPI {
    // Kakao API 키와 리디렉션 URI를 application.properties에서 가져옴
    @Value("${kakao.api_key}")
    private String kakaoApiKey;

    @Value("${kakao.redirect_uri}")
    private String kakaoRedirectUri;

    // 인가 코드를 받아서 accessToken을 반환하는 메서드
    public String getAccessToken(String code) {
        String accessToken = "";
        String reqUrl = "https://kauth.kakao.com/oauth/token"; // Kakao Token API URL

        try {
            URL url = new URL(reqUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            // 필수 헤더 세팅
            conn.setRequestProperty("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
            conn.setDoOutput(true); // OutputStream으로 POST 데이터를 넘겨주겠다는 옵션.

            // 쿼리 파라미터 준비
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
            StringBuilder sb = new StringBuilder();
            sb.append("grant_type=authorization_code");  // 인가 코드로 인증
            sb.append("&client_id=").append(kakaoApiKey);  // 카카오 API 키
            sb.append("&redirect_uri=").append(kakaoRedirectUri);  // 카카오 리디렉션 URI
            sb.append("&code=").append(code);  // 인가 코드

            bw.write(sb.toString());
            bw.flush();

            int responseCode = conn.getResponseCode();  // 응답 코드 확인
            log.info("[KakaoApi.getAccessToken] responseCode = {}", responseCode);

            BufferedReader br;
            if (responseCode >= 200 && responseCode < 300) {
                br = new BufferedReader(new InputStreamReader(conn.getInputStream()));  // 정상 응답
            } else {
                br = new BufferedReader(new InputStreamReader(conn.getErrorStream()));  // 오류 응답
            }

            // 응답을 문자열로 읽어들임
            String line = "";
            StringBuilder responseSb = new StringBuilder();
            while ((line = br.readLine()) != null) {
                responseSb.append(line);
            }
            String result = responseSb.toString();
            log.info("responseBody = {}", result);

            // JSON 응답에서 access_token을 추출
            JsonElement element = JsonParser.parseString(result);
            JsonObject jsonObject = element.getAsJsonObject();
            accessToken = jsonObject.get("access_token").getAsString();  // access_token 가져오기

            br.close();
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();  // 예외 처리
        }
        return accessToken;  // accessToken 반환
    }

    // accessToken을 받아서 사용자 정보를 반환하는 메서드
    public HashMap<String, Object> getUserInfo(String accessToken) {
        HashMap<String, Object> userInfo = new HashMap<>();
        String reqUrl = "https://kapi.kakao.com/v2/user/me";  // 사용자 정보 API URL
        try {
            URL url = new URL(reqUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Authorization", "Bearer " + accessToken);  // Bearer 토큰으로 인증
            conn.setRequestProperty("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

            int responseCode = conn.getResponseCode();  // 응답 코드 확인
            log.info("[KakaoApi.getUserInfo] responseCode : {}", responseCode);

            BufferedReader br;
            if (responseCode >= 200 && responseCode <= 300) {
                br = new BufferedReader(new InputStreamReader(conn.getInputStream()));  // 정상 응답
            } else {
                br = new BufferedReader(new InputStreamReader(conn.getErrorStream()));  // 오류 응답
            }

            // 응답을 문자열로 읽어들임
            String line = "";
            StringBuilder responseSb = new StringBuilder();
            while ((line = br.readLine()) != null) {
                responseSb.append(line);
            }
            String result = responseSb.toString();
            log.info("responseBody = {}", result);

            // JSON 응답에서 사용자 정보 추출
            JsonElement element = JsonParser.parseString(result);

            JsonObject properties = element.getAsJsonObject().get("properties").getAsJsonObject();
            JsonObject kakaoAccount = element.getAsJsonObject().get("kakao_account").getAsJsonObject();

            String nickname = properties.getAsJsonObject().get("nickname").getAsString();  // 닉네임
            String email = kakaoAccount.getAsJsonObject().get("email").getAsString();  // 이메일

            userInfo.put("nickname", nickname);
            userInfo.put("email", email);

            br.close();

        } catch (Exception e) {
            e.printStackTrace();  // 예외 처리
        }
        return userInfo;  // 사용자 정보 반환
    }

    // accessToken을 받아서 로그아웃시키는 메서드
    public void kakaoLogout(String accessToken) {
        String reqUrl = "https://kapi.kakao.com/v1/user/logout";  // 로그아웃 API URL

        try {
            URL url = new URL(reqUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Authorization", "Bearer " + accessToken);  // Bearer 토큰으로 인증

            int responseCode = conn.getResponseCode();  // 응답 코드 확인
            log.info("[KakaoApi.kakaoLogout] responseCode : {}", responseCode);

            BufferedReader br;
            if (responseCode >= 200 && responseCode <= 300) {
                br = new BufferedReader(new InputStreamReader(conn.getInputStream()));  // 정상 응답
            } else {
                br = new BufferedReader(new InputStreamReader(conn.getErrorStream()));  // 오류 응답
            }

            // 응답을 문자열로 읽어들임
            String line = "";
            StringBuilder responseSb = new StringBuilder();
            while ((line = br.readLine()) != null) {
                responseSb.append(line);
            }
            String result = responseSb.toString();
            log.info("kakao logout - responseBody = {}", result);  // 로그아웃 응답 로그

        } catch (Exception e) {
            e.printStackTrace();  // 예외 처리
        }
    }
}
