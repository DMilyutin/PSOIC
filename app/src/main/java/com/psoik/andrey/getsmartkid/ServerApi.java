package com.psoik.andrey.getsmartkid;
import com.psoik.andrey.getsmartkid.forJSON.BooleanRespons;
import com.psoik.andrey.getsmartkid.forJSON.Children;
import com.psoik.andrey.getsmartkid.forJSON.Contribution;
import com.psoik.andrey.getsmartkid.forJSON.Loan;
import com.psoik.andrey.getsmartkid.forJSON.Parent;
import com.psoik.andrey.getsmartkid.forJSON.Product;
import com.psoik.andrey.getsmartkid.forJSON.Task;


import java.util.ArrayList;



import retrofit2.Call;

import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;

import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ServerApi {

    // Авторизация и регистрация
    @FormUrlEncoded
    @POST("api/auth/login")
    Call<Parent> autoriz(@Field("mail") String mail,
                         @Field("password") String password);


    @GET("api/checkmail")
    Call<BooleanRespons> checkMail(@Query("mail") String mail);

    @FormUrlEncoded
    @POST("api/auth/register/parent")
    Call<Parent> registerNewParent(@Field("mail") String mail,
                                   @Field("password") String password,
                                   @Field("name") String name);

    @FormUrlEncoded
    @POST("api/auth/register/child")
    Call<Children> registerNewChildren(@Field("mail") String mail,
                                       @Field("password") String password,
                                       @Field("name") String name);

    // Дети

    @GET("api/children")
    Call<ArrayList<Children>> getOllChildren();

    // История

    // Задачи

    @GET("api/task")
    Call<ArrayList<Task>> getTasks(@Query("childId") int childId);

    @FormUrlEncoded
    @POST("api/task")
    Call<Task> createNewTask(@Field("childId") int childId,
                             @Field("name") String name,
                             @Field("expire") String expire,
                             @Field("price") int price,
                             @Field("description") String description);

    @GET("api/task/confirm")
    Call<Task> confirmTask(@Query("id") int id);

    @GET("api/disapprove/tasks")
    Call<Task> disapproveTask(@Query("id") int id);

    @GET("api/approve/tasks")
    Call<Task> approveTask(@Query("id") int id);

    @GET("api/task/reject")
    Call<Task> rejectTask(@Query("id") int id);

    @DELETE("api/task")
    Call<Task> deleteTask(@Query("id") int id);

    // Награды

    @FormUrlEncoded
    @POST("api/product")
    Call<Product> createNewProduct(@Field("childId") int childId,
                                   @Field("name") String name,
                                   @Field("category") String category,
                                   @Field("price") int price);

    @GET("api/product")
    Call<ArrayList<Product>> getOllProduct(@Query("childId") int childId);

    @DELETE("api/product")
    Call<Task> deleteProduct(@Query("id") int id);

    // Банк

    // Акции

    // Кредиты

    @GET("api/loan")
    Call <ArrayList<Loan>>  getOllLoan(@Query("childId") int childId);

    @FormUrlEncoded
    @POST("api/loan")
    Call<Loan> createNewLoan(@Field("childId") int childId,
                                     @Field("name")String name,
                                     @Field("value")int value,
                                     @Field("percent")double percent,
                                     @Field("timestamp")int timestamp);


    // Вклады

    @FormUrlEncoded
    @POST("api/contribution")
    Call<Contribution> createNewContribution(@Field("childId") int childId,
                                             @Field("name")String name,
                                             @Field("value")int value,
                                             @Field("percent")double percent,
                                             @Field("timestamp")int timestamp);

    @GET("api/approve/contributins")
    Call<Contribution> approveContribution(@Query("id") int id,
                                           @Query("value") int value,
                                           @Query("percent") double percent,
                                           @Query("timestamp") int timestamp);

    @GET("api/contribution")
    Call <ArrayList<Contribution>>  getOllContribution(@Query("childId") int childId);
}
