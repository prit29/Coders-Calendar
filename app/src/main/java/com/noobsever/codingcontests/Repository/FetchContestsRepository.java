package com.noobsever.codingcontests.Repository;

import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.noobsever.codingcontests.Models.ApiResponse;
import com.noobsever.codingcontests.Models.ContestObject;
import com.noobsever.codingcontests.Services.APIClient;
import com.noobsever.codingcontests.Services.ApiInterface;

import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FetchContestsRepository {

    private ApiInterface apiInterface;
    private FetchApiAsyncTask fetchApiAsyncTask;

    public FetchContestsRepository(){

        apiInterface = APIClient.getClient().create(ApiInterface.class);
        Call<ApiResponse> call = apiInterface.getAllContestsFromApi();
        fetchApiAsyncTask = new FetchApiAsyncTask(call);

    }

    public LiveData<List<ContestObject>> getContestsListAsync(){
        return fetchApiAsyncTask.getLiveContestsList();
    }

    public void fetchContestFromApi(){
        fetchApiAsyncTask.execute();
    }

    private static class FetchApiAsyncTask extends AsyncTask<Void,Void,Void>{

        private Call<ApiResponse> call;
        private MutableLiveData<List<ContestObject>> liveContestList;

        private FetchApiAsyncTask(Call<ApiResponse> call){
              this.call = call;
              liveContestList = new MutableLiveData<>();
        }

        private MutableLiveData<List<ContestObject>> getLiveContestsList () {
            return  liveContestList;
        }

        @Override
        protected Void doInBackground(Void... voids) {

            call.enqueue(new Callback<ApiResponse>() {
                @Override
                public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {

                    Log.e("APIFETCHEDREPOASYNC>>",response.code()+" ");
                    ApiResponse apiResponse = response.body();
                    assert apiResponse != null;
                    liveContestList.postValue(apiResponse.getObjects());
                }

                @Override
                public void onFailure(Call<ApiResponse> call, Throwable t) {
                    Log.e("API FETCH ERROR>>>", Objects.requireNonNull(t.getMessage()));
                }
            });
            return null;
        }
    }

}
