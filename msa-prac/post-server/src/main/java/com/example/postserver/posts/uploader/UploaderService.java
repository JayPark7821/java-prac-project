package com.example.postserver.posts.uploader;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

/**
 * UploaderService
 *
 * @author jaypark
 * @version 1.0.0
 * @since 8/25/24
 */
@Service
public class UploaderService {

    @Value("${sns.user-server}")
    private String userServerUrl;
    private RestClient restClient = RestClient.create();

    public UploaderInfo getUserInfo(int userId){
        return restClient.get()
            .uri(userServerUrl + "/api/users/" + userId)
            .retrieve()
            .onStatus(HttpStatusCode::isError, (((request, response) -> {
                throw new RuntimeException("Failed to get user info");
            })))
            .body(UploaderInfo.class);
    }

}
