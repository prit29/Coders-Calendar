package com.noobsever.codingcontests.services;

import com.noobsever.codingcontests.Models.ApiResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("/data")
    Call<ApiResponse> getAllContestsFromApi();

}
