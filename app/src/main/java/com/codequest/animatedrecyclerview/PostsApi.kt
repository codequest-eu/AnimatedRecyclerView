package com.codequest.animatedrecyclerview

import io.reactivex.Observable
import retrofit2.http.GET

interface PostsApi {

    @GET("posts")
    fun getPosts(): Observable<List<Post>>
}
