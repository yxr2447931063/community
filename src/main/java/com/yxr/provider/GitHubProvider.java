package com.yxr.provider;

import com.alibaba.fastjson.JSON;
import com.yxr.dto.AccessTokenDTO;
import com.yxr.dto.GitHubUser;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 使用OKClient来发起请求，和HTTP client差不多的用法
 */
@Component
public class GitHubProvider {

    /**
     * 1. 获取accessToken
     * @param accessTokenDTO
     * @return
     */
    public String getAccessToken(AccessTokenDTO accessTokenDTO){
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();

        RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(accessTokenDTO));
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String string = response.body().string();
            String split = string.split("&")[0];
            String splitToken = split.split("=")[1];
            return   splitToken;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 2. 通过 accessToken 获取用户的信息
     * @param accessToken
     * @return
     */
    public GitHubUser getUser(String accessToken ){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.github.com/user?access_token="+accessToken)
                .build();
        try {
            Response response = client.newCall(request).execute();
            String string = response.body().string();
            GitHubUser gitHubUser = JSON.parseObject(string, GitHubUser.class);
            return gitHubUser;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }



}




